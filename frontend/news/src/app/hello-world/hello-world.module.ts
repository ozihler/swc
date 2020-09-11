import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HelloWorldRoutingModule } from './hello-world-routing.module';
import { HelloWorldComponent } from './hello-world.component';


@NgModule({
  declarations: [HelloWorldComponent],
  imports: [
    CommonModule,
    HelloWorldRoutingModule
  ]
})
export class HelloWorldModule { }
