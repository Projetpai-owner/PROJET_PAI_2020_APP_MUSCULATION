import { Component } from '@angular/core';
import { UserService } from './services/User.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'mbp-webapp';

  constructor(private userService: UserService){ }
}
