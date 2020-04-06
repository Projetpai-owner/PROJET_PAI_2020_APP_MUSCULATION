import { Injectable } from '@angular/core';
import {Md5} from 'ts-md5/dist/md5';

@Injectable({
  providedIn: 'root'
})
export class HashService {

  constructor() { }

  hashPassword(password:string):string{
	return new Md5().appendStr(password).end().toString()
  }

}
