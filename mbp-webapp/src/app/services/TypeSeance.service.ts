import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TypeSeance } from '../models/TypeSeance.model';
import { Observable } from 'rxjs';

@Injectable()
export class TypeSeanceService {

  constructor(private http: HttpClient){ }

  getTypeSeance(): Observable<TypeSeance[]> {
    return this.http.get<TypeSeance[]>('http://localhost:8080/getAllTypeSeance');
  }

}
