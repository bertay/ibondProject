import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDetailCalendrier } from 'app/shared/model/detail-calendrier.model';
import { DetailCalendrierService } from './detail-calendrier.service';
import { DetailCalendrierDeleteDialogComponent } from './detail-calendrier-delete-dialog.component';

@Component({
  selector: 'jhi-detail-calendrier',
  templateUrl: './detail-calendrier.component.html',
})
export class DetailCalendrierComponent implements OnInit, OnDestroy {
  detailCalendriers?: IDetailCalendrier[];
  eventSubscriber?: Subscription;

  constructor(
    protected detailCalendrierService: DetailCalendrierService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.detailCalendrierService.query().subscribe((res: HttpResponse<IDetailCalendrier[]>) => (this.detailCalendriers = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDetailCalendriers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDetailCalendrier): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDetailCalendriers(): void {
    this.eventSubscriber = this.eventManager.subscribe('detailCalendrierListModification', () => this.loadAll());
  }

  delete(detailCalendrier: IDetailCalendrier): void {
    const modalRef = this.modalService.open(DetailCalendrierDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.detailCalendrier = detailCalendrier;
  }
}
