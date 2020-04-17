import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {User} from '../models/User.model';
import { UserService } from '../services/User.service';

@Component({
  selector: 'app-user-liste',
  templateUrl: './user-liste.component.html',
  styleUrls: ['./user-liste.component.scss']
})
export class UserListeComponent implements OnInit {

  obsListUser: Observable<User[]>;

  constructor(private userService: UserService ) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  public getAllUsers(){
    this.obsListUser = this.userService.getAllUsers();
  }

  
}
