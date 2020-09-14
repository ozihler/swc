import {Component, OnInit} from '@angular/core';
import {Asteroid, State} from "./reducers/asteroids.reducer";
import {select, Store} from "@ngrx/store";
import {getAsteroids, isLoading} from "./selectors/asteroids.selectors";
import {Observable} from "rxjs";
import {loadAsteroids} from "./actions/asteroids.actions";

@Component({
  selector: 'nw-asteroids',
  template: `
    <div *ngIf="isLoading$ | async">Lade Asteroidendaten</div>
  `,
  styles: []
})
export class AsteroidsComponent implements OnInit {
  asteroids$: Observable<Asteroid[]>;
  isLoading$: Observable<boolean>;

  constructor(private store: Store<State>) {
    this.asteroids$ = store.pipe(select(getAsteroids));
    this.isLoading$ = store.pipe(select(isLoading));
  }

  ngOnInit(): void {
    this.store.dispatch(loadAsteroids());
  }

}
