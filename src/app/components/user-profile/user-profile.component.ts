import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {UserService} from "../../services/user/user.service";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
    user: User;
    currentUser: string;
    loggedUserName: string;
    isLoggedUserProfile: boolean;
    isAdmin: boolean;

    constructor(private userService: UserService,
                private route: ActivatedRoute) {

    }

    async ngOnInit() {
        if (!JSON.parse(localStorage.getItem('currentUser'))) {
            location.assign('/login');
        }
        this.user = JSON.parse(localStorage.getItem('currentUser'));
        this.loggedUserName = this.user.userName;
        this.isAdmin = this.user.userRole != 0;

        this.currentUser = this.route.snapshot.paramMap.get('userName');
        await this.userService.searchUser(this.currentUser)
            .toPromise().then(user => {
                if (user.userRole != 0) this.user = null;
                else this.user = user;
            });

        if (this.user == null) {
            location.assign('/error');
        }

        this.isLoggedUserProfile = this.loggedUserName === this.user.userName;
    }

    addFriend() {
        //Function for adding friends
    }

    update(): void {
        this.userService.updateProfile(this.currentUser, this.user)
            .subscribe(
                user => {
                    if (!this.isAdmin) localStorage.setItem('currentUser', JSON.stringify(user));
                    location.assign('/user/' + user.userName);
                }
            );
    }

}
