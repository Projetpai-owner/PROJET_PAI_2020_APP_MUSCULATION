import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageGeneral } from '../models/MessageGeneral.model';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class MessagerieService {

    constructor(private http: HttpClient){ }
    
    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
        })
    };

    sendMessageGeneral(messageGeneral : MessageGeneral) : Observable<MessageGeneral>{
        return this.http.post<MessageGeneral>('http://localhost:8080/sendMessageGeneral',messageGeneral,this.httpOptions);
    }
}