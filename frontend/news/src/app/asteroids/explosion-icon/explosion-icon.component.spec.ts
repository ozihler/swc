import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExplosionIconComponent } from './explosion-icon.component';

describe('ExplosionIconComponent', () => {
  let component: ExplosionIconComponent;
  let fixture: ComponentFixture<ExplosionIconComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExplosionIconComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExplosionIconComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
