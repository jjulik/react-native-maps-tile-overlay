import React, { useState } from 'react';
import MapView, { Region } from 'react-native-maps';

import { StyleSheet, View } from 'react-native';
import { MapsTileOverlayView } from 'react-native-maps-tile-overlay';

export default function App() {
  const [initialLocation] = useState<Region>({
    latitude: 39.833333,
    longitude: -98.583333,
    latitudeDelta: 0.3,
    longitudeDelta: 0,
  });

  return (
    <View style={styles.container}>
      <MapView style={styles.map} initialRegion={initialLocation}>
        <MapsTileOverlayView color="#32a852" style={styles.box} />
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
