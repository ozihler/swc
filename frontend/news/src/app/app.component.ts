import {Component} from '@angular/core';

@Component({
  selector: 'nw-root',
  template: `
    <nw-weather></nw-weather>
    <a routerLink="/hello-world">Hello World</a>
    <router-outlet></router-outlet>
  `,
  styles: []
})
export class AppComponent {
}
