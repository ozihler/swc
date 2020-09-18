import {Component, OnInit} from '@angular/core';
import {Feed} from "./feed";
import {FormBuilder, FormGroup} from "@angular/forms";


@Component({
  selector: 'nw-disaster-feed',
  template: `
    <div>

      <div *ngIf="showButton">
        <button
          (click)="showSubmitDisasterFeedForm()"
          class="btn btn-primary">
          Inform about immediate disaster!
        </button>
      </div>

      <div *ngIf="!showButton">
        <form [formGroup]="disasterFeedForm" (ngSubmit)="submitDisasterFeed()">
          <input formControlName="title"
                 type="text"/>
          <input formControlName="message"
                 type="text"/>
          <button
            type="submit"
            class="btn btn-primary">Submit!
          </button>
        </form>
      </div>

      <div>
        <ul>
          <li *ngFor="let feed of feeds">
            <h1>{{feed.title}}</h1>
            <p>{{feed.message}}</p>
          </li>
        </ul>
      </div>
    </div>
  `,
  styles: []
})
export class DisasterFeedComponent implements OnInit {

  feeds: Feed[] = [
    {
      title: "Trump reelected!",
      message: "Trump got reelected in November... 4 more years of disaster"
    },
    {
      title: "Biden hits the bucket!",
      message: "Biden dies after Trump got reelected."
    }
  ]
  showButton: boolean = true;
  disasterFeedForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.disasterFeedForm = formBuilder.group(
      {
        title: '',
        message: ''
      }
    );
  }

  ngOnInit(): void {
  }

  showSubmitDisasterFeedForm() {
    this.showButton = false;
  }

  submitDisasterFeed() {
    console.log(this.disasterFeedForm.value)
    this.disasterFeedForm.reset();
    this.showButton = true;
  }
}
