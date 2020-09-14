import * as fromAsteroids from '../reducers/asteroids.reducer';
import { selectAsteroidstate } from './asteroids.selectors';

describe('Asteroids Selectors', () => {
  it('should select the feature state', () => {
    const result = selectAsteroidstate({
      [fromAsteroids.asteroidsFeatureKey]: {}
    });

    expect(result).toEqual({});
  });
});
