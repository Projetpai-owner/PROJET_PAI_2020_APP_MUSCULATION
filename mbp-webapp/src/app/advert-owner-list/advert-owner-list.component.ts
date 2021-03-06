import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {CurrentUser} from '../models/CurrentUser.model';
import {AdvertService} from '../services/Advert.service';
import {Router} from '@angular/router';
import {AuthService} from '../services/auth.service';
import {AdvertItemList} from '../models/AdvertItemList.model';
import {Observable} from 'rxjs';
import {TypeSeance} from '../models/TypeSeance.model';
import {Salle} from '../models/Salle.model';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {TypeSeanceService} from '../services/TypeSeance.service';
import {SalleService} from '../services/Salle.service';
import {ConfirmAlertService} from "../services/confirm-alert.service";
import {ClassicAlertService} from "../services/classic-alert.service";

@Component({
  selector: 'app-advert-owner-list',
  templateUrl: './advert-owner-list.component.html',
  styleUrls: ['./advert-owner-list.component.scss']
})
export class AdvertOwnerListComponent implements OnInit {

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
              private authService: AuthService,
              private confirmAlertService: ConfirmAlertService,
              private classicAlertService: ClassicAlertService) { }

  ngOnInit(): void {
    this.initAnnonces();
    this.initSelect();
    this.initForm();
    this.initVisibility();
  }

  initAnnonces(): void{
    this.currentUser = this.authService.currentUserValue;
    this.advertService.getAdvertsWhereProprietaire(+this.currentUser.userId).subscribe((res: AdvertItemList[]) => {
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

  filtreAnnonce(advert : any) : boolean{
    const formFiltreValue = this.filtreAnnonceForm.value;
    if(formFiltreValue['date'] != "" && formFiltreValue['date'] != advert.date){
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
    if(formFiltreValue['niveau'] != "" && formFiltreValue['niveau'] != advert.niveauSeance){
      return false;
    }
    if(formFiltreValue['typeSeance'] != "" && formFiltreValue['typeSeance'] != advert.typeSeance){
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

  public actionAnnule(item: AdvertItemList) {
    this.classicAlertService.alert("Action annulée", "L'annonce n'a pas été supprimé", "OK", "sm");
  }

}
