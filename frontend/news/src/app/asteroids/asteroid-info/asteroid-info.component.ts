import {Component, Input, OnInit} from '@angular/core';
import {Asteroid} from "../reducers/asteroids.reducer";

@Component({
  selector: 'nw-asteroid-info',
  template: `
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">{{asteroid.name}} ({{asteroid.id}})</h5>
        <p class="card-text">
          Average Distance: {{asteroid.averageLunarDistance | number:'1.1-2'}} ld
          ({{asteroid.averageMissDistanceInKm| number:'1.1-2'}} km)
        </p>
        <p class="card-text">
          Kinetic Energy (Tons of TNT) {{asteroid.kineticEnergyInTonsOfTNT| number:'1.1-2'}} t
          ({{asteroid.magnitude}})
        </p>
        <p class="card-text">
          {{asteroid.numberOfHiroshimaBombs}} Hiroshima Bombs (around {{asteroid.numberOfHiroshimaDeaths}} deaths)
        </p>

        <nw-explosion-icon *ngFor="let r of getRating(asteroid.numberOfHiroshimaBombs)"></nw-explosion-icon>
      </div>
    </div>`,
  styles: []
})
export class AsteroidInfoComponent implements OnInit {

  @Input() asteroid: Asteroid = {
    id: "",
    name: "",
    averageLunarDistance: 0,
    averageMissDistanceInKm: 0,
    kineticEnergyInTonsOfTNT: 0,
    magnitude: "",
    numberOfHiroshimaBombs: 0,
    numberOfHiroshimaDeaths: 0
  };


  constructor() {
  }

  ngOnInit(): void {
  }


  getRating(nr: number) {
    return new Array(nr);
  }
}
