import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormCreationAnnonceComponent } from './form-creation-annonce/form-creation-annonce.component';

@NgModule({
  declarations: [
    AppComponent,
    FormCreationAnnonceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
