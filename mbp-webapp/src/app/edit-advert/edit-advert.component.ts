import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TypeSeanceService} from '../services/TypeSeance.service';
import {AdvertService} from '../services/Advert.service';
import {Router, ActivatedRoute, Params} from '@angular/router';
import {Advert} from '../models/Advert.model';
import {HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TypeSeance} from '../models/TypeSeance.model';
import {CurrentUser} from '../models/CurrentUser.model';
import {tap} from 'rxjs/operators';
import {AdvertEntity} from '../models/AdvertEntity.model';
import {AdvertEdit} from '../models/AdvertEdit.model';

@Component({
  selector: 'app-edit-advert',
  templateUrl: './edit-advert.component.html',
  styleUrls: ['./edit-advert.component.scss']
})
export class EditAdvertComponent implements OnInit {

  loginForm: FormGroup;
  errorMessage: string;
  obsTypeSeance: Observable<TypeSeance[]>;
  isWait: boolean;
  currentUser: CurrentUser;
  preAid: string;
  currentAdvert: AdvertEntity;

  constructor(private formBuilder: FormBuilder, private typeSeanceService: TypeSeanceService, private advertService: AdvertService, private router: Router, private route: ActivatedRoute) { }


  ngOnInit(): void {
    this.preAid = this.router.url.split('/').pop();
    this.initForm();
    this.getAdvertInfos();
  }

  initForm() {
    this.loginForm = this.formBuilder.group({
      NomCreaAnnonce: ['', Validators.required],
      NiveauCreaAnnonce : ['', Validators.required],
      DescriptionCreaAnnonce: ['', Validators.required],
      DureeSeanceCreaAnnonce: ['', Validators.required],
      DateSeanceCreaAnnonce: ['', Validators.required],
      typeSeanceCreaAnnonce: ['', Validators.required]
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
      this.router.navigate(['./listAdverts']);});
  }

}