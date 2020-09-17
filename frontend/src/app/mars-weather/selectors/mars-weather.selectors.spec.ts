import * as fromMarsWeather from '../reducers/mars-weather.reducer';
import { selectMarsWeatherState } from './mars-weather.selectors';

describe('MarsWeather Selectors', () => {
  it('should select the feature state', () => {
    const result = selectMarsWeatherState({
      [fromMarsWeather.marsWeatherFeatureKey]: {}
    });

    expect(result).toEqual({});
  });
});
