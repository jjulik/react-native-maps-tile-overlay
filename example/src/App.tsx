import React, { useState } from 'react';
import MapView, { Region } from 'react-native-maps';

import { Button, StyleSheet, View } from 'react-native';
import { MapsTileOverlayView } from 'react-native-maps-tile-overlay';

export default function App() {
  const [mapReady, setMapReady] = useState(false);

  const [initialLocation] = useState<Region>({
    latitude: 39.833333,
    longitude: -98.583333,
    latitudeDelta: 0.3,
    longitudeDelta: 0,
  });

  const onClick = () => {
    setMapReady(true);
    console.log('mapReady');
  };

  return (
    <View style={styles.container}>
      <MapView
        style={styles.map}
        initialRegion={initialLocation}
        nativeID="yeet"
      >
        <MapsTileOverlayView mapReady={mapReady} urlTemplate="" />
      </MapView>
      <View style={styles.button}>
        <Button title="Click me" onPress={onClick} />
      </View>
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
    height: '80%',
  },
  button: {
    width: '100%',
    height: '20%',
  }
});
