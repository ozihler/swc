import { TestBed } from '@angular/core/testing';
import { provideMockActions } from '@ngrx/effects/testing';
import { Observable } from 'rxjs';

import { DisasterFeedEffects } from './disaster-feed.effects';

describe('DisasterFeedEffects', () => {
  let actions$: Observable<any>;
  let effects: DisasterFeedEffects;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        DisasterFeedEffects,
        provideMockActions(() => actions$)
      ]
    });

    effects = TestBed.inject(DisasterFeedEffects);
  });

  it('should be created', () => {
    expect(effects).toBeTruthy();
  });
});
