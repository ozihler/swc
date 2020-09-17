import {Injectable} from '@angular/core';
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {catchError, map, concatMap} from 'rxjs/operators';
import {EMPTY, of} from 'rxjs';

import * as MarsWeatherActions from '../actions/mars-weather.actions';
import {MarsWeatherService} from "../mars-weather.service";


@Injectable()
export class MarsWeatherEffects {

  loadMarsWeathers$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(MarsWeatherActions.loadMarsWeathers),
      concatMap((action) => {
        return this.marsWeatherService.fetchWeather()
          .pipe(
            map(data => MarsWeatherActions.loadMarsWeathersSuccess({data}),
              catchError(error => of(MarsWeatherActions.loadMarsWeathersFailure({error})))
            )
          );
      }));
  });


  constructor(private actions$: Actions,
              private marsWeatherService: MarsWeatherService) {
  }

}
