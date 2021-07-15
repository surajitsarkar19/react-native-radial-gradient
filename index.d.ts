declare module 'react-native-radial-gradient' {
    import * as React from 'react';
    import * as ReactNative from 'react-native';
  
    export interface RadialGradientProps extends ReactNative.ViewProps {
      colors: string[];
      center?: number[];
      stops?: number[];
      radius?: number;
    }
  
    export class RadialGradient extends React.Component<RadialGradientProps> {}
  
    export default RadialGradient;
  }
