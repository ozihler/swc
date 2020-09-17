import { createFeatureSelector, createSelector } from '@ngrx/store';
import * as fromDisasterFeed from '../reducers/disaster-feed.reducer';

export const selectDisasterFeedState = createFeatureSelector<fromDisasterFeed.State>(
  fromDisasterFeed.disasterFeedFeatureKey
);
