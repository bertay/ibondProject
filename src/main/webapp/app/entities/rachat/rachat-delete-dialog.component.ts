import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRachat } from 'app/shared/model/rachat.model';
import { RachatService } from './rachat.service';

@Component({
  templateUrl: './rachat-delete-dialog.component.html',
})
export class RachatDeleteDialogComponent {
  rachat?: IRachat;

  constructor(protected rachatService: RachatService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.rachatService.delete(id).subscribe(() => {
      this.eventManager.broadcast('rachatListModification');
      this.activeModal.close();
    });
  }
}
