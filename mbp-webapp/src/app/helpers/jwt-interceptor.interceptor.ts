import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse } from '@angular/common/http';
import { Observable, BehaviorSubject, throwError } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { catchError } from 'rxjs/operators';
import { switchMap } from 'rxjs/internal/operators/switchMap';
import { filter } from 'rxjs/internal/operators/filter';
import { take } from 'rxjs/operators';


@Injectable()
export class JwtInterceptor implements HttpInterceptor {
	constructor(private authService: AuthService) { }

	intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
		// add authorization header with jwt token if available
		if (!request.url.includes("/resetPasswordWithToken")&&!request.url.includes("/isValidPasswordToken")&&!request.url.includes("/resetPassword")
		&&!request.url.includes("/refresh") && !request.url.includes("/login")&& !request.url.includes("/getAllSalles")){
			let currentUser = this.authService.currentUserValue;
			if (currentUser && currentUser.accessToken) {
				request = this.addToken(request, currentUser.accessToken);
			}
			
			return next.handle(request).pipe(catchError(error => {
				if (error instanceof HttpErrorResponse && error.status === 403) {
					return this.handle403Error(request, next);
				} else {
					return throwError(error);
				}
			}));
		}else{
			return next.handle(request);
		}
	}

	private addToken(request: HttpRequest<any>, token: string) {
		return request.clone({
			setHeaders: {
				'Authorization': `Bearer ${token}`
			}
		});
	}

	private isRefreshing = false;
	private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

	private handle403Error(request: HttpRequest<any>, next: HttpHandler) {
		if (!this.isRefreshing) {
			this.isRefreshing = true;
			this.refreshTokenSubject.next(null);
			return this.authService.refresh().pipe(
				switchMap((object: any) => {
					this.authService.currentUserValue.accessToken=object.jwt
					localStorage.setItem('currentUser', JSON.stringify(this.authService.currentUserValue));
					this.isRefreshing = false;
					this.refreshTokenSubject.next(object.jwt);
					return next.handle(this.addToken(request, object.jwt));
				}));

		} else {
			return this.refreshTokenSubject.pipe(
				filter(jwt => jwt != null),
				take(1),
				switchMap(jwt => {
					return next.handle(this.addToken(request, jwt));
				}));
		}
	}

	
}

