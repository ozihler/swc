import { TestBed } from '@angular/core/testing';

import { DisasterFeedService } from './disaster-feed.service';

describe('DisasterFeedService', () => {
  let service: DisasterFeedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DisasterFeedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
