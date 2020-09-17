import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {DisasterFeedComponent} from "./disaster-feed.component";

const routes: Routes = [
  {
    path: "disaster-feed",
    component: DisasterFeedComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DisasterFeedRoutingModule {
}
