package com.reactnativemapstileoverlay

import android.util.Log
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.pow

class WMSTileProvider(width: Int, height: Int) {
  private var mapBound: Array<Double> = arrayOf(-20037508.34789244, 20037508.34789244)
  private var urlTemplate: String = ""

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
    conn.setRequestProperty("Accept", "*/*")
    conn.setRequestProperty("User-Agent", "com.example.mapdemo")
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
}
