import { Component, OnInit } from '@angular/core';
import {AdvertService} from '../services/Advert.service';
import {AdvertItemList} from '../models/AdvertItemList.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-advert-list',
  templateUrl: './advert-list.component.html',
  styleUrls: ['./advert-list.component.scss']
})
export class AdvertListComponent implements OnInit {

  ItemsArray = [];

  constructor(public advertService: AdvertService, private router: Router) { }

  ngOnInit(): void {
    this.advertService.getAdverts().subscribe((res: AdvertItemList[]) => {
      this.ItemsArray = res;
    });
  }

  deleteAdvertById(aid: number){
    this.advertService.deleteAdvertById(aid);
    this.router.navigate(['/']);
  }
}
