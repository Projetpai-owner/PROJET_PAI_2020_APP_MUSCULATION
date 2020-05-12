import { TypeSeance } from '../models/Typeseance.model';

export class AdvertEntity {

  constructor(public description: string,
              public niveauPratique: string,
              public dureeSeance: number,
              public nom: string,
              public dateSeance: Date,
              public idSeance: TypeSeance) {
  }
}
