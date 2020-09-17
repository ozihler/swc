import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, concatMap } from 'rxjs/operators';
import { EMPTY, of } from 'rxjs';

import * as DisasterFeedActions from '../actions/disaster-feed.actions';



@Injectable()
export class DisasterFeedEffects {

  loadDisasterFeeds$ = createEffect(() => {
    return this.actions$.pipe( 

      ofType(DisasterFeedActions.loadDisasterFeeds),
      concatMap(() =>
        /** An EMPTY observable only emits completion. Replace with your own observable API request */
        EMPTY.pipe(
          map(data => DisasterFeedActions.loadDisasterFeedsSuccess({ data })),
          catchError(error => of(DisasterFeedActions.loadDisasterFeedsFailure({ error }))))
      )
    );
  });



  constructor(private actions$: Actions) {}

}
