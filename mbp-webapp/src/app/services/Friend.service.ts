import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Ami } from '../models/Ami.model';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable()
export class FriendService {

    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
        })
    };


	constructor(private http: HttpClient, private authService: AuthService) { }


	getMyFriends(): Observable<Ami[]> {
		return this.http.get<Ami[]>('http://localhost:8080/getAllFriends/' + this.authService.currentUserValue.userId, this.httpOptions);
	}

	deleteByPid(userId,pid) :Observable<string>{
		return this.http.delete<string>('http://localhost:8080/deleteFriend/' + userId+'/'+pid,this.httpOptions);
	}


}