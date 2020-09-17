import { createAction, props } from '@ngrx/store';
import {MarsWeather} from "../mars-weather";

export const loadMarsWeathers = createAction(
  '[MarsWeather] Load MarsWeathers'
);

export const loadMarsWeathersSuccess = createAction(
  '[MarsWeather] Load MarsWeathers Success',
  props<{ data: MarsWeather }>()
);

export const loadMarsWeathersFailure = createAction(
  '[MarsWeather] Load MarsWeathers Failure',
  props<{ error: any }>()
);
