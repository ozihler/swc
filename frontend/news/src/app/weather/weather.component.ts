import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {WeatherService} from "./weather.service";
import {select, Store} from "@ngrx/store";
import {State} from "./reducers/weather.reducer";
import {loadWeathers} from "./actions/weather.actions";
import {getTemperature, getWeatherLoading} from "./selectors/weather.selectors";

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
    </div>
  `,
  styles: []
})
export class WeatherComponent implements OnInit {

  loading$: Observable<boolean>;
  temperature$: Observable<number>;

  constructor(private weatherService: WeatherService,
              private store: Store<State>) {
    this.loading$ = this.store.pipe(select(getWeatherLoading));
    this.temperature$ = this.store.pipe(select(getTemperature));
  }

  ngOnInit(): void {

    if (navigator.geolocation) {
      navigator.geolocation
        .getCurrentPosition(position =>
          this.store.dispatch(loadWeathers({data: position})));
    }
  }

}

