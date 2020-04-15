import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CurrentUser } from '../models/CurrentUser.model';
import { HashService } from './hash.service';
import { map } from 'rxjs/operators';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class AuthService {
	private currentUserSubject: BehaviorSubject<CurrentUser>;
	public currentUser: Observable<CurrentUser>;
	constructor(private http: HttpClient, private hashService: HashService) {
		this.currentUserSubject = new BehaviorSubject<CurrentUser>(JSON.parse(localStorage.getItem('currentUser')));
		this.currentUser = this.currentUserSubject.asObservable();
	}

	httpOptions = {
		headers: new HttpHeaders({
			'Content-Type': 'application/json',
		})
	};


	public get currentUserValue(): CurrentUser {
		return this.currentUserSubject.value;
	}



	login(username: string, passwordNoneHashed: string) {
		const password = this.hashService.hashPassword(passwordNoneHashed)

		return this.http.post<CurrentUser>('http://localhost:8080/login', { username, password }, this.httpOptions)
			.pipe(map(user => {
				if (user && user.accessToken&&user.refreshToken) {
					localStorage.setItem('currentUser', JSON.stringify(user));
					this.currentUserSubject.next(user);
				}

				return user;
			}));


	}
	
	refresh(){
		let url='http://localhost:8080/refresh/'+this.currentUserValue.refreshToken
		return this.http.post<string>(url,this.httpOptions)
	}


	logout() {
		let url='http://localhost:8080/revoke/'+this.currentUserValue.refreshToken
		this.http.delete(url,this.httpOptions).subscribe(response=>{
			localStorage.removeItem('currentUser');
			this.currentUserSubject.next(null);
		});
	
	}


}

