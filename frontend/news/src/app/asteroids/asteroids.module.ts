import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AsteroidsRoutingModule } from './asteroids-routing.module';
import { AsteroidsComponent } from './asteroids.component';
import { StoreModule } from '@ngrx/store';
import * as fromAsteroids from './reducers/asteroids.reducer';
import { EffectsModule } from '@ngrx/effects';
import { AsteroidsEffects } from './effects/asteroids.effects';


@NgModule({
  declarations: [AsteroidsComponent],
  imports: [
    CommonModule,
    AsteroidsRoutingModule,
    StoreModule.forFeature(fromAsteroids.asteroidsFeatureKey, fromAsteroids.reducer),
    EffectsModule.forFeature([AsteroidsEffects])
  ]
})
export class AsteroidsModule { }
