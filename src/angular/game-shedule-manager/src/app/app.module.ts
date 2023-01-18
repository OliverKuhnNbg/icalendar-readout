import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { TestComponentComponent } from './components/test-component/test-component.component';
import { AllGamesComponent } from './components/all-games/all-games.component';

@NgModule({
  declarations: [
    AppComponent,
    TestComponentComponent,
    AllGamesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
