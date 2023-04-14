import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISoumission } from 'app/shared/model/soumission.model';
import { SoumissionService } from './soumission.service';

@Component({
  templateUrl: './soumission-delete-dialog.component.html',
})
export class SoumissionDeleteDialogComponent {
  soumission?: ISoumission;

  constructor(
    protected soumissionService: SoumissionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.soumissionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('soumissionListModification');
      this.activeModal.close();
    });
  }
}
