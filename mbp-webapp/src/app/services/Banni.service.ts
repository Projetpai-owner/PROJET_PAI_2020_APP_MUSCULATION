import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Banni } from '../models/Banni.model';
import { Observable } from 'rxjs';

@Injectable()
export class BanniService {

    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
        })
    };

    constructor(private http: HttpClient) { }

    addBanni(banni: Banni): Observable<Banni> {
        return this.http.post<Banni>('http://localhost:8080/addBanni', banni, this.httpOptions);
    }
}