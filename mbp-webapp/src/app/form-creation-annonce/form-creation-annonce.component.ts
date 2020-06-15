import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NavigationExtras, Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TypeSeance} from '../models/TypeSeance.model';
import {TypeSeanceService} from '../services/TypeSeance.service';
import {Advert} from '../models/Advert.model';
import {AdvertService} from '../services/Advert.service';
import {CurrentUser} from '../models/CurrentUser.model';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-form-creation-annonce',
  templateUrl: './form-creation-annonce.component.html',
  styleUrls: ['./form-creation-annonce.component.scss']
})
export class FormCreationAnnonceComponent implements OnInit {
  advertForm: FormGroup;
  errorMessage: string;
  obsTypeSeance: Observable<TypeSeance[]>;
  isWait: boolean;
  currentUser: CurrentUser;
  toDate = new Date();

  constructor(private formBuilder: FormBuilder, private typeSeanceService: TypeSeanceService, private advertService: AdvertService, private router: Router, private authService: AuthService) { }


  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.advertForm = this.formBuilder.group({
      NomCreaAnnonce: ['', [Validators.required, Validators.maxLength(50)]],
      NiveauCreaAnnonce : ['', Validators.required],
      DescriptionCreaAnnonce: ['', [Validators.required, Validators.maxLength(500)]],
      DureeSeanceCreaAnnonce: ['', Validators.required],
      DateSeanceCreaAnnonce: ['', Validators.required],
      typeSeanceCreaAnnonce: ['', Validators.required]
    });
    this.getAllTypeSeances();
  }

  getAllTypeSeances() {
    this.obsTypeSeance = this.typeSeanceService.getTypeSeance();
  }

  onSubmitForm() {
    this.isWait = true;
    const formValue = this.advertForm.value;

    this.currentUser = this.authService.currentUserValue;

    function transformTimeIntoNumber(value: number) {
      const tmp1 = +(value.toString().split(':')[0]) * 60;
      const tmp2 = +(value.toString().split(':')[1]);
      return tmp1 + tmp2;
    }

    const newAdvert = new Advert(
      +this.currentUser.userId,
      formValue.DescriptionCreaAnnonce,
      formValue.NiveauCreaAnnonce,
      transformTimeIntoNumber(formValue.DureeSeanceCreaAnnonce),
      formValue.NomCreaAnnonce,
      formValue.DateSeanceCreaAnnonce,
      +formValue.typeSeanceCreaAnnonce);
    this.errorMessage = '';
    this.advertService.createAdvert(newAdvert).subscribe(res => {
        this.isWait = false;
        const navigationExtras: NavigationExtras = {state: [{data: 'Votre annonce a été créé avec succès'}, {from: 'advert'}]};
        this.router.navigate(['/advertListProprio'], navigationExtras);
      },
      (err: HttpErrorResponse) => {
        this.isWait = false;
        this.errorMessage = err.error.message;
      }
    );
  }
}
