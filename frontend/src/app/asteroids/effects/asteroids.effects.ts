import {Injectable} from '@angular/core';
import {act, Actions, createEffect, ofType} from '@ngrx/effects';
import {catchError, exhaustMap, map} from 'rxjs/operators';

import * as AsteroidsActions from '../actions/asteroids.actions';
import {loadAsteroidsSuccess} from '../actions/asteroids.actions';
import {AsteroidsService} from "../asteroids.service";
import {loadWeathersFailure} from "../../weather/actions/weather.actions";
import {of} from "rxjs";


@Injectable()
export class AsteroidsEffects {

  loadAsteroids$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(AsteroidsActions.loadAsteroids),
      exhaustMap((action) => {
        console.log("asteroid.effects.data: ", action.data);

        return this.asteroidsService.fetchAsteroids(action.data.startDate, action.data.endDate)
          .pipe(
            map(asteroids => loadAsteroidsSuccess({
              data: {
                asteroids: asteroids.asteroids,
                statistics: asteroids.statistics
              }
            })),
            catchError(error=> of(loadWeathersFailure({error})))
          );
      })
    )
  });


  constructor(private actions$: Actions, private asteroidsService: AsteroidsService) {
  }

}
