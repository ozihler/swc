import {Action, createReducer, on} from '@ngrx/store';
import * as MarsWeatherActions from '../actions/mars-weather.actions';
import {MarsWeather} from "../mars-weather";

export const marsWeatherFeatureKey = 'marsWeather';

export interface State {
  marsWeather: MarsWeather;
  loading: boolean;
  error: string;
}

export const initialState: State = {
  marsWeather: {
    averageTemperatureInCelsius: 0.0,
    season: '',
    location: {
      latitude: 0.0,
      longitude: 0.0
    }
  },
  loading: false,
  error: ''
};


export const reducer = createReducer(
  initialState,

  on(MarsWeatherActions.loadMarsWeathers, state => {
    return {
      ...state,
      loading: true
    }
  }),
  on(MarsWeatherActions.loadMarsWeathersSuccess, (state, action) => {
    return {
      ...state,
      loading: false,
      marsWeather: action.data
    }
  }),
  on(MarsWeatherActions.loadMarsWeathersFailure, (state, action) => {
    return {
      ...state,
      loading: false
    }
  }),
);

