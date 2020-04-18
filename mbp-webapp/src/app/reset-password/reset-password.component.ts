import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../services/User.service';
import { first } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { HashService } from '../services/hash.service';

@Component({
	selector: 'app-reset-password',
	templateUrl: './reset-password.component.html',
	styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {
	resetPasswordForm: FormGroup
	createTokenForm: FormGroup
	errorMessage: string;
	goodToken: boolean
	constructor(private formBuilder: FormBuilder, private userService: UserService, private routeActive: ActivatedRoute, private hashService: HashService,
	private router: Router) { }

	ngOnInit(): void {
		this.containsQueryParameter()
		this.initForm()
	}

	containsQueryParameter() {

		if (this.routeActive.snapshot.queryParams['token']) {
			this.userService.isValidPasswordToken(this.routeActive.snapshot.queryParams['token'])
				.pipe(first())
				.subscribe(
					data => {
						this.goodToken = true;
					},
					error => {
						this.goodToken = false;
					});
		} else {
			this.goodToken = false;
		}
	}

	initForm() {
		this.resetPasswordForm = this.formBuilder.group({
			password: ['',  [Validators.required, Validators.pattern("^(?=.{7,}$)(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9]).*$")]],
			confirmPassword: ['']
		},
		{
			validator: this.mustMatch('password','confirmPassword')
   		 });

		this.createTokenForm = this.formBuilder.group({
			email: ['', Validators.required]
		});
	}

	onSubmitFormCreateToken() {
		if (this.createTokenForm.invalid) {
			return;
		}
		const formValue = this.createTokenForm.value;
		this.userService.createPasswordToken(formValue['email'])
			.pipe(first())
			.subscribe(
				data => {
					console.log("reset password token crée")
					this.router.navigate(['/']);
					window.alert("Vous allez recevoir un mail contenant les instructions pour réinitialiser votre mot de passe")
				},
				error => {
					window.alert(error.error.message)
					console.log("email non valide");
				});

	}

	onSubmitFormResetPassword() {
		if (this.resetPasswordForm.invalid) {
			return;
		}
		const formValue = this.resetPasswordForm.value;
		const hashedPassword = this.hashService.hashPassword(formValue['password'])
		this.userService.resetPassword(hashedPassword,this.routeActive.snapshot.queryParams['token'])
		.pipe(first())
		.subscribe(
				data => {
					console.log("mot de passe changé")
					window.alert("votre mot de passe a bien été changé")
					this.router.navigate(['/']);
				},
				error => {
					window.alert(error.error.message)
					console.log("token expiré ou non valide");
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
