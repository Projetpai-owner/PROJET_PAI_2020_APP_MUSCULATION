import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { CurrentUser } from '../models/CurrentUser.model';
import { UserService } from '../services/User.service';
import { SalleService } from '../services/Salle.service';
import { User } from '../models/User.model';
import { UserBody } from '../models/UserBody.model';
import { Salle } from '../models/Salle.model';
import { Observable, Subject } from 'rxjs';
import { tap, debounceTime } from 'rxjs/operators';
import { HashService } from '../services/hash.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent implements OnInit {

  currentUser: CurrentUser;
  user: Observable<User>;
  obsSalles: Observable<Salle[]>;
  profilForm: FormGroup;
  myUser: UserBody;
  salles: Salle[];
  currentSalle: number;
  _success: boolean = false;
  password: string;
  adresse: string;
  sid: number;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
    		  private salleService: SalleService,
              private userService: UserService,
              private hashService: HashService,
              private router: Router) { }

  ngOnInit(): void {
    this.initForm();
    this.getUserInfos();
  }

  initForm(){
    this.getAllSalles();
    this.profilForm = this.formBuilder.group({
      prenom: [{value : '', disabled: true},  Validators.required],
			nom: [{value : '', disabled: true}, Validators.required],
      bornDate: [{value : '', disabled: true}, Validators.required],
      sexe: [{value : '', disabled: true}, Validators.required],
			adresse: [''],
      sid: [{value : '', disabled: true}],
      sidList: [''],
			username: [{value : '', disabled: true}, [Validators.required, Validators.email]],
			password: ['', [Validators.pattern("^(?=.{7,}$)(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9]).*$")]],
			confirmpassword: ['']
		}, {
			validator: this.mustMatch('password', 'confirmpassword')
    });
  }

  getAllSalles() {
    this.obsSalles = this.salleService.getSalles();
    this.obsSalles.subscribe(res => {
      this.salles = res;
    })
  }

  getSexe(sexe: String){
    return sexe === 'H' ? 'Homme' : sexe === 'F' ? 'Femme' : 'Autre';
  }

  getPassword(pwd: string){
    this.password = pwd;
    return '';
  }

  getAdresse(adresse: string){
    this.adresse = adresse;
    return adresse;
  }

  getSid(sid: number){
    this.sid = sid;
    return sid;
  }

  getUserInfos(){
    this.currentUser = this.authService.currentUserValue;
    this.userService.getUser(this.currentUser.userId).pipe(
      tap(user => this.profilForm.patchValue({
        prenom: user.prenom,
        nom: user.nom,
        bornDate: user.bornDate.substring(0, 10),
        adresse: this.getAdresse(user.adresse),
        username: user.username,
        sid: user.sid.nom,
        sidList: this.getSid(user.sid.sid),
        password: this.getPassword(user.password),
        sexe: this.getSexe(user.sexe)
      }))).subscribe(res => {
        this.myUser = res;
      });
  }

  onSubmitForm(){
    const formValue = this.profilForm.value;
    let hashedPassword = this.myUser.password;
    let finalSid = +formValue['sidList'];
    if (formValue['password'] !== ''){
		  hashedPassword = this.hashService.hashPassword(formValue['password'])
    } 
    if (+formValue['sidList'] === 0){
      finalSid = +this.myUser.sid.sid;
    }
		const newUser = new User(
			this.myUser.nom,
			this.myUser.prenom,
			this.myUser.bornDate,
			this.myUser.sexe,
			this.myUser.username,
			hashedPassword,
			finalSid,
			formValue['adresse'],
			this.myUser.role
    )
    this.userService.updateUser(newUser).subscribe(res => {
      this.authService.refresh();
      this._success = true;
      setTimeout(() => this._success = false, 5000);
      this.ngOnInit();
		},
			(err: HttpErrorResponse) => {
				console.log(err.message);
			}
		);
  }

  getHashedPassword(password: string){
    return this.hashService.hashPassword(password);
  }

  mustMatch(controlName: string, matchingControlName: string){
		return (formGroup: FormGroup) => {
			const control = formGroup.controls[controlName];
			const matchingControl = formGroup.controls[matchingControlName];
			if(matchingControl.errors && !matchingControl.errors.mustMatch){
				return;
			}
			if(control.value !== matchingControl.value){
				matchingControl.setErrors({mustMatch: true});
			} else {
				matchingControl.setErrors(null);
			}
		}
	}

}
