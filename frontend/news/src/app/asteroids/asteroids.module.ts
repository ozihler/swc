import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AsteroidsRoutingModule } from './asteroids-routing.module';
import { AsteroidsComponent } from './asteroids.component';
import { StoreModule } from '@ngrx/store';
import * as fromAsteroids from './reducers/asteroids.reducer';
import { EffectsModule } from '@ngrx/effects';
import { AsteroidsEffects } from './effects/asteroids.effects';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";


@NgModule({
  declarations: [AsteroidsComponent],
  imports: [
    CommonModule,
    FontAwesomeModule,
    AsteroidsRoutingModule,
    StoreModule.forFeature(fromAsteroids.asteroidsFeatureKey, fromAsteroids.reducer),
    EffectsModule.forFeature([AsteroidsEffects])
  ]
})
export class AsteroidsModule { }
