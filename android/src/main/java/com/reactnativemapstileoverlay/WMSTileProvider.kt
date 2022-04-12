package com.reactnativemapstileoverlay

import android.util.Log
import com.google.android.gms.maps.model.Tile
import com.google.android.gms.maps.model.TileProvider
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.pow

class WMSTileProvider(tileSize: Float, urlTemplate: String, doubleTileSize: Boolean, requestHeaders: HashMap<String, Any>) : TileProvider {
  private var mapBound: Array<Double> = arrayOf(-20037508.34789244, 20037508.34789244)
  var urlTemplate: String = urlTemplate
  var tileSize: Float = tileSize
  var doubleTileSize: Boolean = doubleTileSize
  var requestHeaders: HashMap<String, Any> = requestHeaders

  private fun getTileUrl(x: Int, y: Int, zoom: Int): URL? {
    var bb: Array<Double> = getBoundingBox(x, y, zoom)
    var s: String = urlTemplate.replace("{minX}", bb[0].toBigDecimal().toPlainString())
      .replace("{minY}", bb[1].toBigDecimal().toPlainString())
      .replace("{maxX}", bb[2].toBigDecimal().toPlainString())
      .replace("{maxY}", bb[3].toBigDecimal().toPlainString())
      .replace("{width}", "256")
      .replace("{height}", "256")
    return URL(s)
  }

  private fun downloadTile(url: URL): ByteArray {
    var conn = url.openConnection() as HttpURLConnection ?: throw Exception("I HATE JAVA")
    conn.doInput = true
    this.requestHeaders.entries.forEach { (k,v) -> conn.setRequestProperty(k, v.toString())}
    conn.connect()
    try {
      conn.inputStream.use { input ->
        return input.readBytes()
      }
    } catch (e: Exception) {
      conn.errorStream.use { s ->
        Log.e("WMSTileProvider", "Error downloading tile url $url . Response code: ${conn.responseCode}. Response message: ${conn.responseMessage}")
      }
      throw e
    }
  }

  private fun getBoundingBox(x: Int, y: Int, zoom: Int): Array<Double> {
    var two: Double = 2.0
    var tile: Double = 20037508.34789244 * 2 / two.pow(zoom)
    return arrayOf(mapBound[0] + x * tile,
      mapBound[1] - (y + 1) * tile,
      mapBound[0] + (x + 1) * tile,
      mapBound[1] - y * tile)
  }

  override fun getTile(x: Int, y: Int, zoom: Int): Tile {
    val url = getTileUrl(x, y, zoom) ?: return TileProvider.NO_TILE
    try {
      val imageBytes = downloadTile(url)
      return Tile(x, y, imageBytes)
    } catch (e: Exception) {
      Log.e("MapDemo", "Error fetching tile")
      return TileProvider.NO_TILE
    }
  }
}
