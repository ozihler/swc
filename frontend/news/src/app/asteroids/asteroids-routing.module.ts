import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AsteroidsComponent} from "./asteroids.component";

const routes: Routes = [
  {path:"asteroids", component:AsteroidsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AsteroidsRoutingModule { }
