import {AfterContentInit, Component} from '@angular/core';
import {Observable} from "rxjs";
import {WeatherService} from "../weather.service";

@Component({
  selector: 'nw-weather',
  template: `
    <div>Current Temperature:
      <span>{{temperature$ | async}}°</span>
    </div>
  `,
  styles: []
})
export class WeatherComponent implements AfterContentInit {

  temperature$: Observable<number> = new Observable<number>();

  constructor(private weatherService: WeatherService) {

  }

  ngAfterContentInit(): void {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        position =>
          this.temperature$ = this.weatherService.fetchCurrentTemperatureAt(position.coords.latitude, position.coords.longitude));
    }
  }

}
