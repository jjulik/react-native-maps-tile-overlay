import React, { useState } from 'react';
import MapView, { Region } from 'react-native-maps';

import { StyleSheet, View } from 'react-native';
import { MapsTileOverlayView } from 'react-native-maps-tile-overlay';

export default function App() {
  const [mapReady, setMapReady] = useState(false);

  const [initialLocation] = useState<Region>({
    latitude: 43.648678,
    longitude: -79.38231,
    latitudeDelta: 0.3,
    longitudeDelta: 0,
  });

  const requestHeaders = {
    'Accept': '*/*',
    'User-Agent': 'com.example.reactnativemapstileoverlay',
  };

  return (
    <View style={styles.container}>
      <MapView
        style={styles.map}
        initialRegion={initialLocation}
        onMapReady={() => setMapReady(true)}
        nativeID="yeet"
      >
        <MapsTileOverlayView
          mapReady={mapReady}
          urlTemplate="https://sampleserver6.arcgisonline.com/arcgis/rest/services/Toronto/ImageServer/exportImage?f=image&bbox={minX}%2C{minY}%2C{maxX}%2C{maxY}&bboxSR=EPSG%3A3857&imageSR=EPSG%3A3857&size={width}%2C{height}&format=png"
          requestHeaders={requestHeaders}
        />
      </MapView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
  map: {
    width: '100%',
    height: '100%',
  },
});
