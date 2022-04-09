package com.reactnativemapstileoverlay

import android.content.Context
import android.view.View
import com.facebook.react.views.view.ReactViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback

class MapsTileOverlayView(context: Context?) : View(context), OnMapReadyCallback {
  var map: GoogleMap? = null
  var urlTemplate: String = ""
    set(value) {
      field = value
    }
  var mapReady: Boolean = false
    set(value) {
      field = value
      if (value) {

      }
    }

  fun tryLoadMap() {
    var parent = this.parent
    if (parent != null && parent is MapView) {
      var parentMapView = parent as MapView
      parentMapView.getMapAsync(this)
    }
  }

  override fun onMapReady(map: GoogleMap?) {
    this.map = map
    if (this.map != null) {
      // Add the tile provider to the map
    }
  }


}
