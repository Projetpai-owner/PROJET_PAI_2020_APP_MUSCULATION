import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { AcceuilComponent } from './acceuil/acceuil.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FormCreationAnnonceComponent } from './form-creation-annonce/form-creation-annonce.component';
import { UserService} from './services/User.service';
import { SalleService } from './services/Salle.service';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    InscriptionComponent,
    AcceuilComponent,
    NavbarComponent,
    FormCreationAnnonceComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule
  ],
  providers: [
    UserService,
    SalleService

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

