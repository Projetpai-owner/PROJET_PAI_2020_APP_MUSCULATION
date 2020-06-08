import { Component, OnInit } from '@angular/core';
import {AdvertService} from '../services/Advert.service';
import {AdvertItemList} from '../models/AdvertItemList.model';
import { Router } from '@angular/router';
import {CurrentUser} from '../models/CurrentUser.model';
import {UserService} from '../services/User.service';
import {AuthService} from '../services/auth.service';
import {AddParticipant} from '../models/AddParticipant.model';

@Component({
  selector: 'app-advert-list',
  templateUrl: './advert-list.component.html',
  styleUrls: ['./advert-list.component.scss']
})
export class AdvertListComponent implements OnInit {

  ItemsArray = [];
  currentUser: CurrentUser;

  constructor(public advertService: AdvertService, private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    this.advertService.getAdverts().subscribe((res: AdvertItemList[]) => {
      this.ItemsArray = res;
    });
  }

  deleteAdvertById(aid: number){
    this.advertService.deleteAdvertById(aid);
    this.router.navigate(['/']);
  }

  addParticipation(aid: number){
    this.currentUser = this.authService.currentUserValue;
    const newParticipation = new AddParticipant(+this.currentUser.userId, aid);
    this.advertService.addParticipant(newParticipation).subscribe();
  }

  isProprioAnnonce(aid: number){
    this.currentUser = this.authService.currentUserValue;
    return this.advertService.isProprietaireAnnonce(+this.currentUser.userId,aid);
  }
}
