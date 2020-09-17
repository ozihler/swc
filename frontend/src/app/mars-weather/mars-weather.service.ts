import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {MarsWeather} from "./mars-weather";

@Injectable({
  providedIn: 'root'
})
export class MarsWeatherService {

  constructor(private http: HttpClient) {
  }

  public fetchWeather(): Observable<MarsWeather> {
    return this.http.get<MarsWeather>(`${environment.backendBaseUrl}/mars-weather`);
  }
}
