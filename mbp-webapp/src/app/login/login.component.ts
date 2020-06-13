import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service'
import { first } from 'rxjs/operators';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
	loginForm: FormGroup
	errorMessage: string;
  isWait: boolean;

	constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) { }

	ngOnInit(): void {
		this.initForm();
	}

	initForm() {
		this.loginForm = this.formBuilder.group({
			email: ['', Validators.required],
			password: ['', Validators.required]
		});
	}



	onSubmitForm(){
		 if (this.loginForm.invalid) {
            return;
        }

		this.isWait = true;
		const formValue = this.loginForm.value;
		this.authService.login(formValue['email'],formValue['password'])
		    .pipe(first())
            .subscribe(
                data => {
					          console.log("[onSubmitForm] authentication r�ussie");
					          this.isWait = false;
                    this.router.navigate(["/"]);
                },
                error => {
                    console.log("[onSubmitForm] authentication �chou�e");
                    this.isWait = false;
				            this.errorMessage="Email ou mot de passe incorrect";
                });

	}

}
