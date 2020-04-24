import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/User.model';
import { UserBody } from '../models/UserBody.model';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
        })
    };

    constructor(private http: HttpClient){ }

    addUser(user: User): Observable<User> {
        return this.http.post<User>('http://localhost:8080/user', user, this.httpOptions);
    }

    getAllUsers(): Observable<User[]> {
        return this.http.get<User[]>('http://localhost:8080/getAllUsers');
    }
    getUser(userId: string): Observable<UserBody> {
        return this.http.get<UserBody>('http://localhost:8080/getUser' + '?userId=' + userId);
    }

    updateUser(user: User): Observable<User>{
        return this.http.put<User>('http://localhost:8080/updateUser', user, this.httpOptions);
    }



}