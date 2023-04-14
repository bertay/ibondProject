import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IReouverture } from 'app/shared/model/reouverture.model';
import { ReouvertureService } from './reouverture.service';
import { ReouvertureDeleteDialogComponent } from './reouverture-delete-dialog.component';

@Component({
  selector: 'jhi-reouverture',
  templateUrl: './reouverture.component.html',
})
export class ReouvertureComponent implements OnInit, OnDestroy {
  reouvertures?: IReouverture[];
  eventSubscriber?: Subscription;

  constructor(
    protected reouvertureService: ReouvertureService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.reouvertureService.query().subscribe((res: HttpResponse<IReouverture[]>) => (this.reouvertures = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInReouvertures();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IReouverture): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInReouvertures(): void {
    this.eventSubscriber = this.eventManager.subscribe('reouvertureListModification', () => this.loadAll());
  }

  delete(reouverture: IReouverture): void {
    const modalRef = this.modalService.open(ReouvertureDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.reouverture = reouverture;
  }
}
