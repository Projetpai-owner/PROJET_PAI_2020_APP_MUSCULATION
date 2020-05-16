import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MessageGeneral } from '../models/MessageGeneral.model';
import { MessagerieService } from '../services/Messagerie.service';

@Component({
  selector: 'app-message-general',
  templateUrl: './message-general.component.html',
  styleUrls: ['./message-general.component.scss']
})
export class MessageGeneralComponent implements OnInit {

  messageGeneralForm : FormGroup;
  enAttente: boolean;


  constructor(private formBuilder: FormBuilder,private messagerieService: MessagerieService) { }

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
    console.log("ON SUBMIT");
    this.enAttente = true;
    let formValue = this.messageGeneralForm.value;
    console.log(" objet : "+formValue['objet']);
    console.log(" message : "+formValue['message']);
    let messageGeneral = new MessageGeneral(formValue['objet'],formValue['message']);
    this.messagerieService.sendMessageGeneral(messageGeneral).subscribe(response => {console.log("response : " + response)});
    this.enAttente = false;
  }

}
