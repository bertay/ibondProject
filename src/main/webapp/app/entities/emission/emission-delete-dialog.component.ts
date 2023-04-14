import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEmission } from 'app/shared/model/emission.model';
import { EmissionService } from './emission.service';

@Component({
  templateUrl: './emission-delete-dialog.component.html',
})
export class EmissionDeleteDialogComponent {
  emission?: IEmission;

  constructor(protected emissionService: EmissionService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.emissionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('emissionListModification');
      this.activeModal.close();
    });
  }
}
