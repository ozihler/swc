import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DisasterFeedRoutingModule } from './disaster-feed-routing.module';
import { DisasterFeedComponent } from './disaster-feed.component';
import { StoreModule } from '@ngrx/store';
import * as fromDisasterFeed from './reducers/disaster-feed.reducer';
import { EffectsModule } from '@ngrx/effects';
import { DisasterFeedEffects } from './effects/disaster-feed.effects';


@NgModule({
  declarations: [DisasterFeedComponent],
  imports: [
    CommonModule,
    DisasterFeedRoutingModule,
    StoreModule.forFeature(fromDisasterFeed.disasterFeedFeatureKey, fromDisasterFeed.reducer),
    EffectsModule.forFeature([DisasterFeedEffects])
  ]
})
export class DisasterFeedModule { }
