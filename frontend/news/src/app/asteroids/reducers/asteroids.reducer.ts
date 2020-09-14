import { Action, createReducer, on } from '@ngrx/store';
import * as AsteroidsActions from '../actions/asteroids.actions';

export const asteroidsFeatureKey = 'asteroids';

export interface State {

}

export const initialState: State = {

};


export const reducer = createReducer(
  initialState,

  on(AsteroidsActions.loadAsteroidss, state => state),
  on(AsteroidsActions.loadAsteroidssSuccess, (state, action) => state),
  on(AsteroidsActions.loadAsteroidssFailure, (state, action) => state),

);

