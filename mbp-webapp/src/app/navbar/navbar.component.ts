import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { CurrentUser } from '../models/CurrentUser.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  currentUser:CurrentUser;
  isAdmin: boolean;
  constructor(private authService:AuthService,private route:Router) { 
    authService.currentUser.subscribe(user=>{this.currentUser=user;if(user!= null){this.isAdmin = (user.role == "ROLE_ADMIN")}});
  }

  ngOnInit(): void {
  }

  logout(){

	this.authService.logout();
	this.route.navigate(['/'])
  }

}
