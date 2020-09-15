import { TestBed } from '@angular/core/testing';
import { provideMockActions } from '@ngrx/effects/testing';
import { Observable } from 'rxjs';

import { AsteroidsEffects } from './asteroids.effects';

describe('AsteroidsEffects', () => {
  let actions$: Observable<any>;
  let effects: AsteroidsEffects;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        AsteroidsEffects,
        provideMockActions(() => actions$)
      ]
    });

    effects = TestBed.inject(AsteroidsEffects);
  });

  it('should be created', () => {
    expect(effects).toBeTruthy();
  });
});
