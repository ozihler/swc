import {Component, OnInit} from '@angular/core';
import {Asteroid, State} from "./reducers/asteroids.reducer";
import {select, Store} from "@ngrx/store";
import {getAsteroids, isLoading} from "./selectors/asteroids.selectors";
import {Observable} from "rxjs";
import {loadAsteroids} from "./actions/asteroids.actions";
import {faBomb} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'nw-asteroids',
  template: `
    <div *ngIf="isLoading$ | async">Lade Asteroidendaten</div>
    <div *ngIf="!(isLoading$ | async)">
      <div class="card-columns">
        <div class="card"
             *ngFor="let asteroid of asteroids$ | async">
          <div class="card-body">
            <h5 class="card-title">{{asteroid.name}} ({{asteroid.id}})</h5>
            <p class="card-text">
              Average Distance: {{asteroid.averageLunarDistance | number:'1.1-2'}} ld
              ({{asteroid.averageMissDistanceInKm| number:'1.1-2'}} km)
            </p>
            <p class="card-text">
              Kinetic Energy (Tons of TNT) {{asteroid.kineticEnergyInTonsOfTNT| number:'1.1-2'}} t
              ({{asteroid.magnitude}})
            </p>
            <p class="card-text">
              {{asteroid.numberOfHiroshimaBombs}} Hiroshima Bombs (around {{asteroid.numberOfHiroshimaDeaths}} deaths)
            </p>

            <nw-explosion-icon *ngFor="let r of getRating(asteroid.numberOfHiroshimaBombs)"></nw-explosion-icon>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: []
})
export class AsteroidsComponent implements OnInit {
  asteroids$: Observable<Asteroid[]>;
  isLoading$: Observable<boolean>;
  faBomb: any;

  constructor(private store: Store<State>) {
    this.asteroids$ = store.pipe(select(getAsteroids));
    this.isLoading$ = store.pipe(select(isLoading));
    this.faBomb = faBomb;
  }

  ngOnInit(): void {
    const endDate = new Date();
    const startDate = new Date();
    startDate.setDate(startDate.getDate() - 7);
    this.store.dispatch(loadAsteroids({
      data: {
        startDate: startDate,
        endDate: endDate
      }
    }));

  }

  getRating(nr: number) {
    return new Array(nr);
  }
}
