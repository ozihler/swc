import * as fromDisasterFeed from './disaster-feed.actions';

describe('loadDisasterFeeds', () => {
  it('should return an action', () => {
    expect(fromDisasterFeed.loadDisasterFeeds().type).toBe('[DisasterFeed] Load DisasterFeeds');
  });
});
