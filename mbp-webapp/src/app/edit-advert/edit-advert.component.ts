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
  currentAdvert: Advert;

  constructor(private formBuilder: FormBuilder, private typeSeanceService: TypeSeanceService, private advertService: AdvertService, private router: Router, private route: ActivatedRoute) { }


  ngOnInit(): void {
    this.preAid = this.router.url.split('/').pop();
    this.initForm();
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
    this.getAdvertInfos();
  }

  getAdvertInfos(){
    this.advertService.getAdvertById(+this.preAid).pipe(
      tap(advert => this.loginForm.patchValue({
        description: advert.description,
        niveauPratique: advert.niveauPratique,
        dureeSeance: advert.dureeSeance,
        nom: advert.nom,
        dateSeance: advert.dateSeance
      }))).subscribe(res => {
      this.currentAdvert = res;
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

    const newAdvert = new Advert(
      formValue.DescriptionCreaAnnonce,
      formValue.NiveauCreaAnnonce,
      transformTimeIntoNumber(formValue.DureeSeanceCreaAnnonce),
      formValue.NomCreaAnnonce,
      formValue.DateSeanceCreaAnnonce,
      +formValue.typeSeanceCreaAnnonce
    );
    this.errorMessage = '';
    console.log(newAdvert);
    this.advertService.updateAdvert(newAdvert);
  }

}
