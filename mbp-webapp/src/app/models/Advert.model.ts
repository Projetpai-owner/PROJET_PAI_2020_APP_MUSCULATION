export class Advert {

  constructor(public description: string,
              public niveauPratique: string,
              public dureeSeance: number,
              public nom: string,
              public dateSeance: Date,
              public idSeance: number,
              public idUser: number) {
  }
}
