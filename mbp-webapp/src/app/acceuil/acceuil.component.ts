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
 
  constructor(private router: Router) { 
    const navigation = this.router.getCurrentNavigation();
    if(navigation !== null){
      const state = navigation.extras.state as {data: string};
      if(state !== undefined){
        this.inscription = true;
        this.inscription_success = state.data;
        setTimeout(() => this.inscription = false, 6000);
      }
    }
  }

  ngOnInit(): void {
    
  }

}
