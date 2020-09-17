import {Component} from '@angular/core';

@Component({
  selector: 'nw-root',
  template: `
    <nw-weather></nw-weather>
    <nw-mars-weather></nw-mars-weather>
    <nav class="navbar navbar-expand-sm bg-light">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" routerLink="/hello-world">Hello World</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" routerLink="/asteroids">Asteroids</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" routerLink="/disaster-feed">Disaster Feed</a>
        </li>
      </ul>
    </nav>
    <router-outlet></router-outlet>`,
  styles: []
})
export class AppComponent {
}
