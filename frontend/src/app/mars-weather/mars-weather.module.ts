import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MarsWeatherRoutingModule } from './mars-weather-routing.module';
import { MarsWeatherComponent } from './mars-weather.component';
import { StoreModule } from '@ngrx/store';
import * as fromMarsWeather from './reducers/mars-weather.reducer';
import { EffectsModule } from '@ngrx/effects';
import { MarsWeatherEffects } from './effects/mars-weather.effects';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";


@NgModule({
  declarations: [MarsWeatherComponent],
  exports: [
    MarsWeatherComponent
  ],
  imports: [
    CommonModule,
    MarsWeatherRoutingModule,
    StoreModule.forFeature(fromMarsWeather.marsWeatherFeatureKey, fromMarsWeather.reducer),
    EffectsModule.forFeature([MarsWeatherEffects]),
    NgbModule
  ]
})
export class MarsWeatherModule { }
