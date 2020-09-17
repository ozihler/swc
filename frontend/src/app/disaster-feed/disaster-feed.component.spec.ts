import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisasterFeedComponent } from './disaster-feed.component';

describe('DisasterFeedComponent', () => {
  let component: DisasterFeedComponent;
  let fixture: ComponentFixture<DisasterFeedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisasterFeedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisasterFeedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
