import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAdjudication } from 'app/shared/model/adjudication.model';
import { AdjudicationService } from './adjudication.service';
import { AdjudicationDeleteDialogComponent } from './adjudication-delete-dialog.component';

@Component({
  selector: 'jhi-adjudication',
  templateUrl: './adjudication.component.html',
})
export class AdjudicationComponent implements OnInit, OnDestroy {
  adjudications?: IAdjudication[];
  eventSubscriber?: Subscription;

  constructor(
    protected adjudicationService: AdjudicationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.adjudicationService.query().subscribe((res: HttpResponse<IAdjudication[]>) => (this.adjudications = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAdjudications();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAdjudication): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAdjudications(): void {
    this.eventSubscriber = this.eventManager.subscribe('adjudicationListModification', () => this.loadAll());
  }

  delete(adjudication: IAdjudication): void {
    const modalRef = this.modalService.open(AdjudicationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.adjudication = adjudication;
  }
}
