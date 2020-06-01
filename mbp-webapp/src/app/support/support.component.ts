import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, NavigationExtras } from '@angular/router';
import { SupportService } from '../services/SupportUser.service';
import { Support } from '../models/Support.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.scss']
})
export class SupportComponent implements OnInit {
	supportForm: FormGroup
	errorMessage: string;

  constructor(private formBuilder: FormBuilder, private router: Router, private supportService: SupportService) { }

  ngOnInit(): void {
		this.initForm();
	}

	initForm() {
		this.supportForm = this.formBuilder.group({
			object: ['', Validators.required],
			description: ['', Validators.required]
		});
	}

	onSubmitForm(){
		 if (this.supportForm.invalid) {
            return;
        }
		const formValue = this.supportForm.value;
		
		const ticket = new Support(
			formValue['object'],
			formValue['description']
		)
		
		this.errorMessage = '';
		this.supportService.addTicket(ticket).subscribe(res => {
				const navigationExtras: NavigationExtras = {state: [{data: 'Votre demande de support est prise en compte'}, {from: 'support'}]};
				this.router.navigate(['/'], navigationExtras);
			},
			(err: HttpErrorResponse) => {
				this.errorMessage = err.error.message;
			}
		);
				
	}

}
