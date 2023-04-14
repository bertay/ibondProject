import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMembreSyndicat } from 'app/shared/model/membre-syndicat.model';
import { MembreSyndicatService } from './membre-syndicat.service';

@Component({
  templateUrl: './membre-syndicat-delete-dialog.component.html',
})
export class MembreSyndicatDeleteDialogComponent {
  membreSyndicat?: IMembreSyndicat;

  constructor(
    protected membreSyndicatService: MembreSyndicatService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.membreSyndicatService.delete(id).subscribe(() => {
      this.eventManager.broadcast('membreSyndicatListModification');
      this.activeModal.close();
    });
  }
}
