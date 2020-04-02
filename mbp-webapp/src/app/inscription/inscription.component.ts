import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators} from '@angular/forms';
import { User } from '../models/User.model';
import { Salle } from '../models/Salle.model';
import { UserService} from '../services/User.service';
import { SalleService } from '../services/Salle.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss']
})
export class InscriptionComponent implements OnInit {

  errorMessage: string;
  inscriptionForm: FormGroup;
  obsSalles: Observable<Salle[]>;
  IsWait: boolean;

  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private salleService: SalleService,
              private router: Router) { }

  ngOnInit() {
    this.initForm();
  }

  initForm(){
    this.inscriptionForm = this.formBuilder.group({
      prenom: ['', Validators.required],
      nom: ['', Validators.required],
      bornDate: ['', Validators.required],
      sexe: ['', Validators.required],
      adresse: ['', Validators.required],
      sid: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern("^(?=.{7,}$)(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9]).*$")]]
    });
    this.getAllSalles();
  }

  getAllSalles(){
    this.obsSalles = this.salleService.getSalles();
  }

  onSubmitForm(){
    this.IsWait = true;
    const formValue = this.inscriptionForm.value;
    const newUser = new User(
      formValue['nom'],
      formValue['prenom'],
      formValue['bornDate'],
      formValue['sexe'],
      formValue['email'],
      formValue['password'],
      +formValue['sid'],
      formValue['adresse']
    )
    this.errorMessage = '';
    this.userService.addUser(newUser).subscribe(res => {
        this.IsWait = false;
        this.router.navigate(['/']);
      },
      (err: HttpErrorResponse) => {
        this.errorMessage = err.error.message;
      }
    );
  }
      
}
