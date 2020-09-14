import { createAction, props } from '@ngrx/store';

export const loadAsteroidss = createAction(
  '[Asteroids] Load Asteroidss'
);

export const loadAsteroidssSuccess = createAction(
  '[Asteroids] Load Asteroidss Success',
  props<{ data: any }>()
);

export const loadAsteroidssFailure = createAction(
  '[Asteroids] Load Asteroidss Failure',
  props<{ error: any }>()
);
