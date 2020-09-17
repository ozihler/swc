import {Component, OnInit} from '@angular/core';
import {Feed} from "./feed";


@Component({
  selector: 'nw-disaster-feed',
  template: `
    <div>
      <div>
        <button>
          Inform about immediate disaster!
        </button>
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

  constructor() {
  }

  ngOnInit(): void {
  }

}
