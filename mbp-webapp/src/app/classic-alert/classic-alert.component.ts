import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-classic-alert',
  templateUrl: './classic-alert.component.html',
  styleUrls: ['./classic-alert.component.scss']
})
export class ClassicAlertComponent implements OnInit {

  @Input() title: string;
  @Input() message: string;
  @Input() btnText: string;

  constructor(private activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  public dismiss() {
    this.activeModal.dismiss();
  }

}
