import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {MarsWeather} from "./mars-weather";
import {select, Store} from "@ngrx/store";
import {State} from "./reducers/mars-weather.reducer";
import {getError, getMarsWeather, isLoading} from "./selectors/mars-weather.selectors";
import {loadMarsWeathers} from "./actions/mars-weather.actions";

@Component({
  selector: 'nw-mars-weather',
  template: `

    <div class="card text-center" *ngIf="!(loading$|async)">
      <div *ngIf="marsWeather$ | async as marsWeather">
        <h5 class="d-flex justify-content-center">
          Temperature at landing site of InSight on Mars ({{marsWeather.location.latitude| number: '1.1-2'}},
          {{marsWeather.location.longitude| number: '1.1-2'}}):
        </h5>
        <h1 class="d-flex justify-content-center">
          {{marsWeather.averageTemperatureInCelsius | number: '1.1-2'}}°C ({{marsWeather.season | titlecase}})
        </h1>
      </div>

      <div class="d-flex justify-content-center" *ngIf="loading$ | async">
        <ngb-alert [type]="'info'" [dismissible]="false">
          Daten werden geladen...
        </ngb-alert>
      </div>

      <div class="d-flex justify-content-center" *ngIf="error$ | async as error">
        <ngb-alert [type]="'danger'" [dismissible]="false">
          <p>Folgender Fehler trat bei der Abfrage auf:</p>
          <p>{{error}}</p>
        </ngb-alert>
      </div>

    </div>
  `,
  styles: []
})
export class MarsWeatherComponent implements OnInit {
  loading$: Observable<boolean>;
  error$: Observable<any>;
  marsWeather$: Observable<MarsWeather>;

  constructor(private store: Store<State>) {
    this.loading$ = store.pipe(select(isLoading));
    this.marsWeather$ = store.pipe(select(getMarsWeather));
    this.error$ = store.pipe(select(getError));
  }

  ngOnInit(): void {
    this.store.dispatch(loadMarsWeathers());
  }

}
