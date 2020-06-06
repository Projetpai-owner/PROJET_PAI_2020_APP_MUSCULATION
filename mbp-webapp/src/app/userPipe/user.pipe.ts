import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'userFilter'
})
export class UserPipe implements PipeTransform {

  transform(users: any, term?: any): any {
    if( term === undefined) return users;
    return users.filter(function(user){
      return user.nom.toLowerCase().includes(term.toLowerCase()) || user.username.toLowerCase().includes(term.toLowerCase())
		 || user.prenom.toLowerCase().includes(term.toLowerCase());
    })
  }
}