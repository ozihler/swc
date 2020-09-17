import * as fromMarsWeather from './mars-weather.actions';

describe('loadMarsWeathers', () => {
  it('should return an action', () => {
    expect(fromMarsWeather.loadMarsWeathers().type).toBe('[MarsWeather] Load MarsWeathers');
  });
});
