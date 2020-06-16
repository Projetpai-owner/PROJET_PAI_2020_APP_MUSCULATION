import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Advert } from '../models/Advert.model';
import {Observable} from 'rxjs';
import {AdvertItemList} from '../models/AdvertItemList.model';
import { AdvertEntity } from '../models/AdvertEntity.model';
import {AdvertEdit} from '../models/AdvertEdit.model';
import { AddParticipant } from '../models/AddParticipant.model';
import {UserBody} from '../models/UserBody.model';
import {ProprietaireAnnonce} from "../models/ProprietaireAnnonce.model";

@Injectable()
export class AdvertService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private http: HttpClient) { }

  getAdverts(): Observable<AdvertItemList[]> {
    return this.http.get<AdvertItemList[]>('http://localhost:8080/getAllAdvertsItems');
  }

  createAdvert(advert: Advert): Observable<Advert> {
    return this.http.post<Advert>('http://localhost:8080/createAdvert', advert, this.httpOptions);
  }

  deleteAdvertById( aid: number): Observable<Advert> {
    return this.http.delete<Advert>('http://localhost:8080/deleteAdvert/' + aid);
  }

  getAdvertsWhereNotProprietaire(pid: number): Observable<AdvertItemList[]> {
    return this.http.get<AdvertItemList[]>('http://localhost:8080/getAllWhereNotProprietaire/' + pid, this.httpOptions);
  }

  getAdvertsWhereProprietaire(pid: number): Observable<AdvertItemList[]> {
    return this.http.get<AdvertItemList[]>('http://localhost:8080/getAllWhereProprietaire/' + pid, this.httpOptions);
  }

  getAdvertById( aid: number): Observable<AdvertEntity>  {
    return this.http.get<AdvertEntity>('http://localhost:8080/getAdvertById/' + aid );
  }

  getProprietaireByAid(aid: number): Observable<ProprietaireAnnonce> {
    return this.http.get<ProprietaireAnnonce>('http://localhost:8080/getProprioByAid/' + aid, this.httpOptions);
  }

  updateAdvert(advert: AdvertEdit): Observable<AdvertEdit> {
    return this.http.put<AdvertEdit>('http://localhost:8080/updateAdvert', advert, this.httpOptions);
  }

  addParticipant(body: AddParticipant): Observable<AddParticipant> {
    return this.http.post<AddParticipant>('http://localhost:8080/addParticipant', body, this.httpOptions);
  }

  getParticipationsByAid(aid: number): Observable<UserBody[]> {
    return this.http.get<UserBody[]>('http://localhost:8080/getParticipationsForAnnonce/'+aid, this.httpOptions);
  }

  getParticipationsByUid(uid: number): Observable<AdvertItemList[]> {
    return this.http.get<AdvertItemList[]>('http://localhost:8080/getParticipationsForUser/' + uid, this.httpOptions);
  }

  supprimerParticipation(pid: number, aid: number): Observable<AddParticipant> {
    return this.http.delete<AddParticipant>('http://localhost:8080/deleteParticipation/' + aid + '/' + pid);
  }

}
