export class CurrentUser {

	constructor(public jwt: string,public userId:string,public prenom:string,public isAdmin:string) {

	}
}
