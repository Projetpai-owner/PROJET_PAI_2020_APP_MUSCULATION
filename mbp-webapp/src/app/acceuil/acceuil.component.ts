import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-acceuil',
  templateUrl: './acceuil.component.html',
  styleUrls: ['./acceuil.component.scss']
})
export class AcceuilComponent implements OnInit {

  inscription_success: string;
  inscription: boolean = false;

  cancel_success: string;
  cancel: boolean = false;

  support_success: string;
  support: boolean = false;

  messageGeneral_success: string;
  messageGeneral: boolean = false;

  advert_success: string;
  advert: boolean = false;

  constructor(private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    if(navigation !== null){
      const state = navigation.extras.state as [{data: string}, {from: string}];
      if(state !== undefined){
        if(state[1].from === 'inscription'){
          this.inscription = true;
          this.inscription_success = state[0].data;
          setTimeout(() => this.inscription = false, 6000);
        }
        if(state[1].from === 'cancel'){
          this.cancel = true;
          this.cancel_success = state[0].data;
          setTimeout(() => this.cancel = false, 6000);
        }
		    if(state[1].from === 'support'){
		      this.support = true;
		      this.support_success = state[0].data;
		      setTimeout(() => this.support = false, 6000);
		    }
        if(state[1].from === 'messageGeneral'){
          this.messageGeneral = true;
          this.messageGeneral_success = state[0].data;
          setTimeout(() => this.messageGeneral = false, 6000);
        }
        if(state[1].from === 'advert'){
          this.advert = true;
          this.advert_success = state[0].data;
          setTimeout(() => this.advert = false, 6000);
        }
      }
    }
  }

  ngOnInit(): void {

  }

}
