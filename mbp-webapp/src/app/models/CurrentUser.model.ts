export class CurrentUser {

	constructor(public accessToken: string,public refreshToken: string,public userId:string,public prenom:string,public role:string) {

	}
}
