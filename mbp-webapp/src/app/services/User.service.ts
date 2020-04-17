import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/User.model';
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


}