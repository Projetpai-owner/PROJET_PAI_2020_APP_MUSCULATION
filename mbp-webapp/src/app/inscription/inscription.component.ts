import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import { User } from '../models/User.model';
import { UserService} from '../services/User.service';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss']
})
export class InscriptionComponent implements OnInit {

  inscriptionForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private userService: UserService) { }

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
      password: ['', Validators.required]
    });
  }

  onSubmitForm(){
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
    console.log("Utilisateur crée !"); 
    console.log(newUser);
    this.userService.addUser(newUser).subscribe(
      newUser => {
        console.log(newUser);
      }
    );
  }

}
