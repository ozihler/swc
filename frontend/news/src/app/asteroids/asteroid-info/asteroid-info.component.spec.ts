import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsteroidInfoComponent } from './asteroid-info.component';

describe('AsteroidInfoComponent', () => {
  let component: AsteroidInfoComponent;
  let fixture: ComponentFixture<AsteroidInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AsteroidInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AsteroidInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
