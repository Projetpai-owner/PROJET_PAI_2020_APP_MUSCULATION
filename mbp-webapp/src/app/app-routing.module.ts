import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InscriptionComponent } from './inscription/inscription.component';
import { AcceuilComponent } from './acceuil/acceuil.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth/auth.guard';
import { FormCreationAnnonceComponent } from './form-creation-annonce/form-creation-annonce.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';


export const routes: Routes = [
  {path: '', component: AcceuilComponent},
  {path: 'signUp', component: InscriptionComponent,canActivate:[AuthGuard],data:{expectedRole:'NONE'}},
  {path: 'signIn', component: LoginComponent,canActivate:[AuthGuard],data:{expectedRole:'NONE'}},
  {path: 'createAdvert', component: FormCreationAnnonceComponent,canActivate:[AuthGuard],data:{expectedRole:'ROLE_USER'}},
  {path: 'resetPassword', component: ResetPasswordComponent,canActivate:[AuthGuard],data:{expectedRole:'NONE'}}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
