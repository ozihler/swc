import * as fromAsteroids from '../reducers/asteroids.reducer';
import { selectAsteroidsState } from './asteroids.selectors';

describe('Asteroids Selectors', () => {
  it('should select the feature state', () => {
    const result = selectAsteroidsState({
      [fromAsteroids.asteroidsFeatureKey]: {}
    });

    expect(result).toEqual({});
  });
});
