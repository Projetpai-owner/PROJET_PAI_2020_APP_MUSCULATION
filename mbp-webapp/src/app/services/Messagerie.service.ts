import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageGeneral } from '../models/MessageGeneral.model';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Injectable()
export class MessagerieService {

    constructor(private http: HttpClient){ }
    
    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
        })
    };

    sendMessageGeneral(messageGeneral : MessageGeneral) {
        this.http.post('http://localhost:8080/sendMessageGeneral',messageGeneral,this.httpOptions);
    }
}