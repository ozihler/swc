import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {WeatherService} from "./weather.service";
import {select, Store} from "@ngrx/store";
import {State} from "./reducers/weather.reducer";
import {loadWeathers} from "./actions/weather.actions";
import {getError, getTemperature, getWeatherLoading} from "./selectors/weather.selectors";

@Component({
  selector: 'nw-weather',
  template: `
    <div>Current Temperature:
      <ng-container *ngIf="temperature$ | async as temperature">
        {{temperature}}° C
      </ng-container>
      <ng-container *ngIf="loading$ | async">
        Daten werden geladen...
      </ng-container>
      <ng-container *ngIf="error$ | async as error">
        Folgender Fehler trat bei der Abfrage auf: {{error}}
      </ng-container>
    </div>
  `,
  styles: []
})
export class WeatherComponent implements OnInit {

  loading$: Observable<boolean>;
  temperature$: Observable<number>;
  error$: Observable<string>;

  constructor(private weatherService: WeatherService,
              private store: Store<State>) {
    this.loading$ = this.store.pipe(select(getWeatherLoading));
    this.temperature$ = this.store.pipe(select(getTemperature));
    this.error$ = this.store.pipe(select(getError));
  }

  ngOnInit(): void {

    if (navigator.geolocation) {
      navigator.geolocation
        .getCurrentPosition(position =>
          this.store.dispatch(loadWeathers({data: position.coords})));
    }
  }

}

