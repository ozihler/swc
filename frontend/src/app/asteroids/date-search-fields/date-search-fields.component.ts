import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Dates} from "../asteroids.component";
import {FormBuilder, FormGroup} from "@angular/forms";
import {DatePipe} from "@angular/common";

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
  styles: [],
  providers: [DatePipe]
})
export class DateSearchFieldsComponent implements OnInit {
  @Output() datesSelectedEvent = new EventEmitter<Dates>();
  currentSearch: Dates = {startDate: new Date(), endDate: new Date()};
  form: FormGroup;

  private readonly dateFormat = 'yyyy-MM-dd';

  constructor(private formBuilder: FormBuilder,
              private datePipe: DatePipe) {
    let startDate = datePipe.transform(this.currentSearch.startDate, 'yyyy-MM-dd');

    this.form = formBuilder.group(
      {
        startDate: startDate,
        endDate: datePipe.transform(this.currentSearch.endDate, this.dateFormat)
      }
    );
  }

  ngOnInit(): void {
  }

  submitSearch() {
    this.currentSearch = this.form.value;
    this.datesSelectedEvent.emit(this.currentSearch);
  }
}
