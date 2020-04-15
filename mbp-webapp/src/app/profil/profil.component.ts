import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { CurrentUser } from '../models/CurrentUser.model';
import { UserService } from '../services/User.service';
import { SalleService } from '../services/Salle.service';
import { User } from '../models/User.model';
import { UserBody } from '../models/UserBody.model';
import { Salle } from '../models/Salle.model';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
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
      prenom: ['', Validators.required],
			nom: ['', Validators.required],
			bornDate: ['', Validators.required],
			adresse: [''],
			sid: [''],
			username: ['', [Validators.required, Validators.email]],
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

  getUserInfos(){
    this.currentUser = this.authService.currentUserValue;
    this.userService.getUser(this.currentUser.userId).pipe(
      tap(user => this.profilForm.patchValue({
        prenom: user.prenom,
        nom: user.nom,
        bornDate: user.bornDate.substring(0, 10),
        adresse: user.adresse,
        username: user.username
      }))).subscribe(res => {
        this.myUser = res;
      });
  }

  onSubmitForm(){
    const formValue = this.profilForm.value;
    let hashedPassword = this.myUser.password;
    let finalSid = +formValue['sid'];
    if (formValue['password'] !== ''){
		  hashedPassword = this.hashService.hashPassword(formValue['password'])
    } 
    if (+formValue['sid'] === 0){
      finalSid = +this.myUser.sid.sid;
    }
		const newUser = new User(
			formValue['nom'],
			formValue['prenom'],
			formValue['bornDate'],
			this.myUser.sexe,
			formValue['username'],
			hashedPassword,
			finalSid,
			formValue['adresse'],
			'USER'
    )
    this.userService.updateUser(newUser).subscribe(res => {
      this.router.navigate(['myAccount']);
      this.ngOnInit();
		},
			(err: HttpErrorResponse) => {
				console.log(err.error.message);
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
