import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Support } from '../models/Support.model';
import {SupportBody} from '../models/SupportBody.model';


@Injectable()
export class SupportService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private http: HttpClient) { }

  addTicket(support: Support): Observable<Support> {
	  return this.http.post<Support>('http://localhost:8080/createTicket', support, this.httpOptions);
  }

  getAllTickets(): Observable<SupportBody[]> {
    return this.http.get<SupportBody[]>('http://localhost:8080/getAllTickets');
  }

  deleteTicket(suid: number): Observable<Support> {
    return this.http.delete<Support>('http://localhost:8080/deleteTicket/' + suid);
  }

}
