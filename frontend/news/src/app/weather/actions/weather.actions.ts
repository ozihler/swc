import {createAction, props} from '@ngrx/store';

export const loadWeathers = createAction(
  '[Weather] Load Weathers',
  props<{ data: any }>()
);

export const loadWeathersSuccess = createAction(
  '[Weather] Load Weathers Success',
  props<{ data: any }>()
);

export const loadWeathersFailure = createAction(
  '[Weather] Load Weathers Failure',
  props<{ error: any }>()
);
