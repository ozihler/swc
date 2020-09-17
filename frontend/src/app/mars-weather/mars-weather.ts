export interface MarsWeather {
  season: string;
  averageTemperatureInCelsius: number;
  location: Location;
}

export interface Location{
  latitude: number;
  longitude: number;
}
