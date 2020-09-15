import {createAction, props} from '@ngrx/store';

export const loadAsteroids = createAction(
  '[Asteroids] Load Asteroids',
  props<{
    data: {
      startDate: Date,
      endDate: Date
    }
  }>()
);

export const loadAsteroidsSuccess = createAction(
  '[Asteroids] Load Asteroids Success',
  props<{ data: any }>()
);

export const loadAsteroidsFailure = createAction(
  '[Asteroids] Load Asteroids Failure',
  props<{ error: any }>()
);
