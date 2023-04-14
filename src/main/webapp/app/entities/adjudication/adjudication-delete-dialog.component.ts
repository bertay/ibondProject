import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAdjudication } from 'app/shared/model/adjudication.model';
import { AdjudicationService } from './adjudication.service';

@Component({
  templateUrl: './adjudication-delete-dialog.component.html',
})
export class AdjudicationDeleteDialogComponent {
  adjudication?: IAdjudication;

  constructor(
    protected adjudicationService: AdjudicationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.adjudicationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('adjudicationListModification');
      this.activeModal.close();
    });
  }
}
