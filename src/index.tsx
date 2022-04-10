import { requireNativeComponent, UIManager, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-maps-tile-overlay' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

type MapsTileOverlayProps = {
  urlTemplate: string;
  mapReady: boolean;
  zIndex?: number;
  tileSize?: number;
  doubleTileSize?: boolean;
  opacity?: number;
};

const ComponentName = 'MapsTileOverlayView';

export const MapsTileOverlayView =
  UIManager.getViewManagerConfig(ComponentName) != null
    ? requireNativeComponent<MapsTileOverlayProps>(ComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };
