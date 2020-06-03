import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {User} from '../models/User.model';
import {Banni} from '../models/Banni.model';
import { UserService } from '../services/User.service';
import { BanniService } from '../services/Banni.service';
import { AuthService } from '../services/auth.service';
import { CurrentUser } from '../models/CurrentUser.model';
import { ConfirmAlertService } from '../services/confirm-alert.service';
import { ClassicAlertService } from '../services/classic-alert.service';

@Component({
  selector: 'app-user-liste',
  templateUrl: './user-liste.component.html',
  styleUrls: ['./user-liste.component.scss']
})
export class UserListeComponent implements OnInit {

  obsListUser: Observable<User[]>;
  myUsername: string;

  constructor(private userService: UserService, private banniService: BanniService,private authService:AuthService,private confirmAlertService:  ConfirmAlertService
    ,private classicAlertService: ClassicAlertService) { 
    authService.currentUser.subscribe(user=>{this.initMyUserName(user)});
  }

  ngOnInit(): void {
    this.getAllUsers();
  }

  public getAllUsers(){
    this.obsListUser = this.userService.getAllUsers();
  }

  public confirmDeleteUser(user: User){
    this.confirmAlertService.confirm("Confirmez votre action : ","Voulez-vous vraiment bannir l'utilisateur "+user.username+" ?","Confirmer","Annuler","lg")
     .then((confirmed) => {if(confirmed){this.deleteUser(user);}else{this.actionAnnule(user);}})
      .catch(() => this.actionAnnule(user));
  }

  public deleteUser(user: User){
    this.banniService.addBanni(new Banni(user.username)).subscribe(banni =>{
      this.gereRetourDelete(banni);
    });
  }

  public gereRetourDelete(banni: Banni){
    this.ngOnInit();
    this.classicAlertService.alert("Utilisateur banni","L'utilisateur "+banni.email+" a été banni","OK","sm")
  }

  public actionAnnule(user: User){
    this.classicAlertService.alert("Action annulée","L'utilisateur " +user.username+" n'a pas été banni","OK","sm");
  }
  public initMyUserName(currentuser: CurrentUser){
    (this.userService.getUser(currentuser.userId)).subscribe(user => {this.myUsername = user.username});
    console.log("test" + this.myUsername);
  }



}
