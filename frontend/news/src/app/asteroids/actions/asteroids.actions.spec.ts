import * as fromAsteroids from './asteroids.actions';

describe('loadAsteroidss', () => {
  it('should return an action', () => {
    expect(fromAsteroids.loadAsteroidss().type).toBe('[Asteroids] Load Asteroidss');
  });
});
