import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Observable } from 'rxjs';
import { CurrentUser } from '../models/CurrentUser.model';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  currentUser:CurrentUser;
  constructor(private authService:AuthService) { 
	authService.currentUser.subscribe(user=>this.currentUser=user);
  }

  ngOnInit(): void {
  }

  logout(){
	this.authService.logout();
  }
  

}
