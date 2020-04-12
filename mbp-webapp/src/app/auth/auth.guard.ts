import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
	providedIn: 'root'
})
export class AuthGuard implements CanActivate {

	constructor(
		private router: Router,
		private authenticationService: AuthService
	) { }

	canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
		const currentUser = this.authenticationService.currentUserValue;
		const expectedRole = route.data.expectedRole;
		if ((currentUser && expectedRole==currentUser.role)||(!currentUser&&expectedRole=="NONE")) {
			return true;
		}
		if(!currentUser){
			this.router.navigate(['signIn'], { queryParams: { returnUrl: state.url } });
		}else{
			this.router.navigate(['/'], { queryParams: { returnUrl: state.url } });

		}
		return false;
	}
}
