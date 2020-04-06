import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { AcceuilComponent } from './acceuil/acceuil.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FormCreationAnnonceComponent } from './form-creation-annonce/form-creation-annonce.component';
import { UserService } from './services/User.service';
import { SalleService } from './services/Salle.service';
import { LoginComponent } from './login/login.component';
import { UserAccueilComponent } from './user-accueil/user-accueil.component';
import { JwtInterceptor } from './helpers/jwt-interceptor.interceptor';

@NgModule({
	declarations: [
		AppComponent,
		InscriptionComponent,
		AcceuilComponent,
		NavbarComponent,
		FormCreationAnnonceComponent,
		LoginComponent,
		UserAccueilComponent
	],
	imports: [
		BrowserModule,
		FormsModule,
		ReactiveFormsModule,
		AppRoutingModule,
		HttpClientModule,
		AppRoutingModule,
		NgbModule,
	],
	providers: [
		UserService,
		SalleService,
		{ provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }

	],
	bootstrap: [AppComponent]
})
export class AppModule { }

