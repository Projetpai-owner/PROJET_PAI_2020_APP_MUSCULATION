import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InscriptionComponent } from './inscription/inscription.component';
import { AcceuilComponent } from './acceuil/acceuil.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth/auth.guard';


export const routes: Routes = [
  {path: '', component: AcceuilComponent},
  {path: 'signUp', component: InscriptionComponent,canActivate:[AuthGuard]},
  {path: 'signIn', component: LoginComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
