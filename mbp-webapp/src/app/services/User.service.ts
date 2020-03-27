import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse} from '@angular/common/http';
import { User } from '../models/User.model';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';


@Injectable()
export class UserService {

    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };

    constructor(private http: HttpClient){ }

    private handleError(error: HttpErrorResponse) {
        if (error.error instanceof ErrorEvent) {
            console.error('An error occurred:', error.error.message);
        } else {
            console.error(`Backend returned code ${error.status}, ` +
                            `body was: ${error.error}`);
        }
        return throwError(
            'Something bad happened; please try again later.');
        };

    addUser(user: User): Observable<User> {
        return this.http.post<User>('localhost:8080/user', user, this.httpOptions)
        .pipe(
            catchError(this.handleError)
        );
    }


}