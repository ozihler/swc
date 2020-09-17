import * as fromDisasterFeed from '../reducers/disaster-feed.reducer';
import { selectDisasterFeedState } from './disaster-feed.selectors';

describe('DisasterFeed Selectors', () => {
  it('should select the feature state', () => {
    const result = selectDisasterFeedState({
      [fromDisasterFeed.disasterFeedFeatureKey]: {}
    });

    expect(result).toEqual({});
  });
});
