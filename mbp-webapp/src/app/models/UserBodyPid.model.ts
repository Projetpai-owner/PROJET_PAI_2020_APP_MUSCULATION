import { Salle } from '../models/Salle.model';

export class UserBodyPid {

  constructor(
              public pid: number,
              public nom: string,
              public prenom: string,
              public bornDate: string,
              public sexe: string,
              public username: string,
              public password: string,
              public sid: Salle,
              public adresse: string,
              public role: string
  ) {}

}
