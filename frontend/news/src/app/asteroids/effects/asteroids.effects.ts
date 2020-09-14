import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, concatMap } from 'rxjs/operators';
import { EMPTY, of } from 'rxjs';

import * as AsteroidsActions from '../actions/asteroids.actions';



@Injectable()
export class AsteroidsEffects {

  loadAsteroidss$ = createEffect(() => {
    return this.actions$.pipe( 

      ofType(AsteroidsActions.loadAsteroidss),
      concatMap(() =>
        /** An EMPTY observable only emits completion. Replace with your own observable API request */
        EMPTY.pipe(
          map(data => AsteroidsActions.loadAsteroidssSuccess({ data })),
          catchError(error => of(AsteroidsActions.loadAsteroidssFailure({ error }))))
      )
    );
  });



  constructor(private actions$: Actions) {}

}
