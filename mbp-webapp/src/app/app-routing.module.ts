import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InscriptionComponent } from './inscription/inscription.component';
import { AcceuilComponent } from './acceuil/acceuil.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth/auth.guard';
import { FormCreationAnnonceComponent } from './form-creation-annonce/form-creation-annonce.component';
import { AdvertListComponent } from './advert-list/advert-list.component';


export const routes: Routes = [
  {path: '', component: AcceuilComponent},
  {path: 'signUp', component: InscriptionComponent, canActivate:[AuthGuard]},
  {path: 'signIn', component: LoginComponent},
  {path: 'createAdvert', component: FormCreationAnnonceComponent},
  {path: 'listAdverts', component: AdvertListComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
