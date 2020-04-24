import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {User} from '../models/User.model';
import {Banni} from '../models/Banni.model';
import { UserService } from '../services/User.service';
import { BanniService } from '../services/Banni.service';
import { AuthService } from '../services/auth.service';
import { CurrentUser } from '../models/CurrentUser.model';

@Component({
  selector: 'app-user-liste',
  templateUrl: './user-liste.component.html',
  styleUrls: ['./user-liste.component.scss']
})
export class UserListeComponent implements OnInit {

  obsListUser: Observable<User[]>;
  myUsername: string;

  constructor(private userService: UserService, private banniService: BanniService,private authService:AuthService  ) { 
    authService.currentUser.subscribe(user=>{this.initMyUserName(user)});
  }

  ngOnInit(): void {
    this.getAllUsers();
  }

  public getAllUsers(){
    this.obsListUser = this.userService.getAllUsers();
  }

  public deleteUser(user: User){
    this.banniService.addBanni(new Banni(user.username)).subscribe(banni =>{
      this.gereRetourDelete(banni);  
    });
    //gere le retour
  }

  public gereRetourDelete(banni: Banni){

  }

  public initMyUserName(currentuser: CurrentUser){
    (this.userService.getUser(currentuser.userId)).subscribe(user => {this.myUsername = user.username});
    console.log("test" + this.myUsername);
  }
  
}
