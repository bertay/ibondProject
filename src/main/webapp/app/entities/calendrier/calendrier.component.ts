import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICalendrier } from 'app/shared/model/calendrier.model';
import { CalendrierService } from './calendrier.service';
import { CalendrierDeleteDialogComponent } from './calendrier-delete-dialog.component';

@Component({
  selector: 'jhi-calendrier',
  templateUrl: './calendrier.component.html',
})
export class CalendrierComponent implements OnInit, OnDestroy {
  calendriers?: ICalendrier[];
  eventSubscriber?: Subscription;

  constructor(protected calendrierService: CalendrierService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.calendrierService.query().subscribe((res: HttpResponse<ICalendrier[]>) => (this.calendriers = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCalendriers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICalendrier): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCalendriers(): void {
    this.eventSubscriber = this.eventManager.subscribe('calendrierListModification', () => this.loadAll());
  }

  delete(calendrier: ICalendrier): void {
    const modalRef = this.modalService.open(CalendrierDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.calendrier = calendrier;
  }
}
