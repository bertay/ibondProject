import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClassement } from 'app/shared/model/classement.model';
import { ClassementService } from './classement.service';

@Component({
  templateUrl: './classement-delete-dialog.component.html',
})
export class ClassementDeleteDialogComponent {
  classement?: IClassement;

  constructor(
    protected classementService: ClassementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.classementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('classementListModification');
      this.activeModal.close();
    });
  }
}
