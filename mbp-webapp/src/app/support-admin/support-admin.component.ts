import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {SupportService} from '../services/SupportUser.service';
import {UserService} from '../services/User.service';
import {ConfirmAlertService} from '../services/confirm-alert.service';
import {ClassicAlertService} from '../services/classic-alert.service';
import {SupportBody} from '../models/SupportBody.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-support-admin',
  templateUrl: './support-admin.component.html',
  styleUrls: ['./support-admin.component.scss']
})

export class SupportAdminComponent implements OnInit {

  obsListTickets: Observable<SupportBody[]>;

  constructor(private supportService: SupportService, private userService: UserService, private router: Router,
              private confirmAlertService: ConfirmAlertService, private classicAlertService: ClassicAlertService) {
  }

  ngOnInit(): void {
    this.getAllTickets();
  }

  public getAllTickets() {
    this.obsListTickets = this.supportService.getAllTickets();
  }

  public confirmDeleteTicket(ticket: SupportBody) {
    this.confirmAlertService.confirm('Confirmez votre action : ', 'Voulez-vous vraiment supprimer ce ticket ?', 'Confirmer', 'Annuler', 'lg')
      .then((confirmed) => {if (confirmed) {this.deleteUser(ticket); } else {this.actionAnnule(ticket); }})
      .catch(() => this.actionAnnule(ticket));
  }

  public deleteUser(support: SupportBody) {
    this.supportService.deleteTicket(support.suid);
    this.router.navigate(['/']);
    this.gereRetourDelete();
  }

  public gereRetourDelete() {
    this.ngOnInit();
    this.classicAlertService.alert('Ticket supprimé', 'Le ticket a été supprimé de la liste des tickets en cours', 'OK', 'sm');
  }

  public actionAnnule(support: SupportBody) {
    this.classicAlertService.alert('Action annulée', 'Le ticket n\'a pas été supprimé', 'OK', 'sm');
  }

}
