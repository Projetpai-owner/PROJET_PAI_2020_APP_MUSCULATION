export class User {
	
	constructor(public pid: number,
				public nom: string,
				public prenom: string,
				public bornDate: string,
				public sexe: string,
				public username: string,
				public password: string,
				public sid: number,
				public adresse: string,
				public role: string
				) {}

}