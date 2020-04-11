import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { CurrentUser } from '../models/CurrentUser.model';
import { UserService } from '../services/User.service';
import { User } from '../models/User.model';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent implements OnInit {

  currentUser: CurrentUser;
  user: User;

  constructor(private authService: AuthService,
              private userService: UserService) { }

  ngOnInit(): void {
    this.getUserInfos();
  }

  getUserInfos(){
    this.currentUser = this.authService.currentUserValue;
    console.log(this.currentUser);
    this.userService.getUser(this.currentUser.userId)
      .subscribe(res => {
        this.user = res;
        console.log(res);
      });
  }

}
