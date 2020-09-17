import { Action, createReducer, on } from '@ngrx/store';
import * as DisasterFeedActions from '../actions/disaster-feed.actions';

export const disasterFeedFeatureKey = 'disasterFeed';

export interface State {

}

export const initialState: State = {

};


export const reducer = createReducer(
  initialState,

  on(DisasterFeedActions.loadDisasterFeeds, state => state),
  on(DisasterFeedActions.loadDisasterFeedsSuccess, (state, action) => state),
  on(DisasterFeedActions.loadDisasterFeedsFailure, (state, action) => state),

);

