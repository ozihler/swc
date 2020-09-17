import { createAction, props } from '@ngrx/store';

export const loadDisasterFeeds = createAction(
  '[DisasterFeed] Load DisasterFeeds'
);

export const loadDisasterFeedsSuccess = createAction(
  '[DisasterFeed] Load DisasterFeeds Success',
  props<{ data: any }>()
);

export const loadDisasterFeedsFailure = createAction(
  '[DisasterFeed] Load DisasterFeeds Failure',
  props<{ error: any }>()
);
