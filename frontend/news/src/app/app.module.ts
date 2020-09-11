import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {HelloWorldModule} from "./hello-world/hello-world.module";
import {WeatherModule} from "./weather/weather.module";

@NgModule({
  declarations: [
    AppComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HelloWorldModule,
        HttpClientModule,
        WeatherModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
