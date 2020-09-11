import {createReducer, on} from '@ngrx/store';
import * as WeatherActions from '../actions/weather.actions';

export const weatherFeatureKey = 'weather';

export interface State {
  temperature: number;
  latitude: number;
  longitude: number;
  loading: boolean;
}

export const initialState: State = {
  temperature: 0,
  latitude: 0,
  longitude: 0,
  loading: false
};


export const reducer = createReducer(
  initialState,

  on(WeatherActions.loadWeathers, (state, action) => {
    const coords = action.data.coords;
    return {
      ...state,
      latitude: coords.latitude,
      longitude: coords.longitude,
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
  on(WeatherActions.loadWeathersFailure, (state, action) => state),
);

