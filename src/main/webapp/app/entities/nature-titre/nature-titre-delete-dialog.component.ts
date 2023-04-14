import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INatureTitre } from 'app/shared/model/nature-titre.model';
import { NatureTitreService } from './nature-titre.service';

@Component({
  templateUrl: './nature-titre-delete-dialog.component.html',
})
export class NatureTitreDeleteDialogComponent {
  natureTitre?: INatureTitre;

  constructor(
    protected natureTitreService: NatureTitreService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.natureTitreService.delete(id).subscribe(() => {
      this.eventManager.broadcast('natureTitreListModification');
      this.activeModal.close();
    });
  }
}
