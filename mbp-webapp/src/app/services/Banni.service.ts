import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Banni } from '../models/Banni.model';

@Injectable()
export class BanniService {

   constructor(private http: HttpClient){ }

    getBanned(): Observable<Banni[]> {
        return this.http.get<Banni[]>('http://localhost:8080/getBannedUsers');
    }


    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
        })
    };


    addBanni(banni: Banni): Observable<Banni> {
        return this.http.post<Banni>('http://localhost:8080/addBanni', banni, this.httpOptions);
    }
}