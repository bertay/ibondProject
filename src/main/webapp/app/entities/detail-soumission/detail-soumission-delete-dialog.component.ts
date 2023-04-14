import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDetailSoumission } from 'app/shared/model/detail-soumission.model';
import { DetailSoumissionService } from './detail-soumission.service';

@Component({
  templateUrl: './detail-soumission-delete-dialog.component.html',
})
export class DetailSoumissionDeleteDialogComponent {
  detailSoumission?: IDetailSoumission;

  constructor(
    protected detailSoumissionService: DetailSoumissionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.detailSoumissionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('detailSoumissionListModification');
      this.activeModal.close();
    });
  }
}
