import { Injectable } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ClassicAlertComponent } from '../classic-alert/classic-alert.component';

@Injectable()
export class ClassicAlertService {
    
    constructor(private modalService: NgbModal){}

    public alert(
        title: string,
        message: string,
        btnText : string = 'OK',
        dialogSize: 'sm'|'lg' = 'sm'){
            const modalRef = this.modalService.open(ClassicAlertComponent, { size: dialogSize });
            modalRef.componentInstance.title = title;
            modalRef.componentInstance.message = message;
            modalRef.componentInstance.btnText = btnText;
    }
}