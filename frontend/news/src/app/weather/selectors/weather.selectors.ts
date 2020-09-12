import {createFeatureSelector, createSelector} from '@ngrx/store';
import * as fromWeather from '../reducers/weather.reducer';

export const selectWeatherState = createFeatureSelector<fromWeather.State>(
  fromWeather.weatherFeatureKey
);

export const getWeatherLoading = createSelector(
  selectWeatherState,
  state => state.loading
);

export const getTemperature = createSelector(
  selectWeatherState,
  state => state.temperature
);

export const getCoordinates = createSelector(
  selectWeatherState,
  state => state.coordinates
);
