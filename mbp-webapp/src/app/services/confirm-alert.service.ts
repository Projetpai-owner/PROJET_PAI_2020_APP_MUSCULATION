import { Injectable } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmAlertComponent } from '../confirm-alert/confirm-alert.component';

@Injectable()
export class ConfirmAlertService{

    constructor(private modalService: NgbModal){}

    public confirm(
        title: string,
        message: string,
        btnOkText: string = 'Confirmer',
        btnCancelText: string = 'Annuler',
        dialogSize: 'sm'|'lg' = 'sm'): Promise<boolean> {
        const modalRef = this.modalService.open(ConfirmAlertComponent, { size: dialogSize });
        modalRef.componentInstance.title = title;
        modalRef.componentInstance.message = message;
        modalRef.componentInstance.btnOkText = btnOkText;
        modalRef.componentInstance.btnCancelText = btnCancelText;
    
        return modalRef.result;
      }
}