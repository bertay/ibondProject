import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAvisEmission } from 'app/shared/model/avis-emission.model';
import { AvisEmissionService } from './avis-emission.service';
import { AvisEmissionDeleteDialogComponent } from './avis-emission-delete-dialog.component';

@Component({
  selector: 'jhi-avis-emission',
  templateUrl: './avis-emission.component.html',
})
export class AvisEmissionComponent implements OnInit, OnDestroy {
  avisEmissions?: IAvisEmission[];
  eventSubscriber?: Subscription;

  constructor(
    protected avisEmissionService: AvisEmissionService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.avisEmissionService.query().subscribe((res: HttpResponse<IAvisEmission[]>) => (this.avisEmissions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAvisEmissions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAvisEmission): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAvisEmissions(): void {
    this.eventSubscriber = this.eventManager.subscribe('avisEmissionListModification', () => this.loadAll());
  }

  delete(avisEmission: IAvisEmission): void {
    const modalRef = this.modalService.open(AvisEmissionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.avisEmission = avisEmission;
  }
}
