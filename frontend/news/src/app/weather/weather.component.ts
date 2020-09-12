import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {WeatherService} from "./weather.service";
import {select, Store} from "@ngrx/store";
import {State} from "./reducers/weather.reducer";
import {loadWeathers} from "./actions/weather.actions";
import {getCoordinates, getError, getTemperature, getWeatherLoading} from "./selectors/weather.selectors";
import {Coordinates} from "./reducers/weather.reducer";

@Component({
  selector: 'nw-weather',
  template: `
    <div *ngIf="coordinates$ |async as coordinates">
      Current Temperature at {{coordinates.latitude}}, {{coordinates.longitude}}:
      <span *ngIf="temperature$ | async as temperature">
        {{temperature}}° C
      </span>
    </div>
    <div *ngIf="loading$ | async">
      <ngb-alert [type]="'info'" [dismissible]="false">Daten werden geladen...</ngb-alert>
    </div>
    <ngb-alert [type]="'danger'" [dismissible]="false" *ngIf="error$ | async as error">
      <p>Folgender Fehler trat bei der Abfrage auf:</p>
      <p>{{error}}</p>
    </ngb-alert>
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

