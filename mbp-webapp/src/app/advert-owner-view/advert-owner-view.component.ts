import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {TypeSeance} from '../models/TypeSeance.model';
import {CurrentUser} from '../models/CurrentUser.model';
import {AdvertEntity} from '../models/AdvertEntity.model';
import {TypeSeanceService} from '../services/TypeSeance.service';
import {AdvertService} from '../services/Advert.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AdvertEdit} from '../models/AdvertEdit.model';
import {UserBodyPid} from '../models/UserBodyPid.model';
import {ClassicAlertService} from "../services/classic-alert.service";
import {ConfirmAlertService} from "../services/confirm-alert.service";

@Component({
  selector: 'app-advert-owner-view',
  templateUrl: './advert-owner-view.component.html',
  styleUrls: ['./advert-owner-view.component.scss']
})
export class AdvertOwnerViewComponent implements OnInit {

  loginForm: FormGroup;
  errorMessage: string;
  obsTypeSeance: Observable<TypeSeance[]>;
  isWait: boolean;
  currentUser: CurrentUser;
  preAid: string;
  currentAdvert: AdvertEntity;

  constructor(private formBuilder: FormBuilder, private typeSeanceService: TypeSeanceService,
              private advertService: AdvertService, private router: Router, private route: ActivatedRoute,
              private classicAlertService: ClassicAlertService, private confirmAlertService: ConfirmAlertService) { }

  ItemsArray = [];

  ngOnInit(): void {
    this.preAid = this.router.url.split('/').pop();
    this.initForm();
    this.getAdvertInfos();
    this.advertService.getParticipationsByAid(+this.preAid).subscribe((res: UserBodyPid[]) => {
        this.ItemsArray = res;
      }
    );
  }

  initForm() {
    this.loginForm = this.formBuilder.group({
      NomCreaAnnonce: [{value: '', disabled: true}, Validators.required],
      NiveauCreaAnnonce : [{value: '', disabled: true}, Validators.required],
      DescriptionCreaAnnonce: [{value: '', disabled: true}, Validators.required],
      DureeSeanceCreaAnnonce: [{value: '', disabled: true}, Validators.required],
      DateSeanceCreaAnnonce: [{value: '', disabled: true}, Validators.required],
      typeSeanceCreaAnnonce: [{value: '', disabled: true}, Validators.required]
    });
    this.getAllTypeSeances();
  }

  pad(num: number, size: number): string {
    let s = num + '';
    while (s.length < size) {
      s = '0' + s;
    }
    return s;
  }

  transformTimeIntoNumber(value: number) {
    const hours = Math.floor(value / 60);
    const minutes = value % 60;
    return this.pad(hours, 2) + ':' + this.pad(minutes, 2);
  }

  getAdvertInfos(){
    this.advertService.getAdvertById(+this.preAid).subscribe(res => {
      this.currentAdvert = res;
      this.loginForm.controls['DescriptionCreaAnnonce'].setValue(this.currentAdvert.description);
      this.loginForm.controls['NomCreaAnnonce'].setValue(this.currentAdvert.nom);
      this.loginForm.controls['NiveauCreaAnnonce'].setValue(this.currentAdvert.niveauPratique);
      this.loginForm.controls['DureeSeanceCreaAnnonce'].setValue(this.transformTimeIntoNumber(this.currentAdvert.dureeSeance));
      this.loginForm.controls['DateSeanceCreaAnnonce'].setValue(this.currentAdvert.dateSeance);
      this.loginForm.controls['typeSeanceCreaAnnonce'].setValue(this.currentAdvert.idSeance.idSeance);
    });
  }

  getAllTypeSeances() {
    this.obsTypeSeance = this.typeSeanceService.getTypeSeance();
  }

  onSubmitForm() {
    this.isWait = true;
    const formValue = this.loginForm.value;

    function transformTimeIntoNumber(value: number) {
      const tmp1 = +(value.toString().split(':')[0]) * 60;
      const tmp2 = +(value.toString().split(':')[1]);
      return tmp1 + tmp2;
    }

    const newAdvert = new AdvertEdit(
      formValue.DescriptionCreaAnnonce,
      formValue.NiveauCreaAnnonce,
      transformTimeIntoNumber(formValue.DureeSeanceCreaAnnonce),
      formValue.NomCreaAnnonce,
      formValue.DateSeanceCreaAnnonce,
      +formValue.typeSeanceCreaAnnonce,
      +this.preAid
    );
    this.errorMessage = '';
    this.advertService.updateAdvert(newAdvert).subscribe(response =>{
      this.router.navigate(['./advertListProprio']);});
  }

  public confirmDeleteParticipant(item: UserBodyPid){
    this.confirmAlertService.confirm("Confirmez votre action : ","Voulez-vous vraiment supprimer le participant '"+ item.username+"' ?","Confirmer","Annuler","lg")
      .then((confirmed) => {if(confirmed){this.supprimerParticipant(item);}else{this.actionAnnule(item);}})
      .catch(() => this.actionAnnule(item));
  }

  public gereRetourDelete(item: UserBodyPid){
    this.ngOnInit();
    this.classicAlertService.alert("Annonce supprimé","Le participant '"+item.username+" a été supprimé","OK","sm")
  }

  public actionAnnule(item: UserBodyPid) {
    this.classicAlertService.alert("Action annulée", "Le participant n'a pas été supprimé", "OK", "sm");
  }

  supprimerParticipant(item: UserBodyPid){
    this.advertService.supprimerParticipation(item.pid, +this.preAid).subscribe(res => {
      this.gereRetourDelete(item);
    });
  }
}
