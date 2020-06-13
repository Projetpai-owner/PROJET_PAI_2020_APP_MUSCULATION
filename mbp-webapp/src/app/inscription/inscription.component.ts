import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { User } from '../models/User.model';
import { Salle } from '../models/Salle.model';
import { UserService } from '../services/User.service';
import { SalleService } from '../services/Salle.service';
import { Observable } from 'rxjs';
import { Router, NavigationExtras } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { HashService } from '../services/hash.service';
import { BanniService } from '../services/Banni.service';

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
		private router: Router,
		private hashService: HashService,
		private banniService: BanniService
	) { }

	ngOnInit() {
		this.initForm();
	}

	initForm() {
		this.inscriptionForm = this.formBuilder.group({
			prenom: ['', [Validators.required, Validators.maxLength(30)]],
			nom: ['', [Validators.required, Validators.maxLength(25)]],
			bornDate: ['', Validators.required],
			sexe: ['', Validators.required],
			adresse: ['', [Validators.required, Validators.maxLength(200)]],
			sid: ['', Validators.required],
			email: ['', [Validators.required, Validators.email, Validators.maxLength(50)]],
			password: ['', [Validators.required, Validators.pattern("^(?=.{7,}$)(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9]).*$"), Validators.maxLength(50)]],
			confirmpassword: ['', [Validators.required]]
		}, {
			validator: this.mustMatch('password', 'confirmpassword')
		});
		this.getAllSalles();
	}

	getAllSalles() {
		this.obsSalles = this.salleService.getSalles();
	}

	onSubmitForm() {
		this.IsWait = true;
		const formValue = this.inscriptionForm.value;
		const hashedPassword = this.hashService.hashPassword(formValue['password'])
		const newUser = new User(
			null,
			formValue['nom'],
			formValue['prenom'],
			formValue['bornDate'],
			formValue['sexe'],
			formValue['email'],
			hashedPassword,
			+formValue['sid'],
			formValue['adresse'],
			'USER'
		)
		this.errorMessage = '';
		this.banniService.getBanned().subscribe(res => {
			const listBanned = res;
			if(listBanned.find(banned => banned.email === newUser.username)){
				this.errorMessage = 'Votre compte est banni. Vous ne pouvez pas vous reinscrire.';
			} else {
				this.userService.addUser(newUser).subscribe(response => {
					const navigationExtras: NavigationExtras = {state: [{data: 'Votre inscription est prise en compte'}, {from: 'inscription'}]};
					this.router.navigate(['/'], navigationExtras);
					},
					(err: HttpErrorResponse) => {
						this.errorMessage = err.error.message;
					}
				);
			}
			this.IsWait = false;
		});

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
