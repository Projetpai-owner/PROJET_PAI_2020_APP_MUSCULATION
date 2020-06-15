import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, NavigationExtras } from '@angular/router';
import { SupportService } from '../services/SupportUser.service';
import { Support } from '../models/Support.model';
import { HttpErrorResponse } from '@angular/common/http';
import {CurrentUser} from '../models/CurrentUser.model';
import {UserService} from '../services/User.service';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.scss']
})
export class SupportComponent implements OnInit {
	supportForm: FormGroup;
	errorMessage: string;
  myUsername: string;
  isWait: boolean;


  constructor(private formBuilder: FormBuilder, private router: Router, private supportService: SupportService,
              private userService: UserService, private authService: AuthService) {
    authService.currentUser.subscribe(user => {
      if(user.userId != null){
        this.initMyUserName(user);
      }
    });
  }

  ngOnInit(): void {
		this.initForm();
	}

	initForm() {
		this.supportForm = this.formBuilder.group({
			object: ['', [Validators.required, Validators.maxLength(50)]],
			description: ['', [Validators.required, Validators.maxLength(500)]]
		});
	}

  public initMyUserName(currentUser: CurrentUser) {
    this.userService.getUser(currentUser.userId).subscribe(user => {
      this.myUsername = user.username; });
  }

	onSubmitForm() {
		 if (this.supportForm.invalid) {
            return;
        }

    this.isWait = true;

    const formValue = this.supportForm.value;

		 const ticket = new Support(
		   this.myUsername,
		   formValue.object,
       formValue.description
		);

		 this.errorMessage = '';
		 this.supportService.addTicket(ticket).subscribe(res => {
         this.isWait = false;
         const navigationExtras: NavigationExtras = {state: [{data: 'Votre demande de support est prise en compte'}, {from: 'support'}]};
         this.router.navigate(['/'], navigationExtras);
			},
			(err: HttpErrorResponse) => {
		    this.isWait = false;
				this.errorMessage = err.error.message;
			}
		);

	}

}
