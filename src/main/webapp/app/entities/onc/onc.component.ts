import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOnc } from 'app/shared/model/onc.model';
import { OncService } from './onc.service';
import { OncDeleteDialogComponent } from './onc-delete-dialog.component';

@Component({
  selector: 'jhi-onc',
  templateUrl: './onc.component.html',
})
export class OncComponent implements OnInit, OnDestroy {
  oncs?: IOnc[];
  eventSubscriber?: Subscription;

  constructor(protected oncService: OncService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.oncService.query().subscribe((res: HttpResponse<IOnc[]>) => (this.oncs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOncs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOnc): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOncs(): void {
    this.eventSubscriber = this.eventManager.subscribe('oncListModification', () => this.loadAll());
  }

  delete(onc: IOnc): void {
    const modalRef = this.modalService.open(OncDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.onc = onc;
  }
}
