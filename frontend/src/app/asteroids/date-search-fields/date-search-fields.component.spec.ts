import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DateSearchFieldsComponent } from './date-search-fields.component';

describe('DateSearchFieldsComponent', () => {
  let component: DateSearchFieldsComponent;
  let fixture: ComponentFixture<DateSearchFieldsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DateSearchFieldsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DateSearchFieldsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
