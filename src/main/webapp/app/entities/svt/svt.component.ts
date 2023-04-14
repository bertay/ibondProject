import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISvt } from 'app/shared/model/svt.model';
import { SvtService } from './svt.service';
import { SvtDeleteDialogComponent } from './svt-delete-dialog.component';

@Component({
  selector: 'jhi-svt',
  templateUrl: './svt.component.html',
})
export class SvtComponent implements OnInit, OnDestroy {
  svts?: ISvt[];
  eventSubscriber?: Subscription;

  constructor(protected svtService: SvtService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.svtService.query().subscribe((res: HttpResponse<ISvt[]>) => (this.svts = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSvts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISvt): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSvts(): void {
    this.eventSubscriber = this.eventManager.subscribe('svtListModification', () => this.loadAll());
  }

  delete(svt: ISvt): void {
    const modalRef = this.modalService.open(SvtDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.svt = svt;
  }
}
