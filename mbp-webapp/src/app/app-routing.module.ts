import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InscriptionComponent } from './inscription/inscription.component';
import { AcceuilComponent } from './acceuil/acceuil.component';
import { LoginComponent } from './login/login.component';
import { UserListeComponent } from './user-liste/user-liste.component';
import { ProfilComponent } from './profil/profil.component';
import { AuthGuard } from './auth/auth.guard';
import { FormCreationAnnonceComponent } from './form-creation-annonce/form-creation-annonce.component';
import { AdvertListComponent } from './advert-list/advert-list.component';
import {EditAdvertComponent} from './edit-advert/edit-advert.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { SupportComponent } from './support/support.component';
import {SupportAdminComponent} from './support-admin/support-admin.component';
import { FriendManagerComponent } from './friend-manager/friend-manager.component';
import { MessageGeneralComponent } from './message-general/message-general.component';
import {AdvertViewComponent} from './advert-view/advert-view.component';
import {AdvertOwnerListComponent} from './advert-owner-list/advert-owner-list.component';
import {AdvertOwnerViewComponent} from './advert-owner-view/advert-owner-view.component';
import {AdvertAdminListComponent} from './advert-admin-list/advert-admin-list.component';

export const routes: Routes = [
  {path: '', component: AcceuilComponent},
  {path: 'signUp', component: InscriptionComponent, canActivate: [AuthGuard], data: {expectedRole: 'NONE'}},
  {path: 'signIn', component: LoginComponent, canActivate: [AuthGuard], data: {expectedRole: 'NONE'}},
  {path: 'createAdvert', component: FormCreationAnnonceComponent, canActivate: [AuthGuard], data: {expectedRole: 'ROLE_USER'}},
  {path: 'resetPassword', component: ResetPasswordComponent, canActivate: [AuthGuard], data: {expectedRole: 'NONE'}},
  {path: 'listeUser', component: UserListeComponent, canActivate: [AuthGuard], data: {expectedRole: 'ROLE_ADMIN'}},
  {path: 'myAccount', component: ProfilComponent, canActivate: [AuthGuard], data: {expectedRole: 'ROLE_BOTH'}},
  {path: 'technicalSupport', component: SupportComponent, canActivate: [AuthGuard], data: {expectedRole: 'ROLE_USER'}},
  {path: 'adminSupport', component: SupportAdminComponent, canActivate: [AuthGuard], data: {expectedRole: 'ROLE_ADMIN'}},
  {path: 'listAdverts', component: AdvertListComponent, canActivate: [AuthGuard], data: {expectedRole: 'ROLE_USER'}},
  {path: 'listAdvertsAdmin', component: AdvertAdminListComponent, canActivate: [AuthGuard], data: {expectedRole: 'ROLE_ADMIN'}},
  {path: 'editAdvert/:id', component: EditAdvertComponent, canActivate: [AuthGuard], data: {expectedRole: 'ROLE_USER'}},
  {path: 'friendManager', component: FriendManagerComponent, canActivate: [AuthGuard], data: {expectedRole: 'ROLE_BOTH'}},
  {path: 'messageGeneral', component: MessageGeneralComponent, canActivate: [AuthGuard], data: {expectedRole: 'ROLE_ADMIN'}},
  {path: 'viewAnnonce/:id', component: AdvertViewComponent, canActivate: [AuthGuard], data: {expectedRole: 'ROLE_BOTH'}},
  {path: 'viewAnnonceProprio/:id', component: AdvertOwnerViewComponent, canActivate: [AuthGuard], data: {expectedRole: 'ROLE_USER'}},
  {path: 'advertListProprio', component: AdvertOwnerListComponent, canActivate: [AuthGuard], data: {expectedRole: 'ROLE_USER'}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
