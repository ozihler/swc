import { Component } from '@angular/core';

@Component({
  selector: 'nw-root',
  template: `
    <a routerLink="/hello-world">Hello World</a>
    <router-outlet></router-outlet>
  `,
  styles: []
})
export class AppComponent {
}
