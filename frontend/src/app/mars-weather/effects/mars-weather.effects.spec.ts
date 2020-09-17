import { TestBed } from '@angular/core/testing';
import { provideMockActions } from '@ngrx/effects/testing';
import { Observable } from 'rxjs';

import { MarsWeatherEffects } from './mars-weather.effects';

describe('MarsWeatherEffects', () => {
  let actions$: Observable<any>;
  let effects: MarsWeatherEffects;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        MarsWeatherEffects,
        provideMockActions(() => actions$)
      ]
    });

    effects = TestBed.inject(MarsWeatherEffects);
  });

  it('should be created', () => {
    expect(effects).toBeTruthy();
  });
});
