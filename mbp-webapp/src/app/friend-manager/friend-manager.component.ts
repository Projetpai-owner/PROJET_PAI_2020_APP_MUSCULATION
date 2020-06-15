import { Component, OnInit } from '@angular/core';
import { Ami } from '../models/Ami.model';
import { FriendService } from '../services/Friend.service';
import { AuthService } from '../services/auth.service';
import { Observable } from 'rxjs';
import { ClassicAlertService } from '../services/classic-alert.service';
import { User } from '../models/User.model';
import { UserService } from '../services/User.service';

@Component({
	selector: 'app-friend-manager',
	templateUrl: './friend-manager.component.html',
	styleUrls: ['./friend-manager.component.scss']
})


export class FriendManagerComponent implements OnInit {
	friends: Observable<Ami[]>;
	obsListUser: Observable<User[]>;
	term:string;


	constructor(public userService: UserService, public friendService: FriendService, public authService: AuthService, public classicAlertService: ClassicAlertService) { }

	ngOnInit(): void {
		this.friends = this.friendService.getMyFriends();
		this.getAllUsersExceptFriendsAndMe();
	}


	public getAllUsersExceptFriendsAndMe() {
		this.obsListUser = this.userService.getAllUsersExceptFriendsAndMe(this.authService.currentUserValue.userId);
	}

	delete(pid): void {
		this.friendService.deleteByPid(this.authService.currentUserValue.userId, pid).subscribe(message => {
			this.ngOnInit();
		});
	}


	public addFriend(user:User){
		this.friendService.addFriend(user.pid).subscribe(message =>{
			this.ngOnInit();
		});
	}

}


