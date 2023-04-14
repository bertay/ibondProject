import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDetailSoumission } from 'app/shared/model/detail-soumission.model';
import { DetailSoumissionService } from './detail-soumission.service';
import { DetailSoumissionDeleteDialogComponent } from './detail-soumission-delete-dialog.component';

@Component({
  selector: 'jhi-detail-soumission',
  templateUrl: './detail-soumission.component.html',
})
export class DetailSoumissionComponent implements OnInit, OnDestroy {
  detailSoumissions?: IDetailSoumission[];
  eventSubscriber?: Subscription;

  constructor(
    protected detailSoumissionService: DetailSoumissionService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.detailSoumissionService.query().subscribe((res: HttpResponse<IDetailSoumission[]>) => (this.detailSoumissions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDetailSoumissions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDetailSoumission): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDetailSoumissions(): void {
    this.eventSubscriber = this.eventManager.subscribe('detailSoumissionListModification', () => this.loadAll());
  }

  delete(detailSoumission: IDetailSoumission): void {
    const modalRef = this.modalService.open(DetailSoumissionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.detailSoumission = detailSoumission;
  }
}
