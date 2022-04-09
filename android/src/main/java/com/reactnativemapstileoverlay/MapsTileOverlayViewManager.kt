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
}
