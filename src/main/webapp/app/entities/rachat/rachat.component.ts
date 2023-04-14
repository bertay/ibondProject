import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRachat } from 'app/shared/model/rachat.model';
import { RachatService } from './rachat.service';
import { RachatDeleteDialogComponent } from './rachat-delete-dialog.component';

@Component({
  selector: 'jhi-rachat',
  templateUrl: './rachat.component.html',
})
export class RachatComponent implements OnInit, OnDestroy {
  rachats?: IRachat[];
  eventSubscriber?: Subscription;

  constructor(protected rachatService: RachatService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.rachatService.query().subscribe((res: HttpResponse<IRachat[]>) => (this.rachats = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRachats();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRachat): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRachats(): void {
    this.eventSubscriber = this.eventManager.subscribe('rachatListModification', () => this.loadAll());
  }

  delete(rachat: IRachat): void {
    const modalRef = this.modalService.open(RachatDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.rachat = rachat;
  }
}
