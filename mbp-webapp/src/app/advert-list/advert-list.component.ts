import { Component, OnInit } from '@angular/core';
import {AdvertService} from '../services/Advert.service';
import {AdvertItemList} from '../models/AdvertItemList.model';
import { Router } from '@angular/router';
import {ConfirmAlertService} from "../services/confirm-alert.service";
import {User} from "../models/User.model";
import {ClassicAlertService} from "../services/classic-alert.service";
import {Banni} from "../models/Banni.model";

@Component({
  selector: 'app-advert-list',
  templateUrl: './advert-list.component.html',
  styleUrls: ['./advert-list.component.scss']
})
export class AdvertListComponent implements OnInit {

  ItemsArray = [];

  constructor(public advertService: AdvertService, private confirmAlertService: ConfirmAlertService,
              private classicAlertService: ClassicAlertService, private router: Router) { }

  ngOnInit(): void {
    this.advertService.getAdverts().subscribe((res: AdvertItemList[]) => {
      this.ItemsArray = res;
    });
  }

  public confirmDeleteAdvert(item: AdvertItemList){
    this.confirmAlertService.confirm("Confirmez votre action : ","Voulez-vous vraiment supprimer l'annonce '"+ item.nom+"' ?","Confirmer","Annuler","lg")
      .then((confirmed) => {if(confirmed){this.deleteAdvertById(item);}else{this.actionAnnule(item);}})
      .catch(() => this.actionAnnule(item));
  }

  deleteAdvertById(item: AdvertItemList) {
    this.advertService.deleteAdvertById(item.aid).subscribe(res => {
      this.gereRetourDelete(item);
    });
  }

  public gereRetourDelete(item: AdvertItemList){
    this.ngOnInit();
    this.classicAlertService.alert("Annonce supprimé","L'annonce '"+item.nom+" a été supprimé","OK","sm")
  }

  public actionAnnule(item: AdvertItemList){
    this.classicAlertService.alert("Action annulée","L'annonce n'a pas été supprimé","OK","sm");
  }
}
