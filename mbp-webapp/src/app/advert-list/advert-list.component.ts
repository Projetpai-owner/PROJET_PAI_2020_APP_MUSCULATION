import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {AdvertService} from '../services/Advert.service';
import {AdvertItemList} from '../models/AdvertItemList.model';
import { Router } from '@angular/router';
import {ConfirmAlertService} from "../services/confirm-alert.service";
import {ClassicAlertService} from "../services/classic-alert.service";
import { Observable } from 'rxjs';
import { TypeSeance } from '../models/TypeSeance.model';
import { TypeSeanceService } from '../services/TypeSeance.service';
import { SalleService } from '../services/Salle.service';
import { Salle } from '../models/Salle.model';
import { FormGroup, FormBuilder,FormControl} from '@angular/forms';
import {CurrentUser} from '../models/CurrentUser.model';
import {AuthService} from '../services/auth.service';
import {AddParticipant} from '../models/AddParticipant.model';

@Component({
  selector: 'app-advert-list',
  templateUrl: './advert-list.component.html',
  styleUrls: ['./advert-list.component.scss']
})

export class AdvertListComponent implements OnInit {

  @ViewChild('formFiltre') formFiltre:ElementRef;
  ItemsArray = [];
  toutesLesannonces = [];
  obsTypeSeance: Observable<TypeSeance[]>;
  obsSalles: Observable<Salle[]>;
  filtreAnnonceForm: FormGroup;
  zoneFiltreVisible: boolean;
  currentUser: CurrentUser;
  participate_success: boolean;
  nomAnnonce: string;
  aidList: number[] = [];

  constructor(public advertService: AdvertService, private confirmAlertService: ConfirmAlertService,
              private classicAlertService: ClassicAlertService, private router: Router,
              private typeSeanceService: TypeSeanceService, private salleService: SalleService,
              private formBuilder: FormBuilder, private authService: AuthService) { }

  ngOnInit(): void {
    this.initAnnonces();
    this.initSelect();
    this.initForm();
    this.initVisibility();
    this.initParticipation();
  }

  initAnnonces(): void{
    this.currentUser = this.authService.currentUserValue;
    this.advertService.getAdvertsWhereNotProprietaire(+this.currentUser.userId).subscribe((res: AdvertItemList[]) => {
      this.ItemsArray = res;
      this.toutesLesannonces = res;
    });
  }

  initSelect(): void {
    this.obsTypeSeance = this.typeSeanceService.getTypeSeance();
    this.obsSalles = this.salleService.getSalles();
  }

  initForm(): void{
    this.filtreAnnonceForm = new FormGroup({
      date : new FormControl(''),
      dureeMin : new FormControl(''),
      dureeMax : new FormControl(''),
      niveau : new FormControl(''),
      typeSeance : new FormControl(''),
      salle : new FormControl(''),
      sex : new FormControl('')
    });
  }

  initVisibility(): void{
    this.zoneFiltreVisible = false;
  }

  initParticipation() : void {
    this.advertService.getParticipationsByUid(+this.currentUser.userId).subscribe((res : AdvertItemList[]) => {
      for(let advert of res) {
        this.aidList.push(advert.aid);
      }
    });
  }

  showZoneFiltre(): void{
    this.zoneFiltreVisible = !this.zoneFiltreVisible;
  }

  submitForm(): void{
    this.ItemsArray = this.toutesLesannonces;
    this.ItemsArray = this.ItemsArray.filter(annonce => this.filtreAnnonce(annonce));
  }

  filtreAnnonce(advert : any) : boolean{
    const formFiltreValue = this.filtreAnnonceForm.value;
    if(formFiltreValue['date'] != "" && formFiltreValue['date'] != advert.dateSeance){
      return false;
    }
    if(formFiltreValue['dureeMin'] != ""){
      var duremin = formFiltreValue['dureeMin'];
      var heur = parseInt(duremin.substring(0,2));
      var min = parseInt(duremin.substring(3,5));
      duremin = heur*60 + min;
      if(duremin > parseInt(advert.duree)){
        return false;
      }
    }
    if(formFiltreValue['dureeMax'] != "" ){
      var duremax = formFiltreValue['dureeMax'];
      var heure = parseInt(duremax.substring(0,2));
      var mine = parseInt(duremax.substring(3,5));
      duremax = heure*60 + mine;
      if(duremax < parseInt(advert.duree)){
        return false;
      }
    }
    if(formFiltreValue['niveau'] != "" && formFiltreValue['niveau'] != advert.niveau){
      return false;
    }
    if(formFiltreValue['typeSeance'] != "" && formFiltreValue['typeSeance'] != advert.type){
      return false;
    }
    if(formFiltreValue['sex'] != "" && formFiltreValue['sex'] != advert.sexAnnonceur){
      return false;
    }
    if(formFiltreValue['salle'] != "" && formFiltreValue['salle'] != advert.salleAnnonceur ){
      return false;
    }

    return true;
  }

  clearForm(): void{
    for(let i=0; i < this.formFiltre.nativeElement.elements.length-2;i++){
      this.formFiltre.nativeElement.elements[i].value = '';
    }
    this.ItemsArray = this.toutesLesannonces;
  }

  addParticipation(aid: number, nom: string){
    this.currentUser = this.authService.currentUserValue;
    const newParticipation = new AddParticipant(+this.currentUser.userId, aid);
    this.advertService.addParticipant(newParticipation).subscribe(res => {
      this.participate_success = true;
      this.nomAnnonce = nom;
      setTimeout(() => this.participate_success = false, 6000);
      this.ngOnInit();
    });
  }

}
