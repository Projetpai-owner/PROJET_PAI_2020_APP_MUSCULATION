import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MessageGeneral } from '../models/MessageGeneral.model';
import { MessagerieService } from '../services/Messagerie.service';
import { NavigationExtras, Router } from '@angular/router';

@Component({
  selector: 'app-message-general',
  templateUrl: './message-general.component.html',
  styleUrls: ['./message-general.component.scss']
})
export class MessageGeneralComponent implements OnInit {

  messageGeneralForm : FormGroup;
  enAttente: boolean;


  constructor(private formBuilder: FormBuilder,private messagerieService: MessagerieService, private router: Router) { }

  ngOnInit(): void {
    this.initForm();
    this.enAttente = false;
  }

  initForm(){
    this.messageGeneralForm = this.formBuilder.group({
      objet : ['',Validators.required],
      message : ['',Validators.required]
    },{});
  }

  submitForm(){
    this.enAttente = true;
    let formValue = this.messageGeneralForm.value;
    let messageGeneral = new MessageGeneral(formValue['objet'],formValue['message']);
    this.messagerieService.sendMessageGeneral(messageGeneral).subscribe();
    this.enAttente = false;
    const navigationExtras: NavigationExtras = {state: [{data: 'Le message a bien été envoyé'}, {from: 'messageGeneral'}]};
		this.router.navigate(['/'], navigationExtras);
  }

}
