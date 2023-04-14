import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMembreSyndicat } from 'app/shared/model/membre-syndicat.model';
import { MembreSyndicatService } from './membre-syndicat.service';
import { MembreSyndicatDeleteDialogComponent } from './membre-syndicat-delete-dialog.component';

@Component({
  selector: 'jhi-membre-syndicat',
  templateUrl: './membre-syndicat.component.html',
})
export class MembreSyndicatComponent implements OnInit, OnDestroy {
  membreSyndicats?: IMembreSyndicat[];
  eventSubscriber?: Subscription;

  constructor(
    protected membreSyndicatService: MembreSyndicatService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.membreSyndicatService.query().subscribe((res: HttpResponse<IMembreSyndicat[]>) => (this.membreSyndicats = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMembreSyndicats();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMembreSyndicat): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMembreSyndicats(): void {
    this.eventSubscriber = this.eventManager.subscribe('membreSyndicatListModification', () => this.loadAll());
  }

  delete(membreSyndicat: IMembreSyndicat): void {
    const modalRef = this.modalService.open(MembreSyndicatDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.membreSyndicat = membreSyndicat;
  }
}
