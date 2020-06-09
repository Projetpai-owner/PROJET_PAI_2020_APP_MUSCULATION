import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {AdvertService} from '../services/Advert.service';
import {AdvertItemList} from '../models/AdvertItemList.model';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TypeSeance } from '../models/TypeSeance.model';
import { TypeSeanceService } from '../services/TypeSeance.service';
import { SalleService } from '../services/Salle.service';
import { Salle } from '../models/Salle.model';
import { FormGroup, FormBuilder,FormControl} from '@angular/forms';
import { Advert } from '../models/Advert.model';
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

  @ViewChild('formFiltre') formFiltre:ElementRef;
  ItemsArray = [];
  toutesLesannonces = [];
  obsTypeSeance: Observable<TypeSeance[]>;
  obsSalles: Observable<Salle[]>;
  filtreAnnonceForm: FormGroup;
  zoneFiltreVisible: boolean;
  currentUser: CurrentUser;
  
  constructor(public advertService: AdvertService, 
              private typeSeanceService: TypeSeanceService,
              private salleService: SalleService,
              private router: Router,
              private formBuilder: FormBuilder,
              private authService: AuthService) { }

  ngOnInit(): void {
    this.initAnnonces();
    this.initSelect();
    this.initForm();
    this.initVisibility();
  }

  initAnnonces(): void{
    this.advertService.getAdverts().subscribe((res: AdvertItemList[]) => {
      console.log("recuperatino des annonces ");
      
      this.ItemsArray = res;
      console.log(this.ItemsArray[0]);
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

  showZoneFiltre(): void{
    this.zoneFiltreVisible = !this.zoneFiltreVisible;
  }

  submitForm(): void{
    this.ItemsArray = this.ItemsArray.filter(annonce => this.filtreAnnonce(annonce));
  }

  filtreAnnonce(advert : any) : boolean{
    const formFiltreValue = this.filtreAnnonceForm.value;
    if(formFiltreValue['date'] != "" && formFiltreValue['date'] != advert.date){
      return false;
    }
    if(formFiltreValue['dureeMin'] != "" && formFiltreValue['dureeMin'] > advert.duree){
      return false;
    }
    if(formFiltreValue['dureeMax'] != "" && formFiltreValue['dureeMax'] < advert.duree){
      return false;
    }
    if(formFiltreValue['niveau'] != "" && formFiltreValue['niveau'] != advert.niveauSeance){
      return false;
    }
    if(formFiltreValue['typeSeance'] != "" && formFiltreValue['typeSeance'] != advert.typeSeance){
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
