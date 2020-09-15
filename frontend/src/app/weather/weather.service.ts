import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {map} from "rxjs/operators";
import {Coordinates} from "./reducers/weather.reducer";

export interface CurrentWeatherDto {
  temperature: number;
}

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  constructor(private http: HttpClient) {
  }

  fetchCurrentTemperatureAt(coordinates: Coordinates): Observable<number> {
    const currentWeatherUrl = `${environment.backendBaseUrl}/current-weather?lat=${coordinates.latitude}&lon=${coordinates.longitude}`;
    return this.http.get<CurrentWeatherDto>(currentWeatherUrl)
      .pipe(map(response => response.temperature));
  }
}
