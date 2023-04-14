import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParametres } from 'app/shared/model/parametres.model';
import { ParametresService } from './parametres.service';

@Component({
  templateUrl: './parametres-delete-dialog.component.html',
})
export class ParametresDeleteDialogComponent {
  parametres?: IParametres;

  constructor(
    protected parametresService: ParametresService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.parametresService.delete(id).subscribe(() => {
      this.eventManager.broadcast('parametresListModification');
      this.activeModal.close();
    });
  }
}
