import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Asteroid, Statistics} from "./reducers/asteroids.reducer";

export interface Asteroids {
  asteroids: Asteroid[];
  statistics: Statistics;
}

@Injectable({
  providedIn: 'root'
})
export class AsteroidsService {

  constructor(private http: HttpClient) {

  }

  public fetchAsteroids(startDate: Date, endDate: Date): Observable<Asteroids> {
    let url = `${environment.backendBaseUrl}${
      environment.asteroidsUrl
    }/kineticEnergy?startDate=${
      AsteroidsService.formatDate(startDate)}&endDate=${
      AsteroidsService.formatDate(endDate)}`;

    return this.http.get<Asteroids>(url);
  }

  private static formatDate(date: Date) {
    let d = new Date(date);
    let month = `${d.getMonth() + 1}`;
    let day = `${d.getDate()}`;
    let year = d.getFullYear();

    if (month.length < 2) {
      month = '0' + month;
    }
    if (day.length < 2) {
      day = '0' + day;
    }

    return [year, month, day].join('-');
  }
}
