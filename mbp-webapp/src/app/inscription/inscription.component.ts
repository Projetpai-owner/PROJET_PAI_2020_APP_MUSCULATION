import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators} from '@angular/forms';
import { User } from '../models/User.model';
import { Salle } from '../models/Salle.model';
import { UserService} from '../services/User.service';
import { SalleService } from '../services/Salle.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss']
})
export class InscriptionComponent implements OnInit {

  errorMessage: string;
  inscriptionForm: FormGroup;
  obsSalles: Observable<Salle[]>;
  obsEmails: Observable<string[]>;

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
      password: ['', [Validators.required, Validators.pattern("[0-9a-zA-Z]{6,}")]]
    });
    this.getAllSalles();
    this.getEmails();
  }

  getAllSalles(){
    this.obsSalles = this.salleService.getSalles();
  }

  getEmails(){
    this.obsEmails = this.userService.getEmails();
  }

  onSubmitForm(){
    const formValue = this.inscriptionForm.value;
    let duplicate = false;
    this.obsEmails.forEach(emails => {
      for(let email of emails){
        if(email === formValue['email']) {
          duplicate = true;
          this.errorMessage = 'Cet email est deja utilise par un autre compte';
          console.log('duplicate!');
        }
      }
      if(!duplicate){
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
        this.errorMessage = '';
        this.userService.addUser(newUser).subscribe(
          user => {
            console.log(user);
          }
        );
        this.router.navigate(['/']);
      }
      
    });
  }
}
