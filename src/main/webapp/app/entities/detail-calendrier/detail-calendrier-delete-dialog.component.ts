import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDetailCalendrier } from 'app/shared/model/detail-calendrier.model';
import { DetailCalendrierService } from './detail-calendrier.service';

@Component({
  templateUrl: './detail-calendrier-delete-dialog.component.html',
})
export class DetailCalendrierDeleteDialogComponent {
  detailCalendrier?: IDetailCalendrier;

  constructor(
    protected detailCalendrierService: DetailCalendrierService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.detailCalendrierService.delete(id).subscribe(() => {
      this.eventManager.broadcast('detailCalendrierListModification');
      this.activeModal.close();
    });
  }
}
