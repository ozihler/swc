import {createFeatureSelector, createSelector} from '@ngrx/store';
import * as fromAsteroids from '../reducers/asteroids.reducer';

export const selectAsteroidsState = createFeatureSelector<fromAsteroids.State>(
  fromAsteroids.asteroidsFeatureKey
);

export const getAsteroids = createSelector(selectAsteroidsState, state => state.asteroids);
export const isLoading = createSelector(selectAsteroidsState, state => state.isLoading);
