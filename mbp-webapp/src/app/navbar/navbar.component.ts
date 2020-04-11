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
  constructor(private authService:AuthService,
              private router: Router) { 
	authService.currentUser.subscribe(user=>this.currentUser=user);
  }

  ngOnInit(): void {
  }

  logout(){
  this.authService.logout();
  this.router.navigate(['/']);

  }
  

}
