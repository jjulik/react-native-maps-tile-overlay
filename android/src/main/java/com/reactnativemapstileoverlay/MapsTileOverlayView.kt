package com.reactnativemapstileoverlay

import android.content.Context
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.TileOverlay
import com.google.android.gms.maps.model.TileOverlayOptions

class MapsTileOverlayView(context: Context?) : View(context), OnMapReadyCallback {
  private var map: GoogleMap? = null
  var urlTemplate: String = ""
    set(value) {
      field = value
      this.tileProvider?.urlTemplate = value
      this.tileOverlay?.clearTileCache()
    }
  var mapReady: Boolean = false
  var zIndex: Float = -1.0f
    set(value) {
      field = value
      this.tileOverlay?.zIndex = value
    }
  var tileSize: Float = 256.0f
    set(value) {
      field = value
      this.tileProvider?.tileSize = value
      this.tileOverlay?.clearTileCache()
    }
  var doubleTileSize: Boolean = false
    set(value) {
      field = value
      this.tileProvider?.doubleTileSize = value
      this.tileOverlay?.clearTileCache()
    }
  var opacity: Float = 1.0f
    set(value) {
      field = value
      this.tileOverlay?.transparency = 1 - value
    }

  private var tileProvider: WMSTileProvider? = null
  private var tileOverlay: TileOverlay? = null

  fun tryLoadMap() {
    var parent = this.parent
    if (parent != null && parent is MapView) {
      var parentMapView = parent as MapView
      parentMapView.getMapAsync(this)
    }
  }

  override fun onMapReady(map: GoogleMap?) {
    this.map = map
    if (this.map != null && this.tileProvider != null) {
      // Add the tile provider to the map
      var tileProvider = WMSTileProvider(this.tileSize, this.urlTemplate)
      var tileOverlayOptions = TileOverlayOptions()
      tileOverlayOptions.zIndex(this.zIndex)
      tileOverlayOptions.transparency(1 - this.opacity)
      tileOverlayOptions.tileProvider(tileProvider)
      this.tileProvider = tileProvider
      this.tileOverlay = this.map?.addTileOverlay(tileOverlayOptions)
      this.map?.clear()
    }
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    this.tileOverlay?.remove()
    this.map = null
    this.tileOverlay = null
    this.tileProvider = null
  }
}
