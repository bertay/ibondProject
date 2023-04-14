import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISvt } from 'app/shared/model/svt.model';
import { SvtService } from './svt.service';

@Component({
  templateUrl: './svt-delete-dialog.component.html',
})
export class SvtDeleteDialogComponent {
  svt?: ISvt;

  constructor(protected svtService: SvtService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.svtService.delete(id).subscribe(() => {
      this.eventManager.broadcast('svtListModification');
      this.activeModal.close();
    });
  }
}
