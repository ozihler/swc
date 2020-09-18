import {Component, OnDestroy, OnInit} from '@angular/core';
import {Feed} from "./feed";
import {FormBuilder, FormGroup} from "@angular/forms";
import {DisasterFeedService} from './disaster-feed.service';
import {catchError, map, tap} from "rxjs/operators";
import {EMPTY, Observable} from "rxjs";


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

    <div>
      {{liveData$ | async}}
    </div>
  `,
  styles: []
})
export class DisasterFeedComponent implements OnInit, OnDestroy {

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
  liveData$: Observable<any>;

  constructor(private formBuilder: FormBuilder,
              private disasterFeedService: DisasterFeedService) {
    this.disasterFeedForm = formBuilder.group(
      {
        title: '',
        message: ''
      }
    );


    this.liveData$ = this.disasterFeedService.messages$.pipe(
      map((rows: any) => rows.data),
      catchError(error => {
        throw error
      }),
      tap({
          error: error => console.log('[Live component] Error:', error),
          complete: () => console.log('[Live component] Connection Closed')
        }
      )
    );

  }

  ngOnInit(): void {

  }

  showSubmitDisasterFeedForm() {
    this.showButton = false;
  }

  submitDisasterFeed() {
    let feed = this.disasterFeedForm.value as Feed;
    console.log(feed)
    this.disasterFeedService.sendMessage(feed);
    this.disasterFeedForm.reset();
    this.showButton = true;
  }

  ngOnDestroy(): void {
    this.disasterFeedService.close();
  }
}
