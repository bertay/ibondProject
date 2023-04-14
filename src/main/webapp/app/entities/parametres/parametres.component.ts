import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IParametres } from 'app/shared/model/parametres.model';
import { ParametresService } from './parametres.service';
import { ParametresDeleteDialogComponent } from './parametres-delete-dialog.component';

@Component({
  selector: 'jhi-parametres',
  templateUrl: './parametres.component.html',
})
export class ParametresComponent implements OnInit, OnDestroy {
  parametres?: IParametres[];
  eventSubscriber?: Subscription;

  constructor(protected parametresService: ParametresService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.parametresService.query().subscribe((res: HttpResponse<IParametres[]>) => (this.parametres = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInParametres();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IParametres): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInParametres(): void {
    this.eventSubscriber = this.eventManager.subscribe('parametresListModification', () => this.loadAll());
  }

  delete(parametres: IParametres): void {
    const modalRef = this.modalService.open(ParametresDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.parametres = parametres;
  }
}
