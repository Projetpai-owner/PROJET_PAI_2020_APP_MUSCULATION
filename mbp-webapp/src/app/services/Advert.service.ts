import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Advert } from '../models/Advert.model';
import {Observable, throwError} from 'rxjs';
import {TypeSeance} from '../models/TypeSeance.model';
import {AdvertItemList} from '../models/AdvertItemList.model';
import {User} from '../models/User.model';
import { AdvertEntity } from '../models/AdvertEntity.model';
import {AdvertEdit} from '../models/AdvertEdit.model';
import {catchError} from 'rxjs/operators';
import { AddParticipant } from '../models/AddParticipant.model';
import { ProprietaireAnnonce } from '../models/ProprietaireAnnonce.model';

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

  getAdvertById( aid: number): Observable<AdvertEntity>  {
    return this.http.get<AdvertEntity>('http://localhost:8080/getAdvertById/' + aid );
  }

  updateAdvert(advert: AdvertEdit): Observable<AdvertEdit> {
    return this.http.put<AdvertEdit>('http://localhost:8080/updateAdvert', advert, this.httpOptions);
  }

  addParticipant(body: AddParticipant): Observable<AddParticipant> {
    console.log(body.aid);
    console.log(body.uid);
    return this.http.post<AddParticipant>('http://localhost:8080/addParticipant', body, this.httpOptions);
  }

  getProprietaireByAid(aid: number): Observable<ProprietaireAnnonce> {
      return this.http.get<ProprietaireAnnonce>('http://localhost:8080/getProprioByAid/' + aid, this.httpOptions);
  }

  isProprietaireAnnonce(uid: number, aid: number): boolean {
    let userProp;
    const proprioAnnonce = this.getProprietaireByAid(aid).subscribe( proprio => {
      userProp  = proprio.pidProprietaire;
    });
    return userProp === uid;
  }

}
