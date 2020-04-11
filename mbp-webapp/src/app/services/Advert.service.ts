import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Advert } from '../models/Advert.model';
import { Observable } from 'rxjs';
import {TypeSeance} from '../models/TypeSeance.model';

@Injectable()
export class AdvertService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private http: HttpClient) { }

  createAdvert(advert: Advert): Observable<Advert> {
    return this.http.post<Advert>('http://localhost:8080/createAdvert', advert, this.httpOptions);
  }

  getAdverts(): Observable<Advert[]> {
    return this.http.get<Advert[]>('http://localhost:8080/getAllAdverts');
  }


}
