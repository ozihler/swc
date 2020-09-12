import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WeatherRoutingModule } from './weather-routing.module';
import { WeatherComponent } from './weather.component';
import { StoreModule } from '@ngrx/store';
import * as fromWeather from './reducers/weather.reducer';
import { EffectsModule } from '@ngrx/effects';
import { WeatherEffects } from './effects/weather.effects';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";


@NgModule({
    declarations: [WeatherComponent],
    exports: [
        WeatherComponent
    ],
  imports: [
    CommonModule,
    WeatherRoutingModule,
    StoreModule.forFeature(fromWeather.weatherFeatureKey, fromWeather.reducer),
    EffectsModule.forFeature([WeatherEffects]),
    NgbModule
  ]
})
export class WeatherModule { }
