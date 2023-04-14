import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReouverture } from 'app/shared/model/reouverture.model';
import { ReouvertureService } from './reouverture.service';

@Component({
  templateUrl: './reouverture-delete-dialog.component.html',
})
export class ReouvertureDeleteDialogComponent {
  reouverture?: IReouverture;

  constructor(
    protected reouvertureService: ReouvertureService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.reouvertureService.delete(id).subscribe(() => {
      this.eventManager.broadcast('reouvertureListModification');
      this.activeModal.close();
    });
  }
}
