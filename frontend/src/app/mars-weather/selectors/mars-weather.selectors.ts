import {createFeatureSelector, createSelector} from '@ngrx/store';
import * as fromMarsWeather from '../reducers/mars-weather.reducer';

export const selectMarsWeatherState = createFeatureSelector<fromMarsWeather.State>(
  fromMarsWeather.marsWeatherFeatureKey
);

export const isLoading = createSelector(
  selectMarsWeatherState,
  state => state.loading
)

export const getMarsWeather = createSelector(
  selectMarsWeatherState,
  state => state.marsWeather
);

export const getError = createSelector(
  selectMarsWeatherState,
  state => state.error
);
