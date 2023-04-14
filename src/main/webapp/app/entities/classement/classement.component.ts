import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IClassement } from 'app/shared/model/classement.model';
import { ClassementService } from './classement.service';
import { ClassementDeleteDialogComponent } from './classement-delete-dialog.component';

@Component({
  selector: 'jhi-classement',
  templateUrl: './classement.component.html',
})
export class ClassementComponent implements OnInit, OnDestroy {
  classements?: IClassement[];
  eventSubscriber?: Subscription;

  constructor(protected classementService: ClassementService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.classementService.query().subscribe((res: HttpResponse<IClassement[]>) => (this.classements = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInClassements();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IClassement): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInClassements(): void {
    this.eventSubscriber = this.eventManager.subscribe('classementListModification', () => this.loadAll());
  }

  delete(classement: IClassement): void {
    const modalRef = this.modalService.open(ClassementDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.classement = classement;
  }
}
