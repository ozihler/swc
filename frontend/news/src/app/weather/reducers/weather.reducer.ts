import {createReducer, on} from '@ngrx/store';
import * as WeatherActions from '../actions/weather.actions';

export const weatherFeatureKey = 'weather';

export interface Coordinates {
  latitude: number;
  longitude: number;
}

export interface State {
  temperature: number;
  coordinates: Coordinates;
  loading: boolean;
  error: string;
}

export const initialState: State = {
  temperature: 0,
  coordinates: {latitude: 0, longitude: 0},
  loading: false,
  error: ''
};

export const reducer = createReducer(
  initialState,

  on(WeatherActions.loadWeathers, (state, action) => {
    const coords = action.data;

    return {
      ...state,
      coordinates: {
        latitude: coords.latitude,
        longitude: coords.longitude
      },
      loading: true
    };
  }),
  on(WeatherActions.loadWeathersSuccess, (state, action) => {
    return {
      ...state,
      temperature: action.data,
      loading: false,
    }
  }),
  on(WeatherActions.loadWeathersFailure, (state, action) => {
    return {
      ...state,
      loading: false,
      error: action.error.message
    }
  }),
);

