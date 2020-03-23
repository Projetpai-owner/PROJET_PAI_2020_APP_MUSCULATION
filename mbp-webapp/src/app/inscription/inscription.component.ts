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
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      bornDate: ['', Validators.required],
      sex: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onSubmitForm(){
    const formValue = this.inscriptionForm.value;
    const newUser = new User(
      formValue['firstName'],
      formValue['lastName'],
      formValue['bornDate'],
      formValue['sex'],
      formValue['email'],
      formValue['password']
    ) 
    console.log("Utilisateur crée !"); 
    this.userService.addUser(newUser);
  }

}
