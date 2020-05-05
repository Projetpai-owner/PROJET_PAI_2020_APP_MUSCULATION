import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Advert } from '../models/Advert.model';
import { Observable } from 'rxjs';
import {TypeSeance} from '../models/TypeSeance.model';
import {AdvertItemList} from '../models/AdvertItemList.model';
import {User} from '../models/User.model';

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

  getAdverts(): Observable<AdvertItemList[]> {
    return this.http.get<AdvertItemList[]>('http://localhost:8080/getAllAdvertsItems');
  }

  deleteAdvertById( aid: number): void {
    this.http.delete('http://localhost:8080/deleteAdvert/' + aid).subscribe((s) => {
        console.log(s);
      });
  }

  getAdvertById( aid : number): Observable<Advert> {
    return this.http.get<Advert>('http://localhost:8080/getAdvertById/' + aid );
  }

  updateAdvert(advert: Advert): Observable<Advert> {
    return this.http.put<Advert>('http://localhost:8080/updateAdvert', advert, this.httpOptions);
  }

}
