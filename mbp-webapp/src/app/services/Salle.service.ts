import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Salle } from '../models/Salle.model';
import { Observable } from 'rxjs';

@Injectable()
export class SalleService {

    constructor(private http: HttpClient){ }

    getSalles(): Observable<Salle[]> {
        return this.http.get<Salle[]>('http://localhost:8080/getAllSalles');
    }

}