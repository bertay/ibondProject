import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISoumission } from 'app/shared/model/soumission.model';
import { SoumissionService } from './soumission.service';
import { SoumissionDeleteDialogComponent } from './soumission-delete-dialog.component';

@Component({
  selector: 'jhi-soumission',
  templateUrl: './soumission.component.html',
})
export class SoumissionComponent implements OnInit, OnDestroy {
  soumissions?: ISoumission[];
  eventSubscriber?: Subscription;

  constructor(protected soumissionService: SoumissionService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.soumissionService.query().subscribe((res: HttpResponse<ISoumission[]>) => (this.soumissions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSoumissions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISoumission): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSoumissions(): void {
    this.eventSubscriber = this.eventManager.subscribe('soumissionListModification', () => this.loadAll());
  }

  delete(soumission: ISoumission): void {
    const modalRef = this.modalService.open(SoumissionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.soumission = soumission;
  }
}
