import {Component, OnInit} from '@angular/core';
import {Asteroid, State} from "./reducers/asteroids.reducer";
import {select, Store} from "@ngrx/store";
import {getAsteroids, isLoading} from "./selectors/asteroids.selectors";
import {Observable} from "rxjs";
import {loadAsteroids} from "./actions/asteroids.actions";
import {FormBuilder} from "@angular/forms";

export interface Dates {
  startDate: Date;
  endDate: Date;
}

@Component({
  selector: 'nw-asteroids',
  template: `
    <div *ngIf="isLoading$ | async">Lade Asteroidendaten</div>
    <div *ngIf="!(isLoading$ | async)">
      <nw-date-search-fields
        (datesSelectedEvent)="updateAsteroids($event)">

      </nw-date-search-fields>

      <div class="card-columns">
        <nw-asteroid-info
          *ngFor="let asteroid of asteroids$ | async"
          [asteroid]="asteroid">
        </nw-asteroid-info>
      </div>

    </div>
  `,
  styles: []
})
export class AsteroidsComponent implements OnInit {
  asteroids$: Observable<Asteroid[]>;
  isLoading$: Observable<boolean>;

  constructor(
    private store: Store<State>
  ) {
    this.asteroids$ = store.pipe(select(getAsteroids));
    this.isLoading$ = store.pipe(select(isLoading));
  }

  ngOnInit(): void {
    const endDate = new Date();
    const startDate = new Date();
    startDate.setDate(startDate.getDate() - 7);
    this.updateAsteroids({startDate: startDate, endDate: endDate});
  }

  updateAsteroids(dates: Dates): void {
    this.store.dispatch(loadAsteroids({
      data: {
        startDate: dates.startDate,
        endDate: dates.endDate
      }
    }));
  }
}
