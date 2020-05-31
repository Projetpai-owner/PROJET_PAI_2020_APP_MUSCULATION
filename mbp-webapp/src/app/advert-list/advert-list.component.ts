import { Component, OnInit } from '@angular/core';
import {AdvertService} from '../services/Advert.service';
import {AdvertItemList} from '../models/AdvertItemList.model';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TypeSeance } from '../models/TypeSeance.model';
import { TypeSeanceService } from '../services/TypeSeance.service';
import { SalleService } from '../services/Salle.service';
import { Salle } from '../models/Salle.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-advert-list',
  templateUrl: './advert-list.component.html',
  styleUrls: ['./advert-list.component.scss']
})
export class AdvertListComponent implements OnInit {

  ItemsArray = [];
  obsTypeSeance: Observable<TypeSeance[]>;
  obsSalles: Observable<Salle[]>;
  filtreAnnonceForm: FormGroup;
  zoneFiltreVisible: boolean;
  
  constructor(public advertService: AdvertService, 
              private typeSeanceService: TypeSeanceService,
              private salleService: SalleService,
              private router: Router,
              private formBuilder: FormBuilder,) { }

  ngOnInit(): void {
    this.advertService.getAdverts().subscribe((res: AdvertItemList[]) => {
      this.ItemsArray = res;
    });
    this.initSelect();
    this.initForm();
    this.zoneFiltreVisible = false;
  }

  initSelect(): void {
    this.obsTypeSeance = this.typeSeanceService.getTypeSeance();
    this.obsSalles = this.salleService.getSalles();
  }

  initForm(): void{
    this.filtreAnnonceForm = this.formBuilder.group({
      date: ['',null],
      dureeMin: ['',null],
      dureeMax: ['',null],
      niveau: ['',null],
      typeSeance: ['',null],
      salle: ['',null],
      sex: ['',null]
    });
  }

  showZoneFiltre(): void{
    this.zoneFiltreVisible = !this.zoneFiltreVisible;
  }

  deleteAdvertById(aid: number){
    this.advertService.deleteAdvertById(aid);
    this.router.navigate(['/']);
  }
}
