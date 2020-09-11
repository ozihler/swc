import * as fromWeather from '../reducers/weather.reducer';
import { selectWeatherState } from './weather.selectors';

describe('Weather Selectors', () => {
  it('should select the feature state', () => {
    const result = selectWeatherState({
      [fromWeather.weatherFeatureKey]: {}
    });

    expect(result).toEqual({});
  });
});
