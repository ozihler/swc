import {Injectable} from '@angular/core';
import {Actions, createEffect, ofType} from '@ngrx/effects';
import {catchError, concatMap, map} from 'rxjs/operators';

import * as WeatherActions from '../actions/weather.actions';
import {loadWeathersFailure, loadWeathersSuccess} from '../actions/weather.actions';
import {WeatherService} from "../weather.service";
import {of} from "rxjs";


@Injectable()
export class WeatherEffects {

  loadWeathers$ = createEffect(() => {
    return this.actions$.pipe(
      ofType(WeatherActions.loadWeathers),
      concatMap((action) => {
          const coordinates = {
            latitude: action.data.latitude,
            longitude: action.data.longitude
          }
          return this.weatherService
            .fetchCurrentTemperatureAt(coordinates)
            .pipe(
              map(temperature => loadWeathersSuccess({data: temperature})),
              catchError(error => of(loadWeathersFailure({error})))
            );
        }
      ))
      ;
  });


  constructor(private actions$: Actions,
              private weatherService: WeatherService) {
  }

}
