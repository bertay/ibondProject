import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEmission } from 'app/shared/model/emission.model';
import { EmissionService } from './emission.service';
import { EmissionDeleteDialogComponent } from './emission-delete-dialog.component';

@Component({
  selector: 'jhi-emission',
  templateUrl: './emission.component.html',
})
export class EmissionComponent implements OnInit, OnDestroy {
  emissions?: IEmission[];
  eventSubscriber?: Subscription;

  constructor(protected emissionService: EmissionService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.emissionService.query().subscribe((res: HttpResponse<IEmission[]>) => (this.emissions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEmissions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEmission): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEmissions(): void {
    this.eventSubscriber = this.eventManager.subscribe('emissionListModification', () => this.loadAll());
  }

  delete(emission: IEmission): void {
    const modalRef = this.modalService.open(EmissionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.emission = emission;
  }
}
