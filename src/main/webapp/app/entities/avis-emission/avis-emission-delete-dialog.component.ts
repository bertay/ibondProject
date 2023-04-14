import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAvisEmission } from 'app/shared/model/avis-emission.model';
import { AvisEmissionService } from './avis-emission.service';

@Component({
  templateUrl: './avis-emission-delete-dialog.component.html',
})
export class AvisEmissionDeleteDialogComponent {
  avisEmission?: IAvisEmission;

  constructor(
    protected avisEmissionService: AvisEmissionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.avisEmissionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('avisEmissionListModification');
      this.activeModal.close();
    });
  }
}
