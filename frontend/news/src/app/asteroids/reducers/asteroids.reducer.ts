import {createReducer, on} from '@ngrx/store';
import * as AsteroidsActions from '../actions/asteroids.actions';

export const asteroidsFeatureKey = 'asteroids';

export interface Asteroid {
  averageLunarDistance: number,
  averageMissDistanceInKm: number,
  id: string,
  kineticEnergyInTonsOfTNT: number,
  magnitude: string,
  name: string,
  numberOfHiroshimaBombs: number
}

export interface Statistics {
  averageNrOfHiroshimaBombs: number,
  sdInNrOfHiroshimaBombs: number
}

export interface State {
  asteroids: Asteroid[];
  statistics: Statistics;
  isLoading: boolean;
}

export const initialState: State = {
  asteroids: [],
  statistics: {
    averageNrOfHiroshimaBombs: 0,
    sdInNrOfHiroshimaBombs: 0
  },
  isLoading: false
};


export const reducer = createReducer(
  initialState,

  on(AsteroidsActions.loadAsteroids, state => {
    console.log(state);
    return {
      ...state,
      isLoading: true
    }
  }),
  on(AsteroidsActions.loadAsteroidsSuccess, (state, action) => {
    return {
      ...state,
      isLoading: false
    }
  }),

  on(AsteroidsActions.loadAsteroidsFailure, (state, action) => {
    return {
      ...state,
      isLoading: false
    }
  }),
);

