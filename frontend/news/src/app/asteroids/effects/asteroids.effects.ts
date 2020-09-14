import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import {catchError, map, concatMap, exhaustMap} from 'rxjs/operators';
import { EMPTY, of } from 'rxjs';

import * as AsteroidsActions from '../actions/asteroids.actions';



@Injectable()
export class AsteroidsEffects {

  loadAsteroids$ = createEffect(() => {
    return this.actions$.pipe(

      ofType(AsteroidsActions.loadAsteroids),
      exhaustMap(() =>
        /** An EMPTY observable only emits completion. Replace with your own observable API request */
        EMPTY.pipe(
          map(data => AsteroidsActions.loadAsteroidsSuccess({ data })),
          catchError(error => of(AsteroidsActions.loadAsteroidsFailure({ error }))))
      )
    );
  });



  constructor(private actions$: Actions) {}

}
