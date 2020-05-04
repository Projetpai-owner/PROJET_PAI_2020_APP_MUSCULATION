import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Banni } from '../models/Banni.model';

@Injectable()
export class BanniService {

   constructor(private http: HttpClient){ }

    getBanned(): Observable<Banni[]> {
        return this.http.get<Banni[]>('http://localhost:8080/getBannedUsers');
    }

}