import { createFeatureSelector, createSelector } from '@ngrx/store';
import * as fromAsteroids from '../reducers/asteroids.reducer';

export const selectAsteroidsState = createFeatureSelector<fromAsteroids.State>(
  fromAsteroids.asteroidsFeatureKey
);
