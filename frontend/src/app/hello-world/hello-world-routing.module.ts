import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HelloWorldComponent} from "./hello-world.component";

const routes: Routes = [
  {path: 'hello-world', component: HelloWorldComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HelloWorldRoutingModule {
}
