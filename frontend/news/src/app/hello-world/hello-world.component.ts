import {Component, OnInit} from '@angular/core';
import {HelloWorldService} from "./hello-world.service";
import {Observable} from "rxjs";

@Component({
  selector: 'nw-hello-world',
  template: `
    <p>
      {{helloWorld$ | async }}
    </p>
  `,
  styles: []
})
export class HelloWorldComponent implements OnInit {
  helloWorld$: Observable<string>;

  constructor(private helloWorldService: HelloWorldService) {
    this.helloWorld$ = this.helloWorldService.fetchHelloWorld();
  }

  ngOnInit(): void {

  }

}
