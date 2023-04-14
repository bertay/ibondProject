import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INatureTitre } from 'app/shared/model/nature-titre.model';
import { NatureTitreService } from './nature-titre.service';
import { NatureTitreDeleteDialogComponent } from './nature-titre-delete-dialog.component';

@Component({
  selector: 'jhi-nature-titre',
  templateUrl: './nature-titre.component.html',
})
export class NatureTitreComponent implements OnInit, OnDestroy {
  natureTitres?: INatureTitre[];
  eventSubscriber?: Subscription;

  constructor(
    protected natureTitreService: NatureTitreService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.natureTitreService.query().subscribe((res: HttpResponse<INatureTitre[]>) => (this.natureTitres = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInNatureTitres();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INatureTitre): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNatureTitres(): void {
    this.eventSubscriber = this.eventManager.subscribe('natureTitreListModification', () => this.loadAll());
  }

  delete(natureTitre: INatureTitre): void {
    const modalRef = this.modalService.open(NatureTitreDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.natureTitre = natureTitre;
  }
}
