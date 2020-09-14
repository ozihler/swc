import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {WeatherService} from "./weather.service";
import {select, Store} from "@ngrx/store";
import {Coordinates, State} from "./reducers/weather.reducer";
import {loadWeathers} from "./actions/weather.actions";
import {getCoordinates, getError, getTemperature, getWeatherLoading} from "./selectors/weather.selectors";

@Component({
  selector: 'nw-weather',
  template: `
    <div class="card text-center">
      <div>
        <h5 class="d-flex justify-content-center" *ngIf="coordinates$ | async as coordinates">
          Current Temperature at your location ({{coordinates.latitude| number: '1.1-2'}},
          {{coordinates.longitude| number: '1.1-2'}}):
        </h5>
        <h1 class="d-flex justify-content-center" *ngIf="temperature$ | async as temperature">
          {{temperature}}° C
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

    <div class="card text-center">
      todo Mars Weather
    </div>
  `,
  styles: []
})
export class WeatherComponent implements OnInit {

  loading$: Observable<boolean>;
  temperature$: Observable<number>;
  error$: Observable<string>;
  coordinates$: Observable<Coordinates>;

  constructor(private weatherService: WeatherService,
              private store: Store<State>) {
    this.loading$ = this.store.pipe(select(getWeatherLoading));
    this.temperature$ = this.store.pipe(select(getTemperature));
    this.error$ = this.store.pipe(select(getError));
    this.coordinates$ = this.store.pipe(select(getCoordinates));
  }

  ngOnInit(): void {

    if (navigator.geolocation) {
      navigator.geolocation
        .getCurrentPosition(position =>
          this.store.dispatch(loadWeathers({data: position.coords})));
    }
  }

}

