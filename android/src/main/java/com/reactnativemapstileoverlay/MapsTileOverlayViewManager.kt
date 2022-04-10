package com.reactnativemapstileoverlay

import com.facebook.react.uimanager.*
import com.facebook.react.uimanager.annotations.ReactProp

class MapsTileOverlayViewManager : SimpleViewManager<MapsTileOverlayView>() {
  override fun getName() = "MapsTileOverlayView"

  override fun createViewInstance(reactContext: ThemedReactContext): MapsTileOverlayView {
    return MapsTileOverlayView(reactContext)
  }

  @ReactProp(name = "mapReady")
  fun setMapReady(view: MapsTileOverlayView, mapReady: Boolean) {
    view.mapReady = mapReady
    if (mapReady) {
      view.tryLoadMap()
    }
  }

  @ReactProp(name = "urlTemplate")
  fun setUrlTemplate(view: MapsTileOverlayView, urlTemplate: String) {
    view.urlTemplate = urlTemplate
  }

  @ReactProp(name = "zIndex", defaultFloat = -1.0f)
  override fun setZIndex(view: MapsTileOverlayView, zIndex: Float) {
    view.zIndex = zIndex
  }

  @ReactProp(name = "tileSize", defaultFloat = 256.0f)
  fun setTileSize(view: MapsTileOverlayView, tileSize: Float) {
    view.tileSize = tileSize
  }

  @ReactProp(name = "doubleTileSize", defaultBoolean = false)
  fun setDoubleTileSize(view: MapsTileOverlayView, doubleTileSize: Boolean) {
    view.doubleTileSize = doubleTileSize
  }

  @ReactProp(name = "opacity", defaultFloat = 1.0f)
  override fun setOpacity(view: MapsTileOverlayView, opacity: Float) {
    view.opacity = opacity
  }
}
