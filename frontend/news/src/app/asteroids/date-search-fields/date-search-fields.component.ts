import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Dates} from "../asteroids.component";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'nw-date-search-fields',
  template: `
    <div>
      <form [formGroup]="form" (ngSubmit)="submitSearch()">
        <span>Startdatum: <input formControlName="startDate" type="date"/></span>
        <span>Enddatum: <input formControlName="endDate" type="date"/></span>
        <button type="submit">Suchen!</button>
      </form>
    </div>
  `,
  styles: []
})
export class DateSearchFieldsComponent implements OnInit {

  @Output() datesSelectedEvent = new EventEmitter<Dates>();
  form: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.form = formBuilder.group(
      {
        startDate: '',
        endDate: ''
      }
    );
  }

  ngOnInit(): void {
  }

  submitSearch() {
    this.datesSelectedEvent.emit(this.form.value);
  }
}
