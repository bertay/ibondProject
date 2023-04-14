import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOnc } from 'app/shared/model/onc.model';
import { OncService } from './onc.service';

@Component({
  templateUrl: './onc-delete-dialog.component.html',
})
export class OncDeleteDialogComponent {
  onc?: IOnc;

  constructor(protected oncService: OncService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.oncService.delete(id).subscribe(() => {
      this.eventManager.broadcast('oncListModification');
      this.activeModal.close();
    });
  }
}
