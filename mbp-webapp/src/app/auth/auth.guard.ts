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
		if (currentUser && (expectedRole=='NONE'||expectedRole==currentUser.role)) {
			return true;
		}
		this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
		return false;
	}
}