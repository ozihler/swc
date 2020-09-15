import * as fromAsteroids from './asteroids.actions';

describe('loadAsteroids', () => {
  it('should return an action', () => {
    expect(fromAsteroids.loadAsteroids().type).toBe('[Asteroids] Load Asteroids');
  });
});
