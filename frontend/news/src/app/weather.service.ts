import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";
import {map} from "rxjs/operators";

export interface CurrentWeatherDto {
  temperature: number;
}

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  constructor(private http: HttpClient) {
  }

  fetchCurrentTemperatureAt(latitude: number, longitude: number): Observable<number> {
    return this.http
      .get<CurrentWeatherDto>(`${environment.backendBaseUrl}/current-weather?lat=${latitude}&lon=${longitude}`)
      .pipe(
        map(response => response.temperature)
      );
  }
}
