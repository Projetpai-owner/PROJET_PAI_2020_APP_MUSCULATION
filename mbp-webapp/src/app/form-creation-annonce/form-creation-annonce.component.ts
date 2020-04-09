import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TypeSeance} from '../models/TypeSeance.model';
import {TypeSeanceService} from '../services/TypeSeance.service';
import {Advert} from '../models/Advert.model';
import {AdvertService} from '../services/Advert.service';

@Component({
  selector: 'app-form-creation-annonce',
  templateUrl: './form-creation-annonce.component.html',
  styleUrls: ['./form-creation-annonce.component.scss']
})
export class FormCreationAnnonceComponent implements OnInit {
  loginForm: FormGroup
  errorMessage: string;
  obsTypeSeance: Observable<TypeSeance[]>;
  isWait: boolean;


  constructor(private formBuilder: FormBuilder, private typeSeanceService: TypeSeanceService, private advertService: AdvertService, private router: Router) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.loginForm = this.formBuilder.group({
      NomCreaAnnonce: ['', Validators.required],
      DescriptionCreaAnnonce: ['', Validators.required],
      DureeSeanceCreaAnnonce: ['', Validators.required],
      DateSeanceCreaAnnonce: ['', Validators.required]
    });
    this.getAllTypeSeances();
  }

  getAllTypeSeances(){
    this.obsTypeSeance = this.typeSeanceService.getSalles();
  }

  onSubmitForm() {
    this.isWait = true;
    const formValue = this.loginForm.value;
    const newAdvert = new Advert(
      formValue['DescriptionCreaAnnonce'],
      formValue['NiveauPratiquantCreaAnnonce'],
      formValue['DureeSeanceCreaAnnonce'],
      formValue['NomCreaAnnonce'],
      formValue['DateSeanceCreaAnnonce'],
      +formValue['idSeance']
    )
    this.errorMessage = '';
    this.advertService.createAdvert(newAdvert).subscribe(res => {
        this.isWait = false;
        this.router.navigate(['/']);
      },
      (err: HttpErrorResponse) => {
        this.errorMessage = err.error.message;
      }
    );
  }

}
