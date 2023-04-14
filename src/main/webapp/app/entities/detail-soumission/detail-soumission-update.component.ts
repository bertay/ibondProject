import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDetailSoumission, DetailSoumission } from 'app/shared/model/detail-soumission.model';
import { DetailSoumissionService } from './detail-soumission.service';
import { ISoumission } from 'app/shared/model/soumission.model';
import { SoumissionService } from 'app/entities/soumission/soumission.service';

@Component({
  selector: 'jhi-detail-soumission-update',
  templateUrl: './detail-soumission-update.component.html',
})
export class DetailSoumissionUpdateComponent implements OnInit {
  isSaving = false;
  soumissions: ISoumission[] = [];

  editForm = this.fb.group({
    id: [],
    montantSoumission: [null, [Validators.required, Validators.min(0)]],
    tauxPropose: [null, [Validators.required, Validators.min(0), Validators.max(100)]],
    operateur: [null, [Validators.required]],
    dateOperation: [null, [Validators.required]],
    soumissionId: [],
  });

  constructor(
    protected detailSoumissionService: DetailSoumissionService,
    protected soumissionService: SoumissionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detailSoumission }) => {
      if (!detailSoumission.id) {
        const today = moment().startOf('day');
        detailSoumission.dateOperation = today;
      }

      this.updateForm(detailSoumission);

      this.soumissionService.query().subscribe((res: HttpResponse<ISoumission[]>) => (this.soumissions = res.body || []));
    });
  }

  updateForm(detailSoumission: IDetailSoumission): void {
    this.editForm.patchValue({
      id: detailSoumission.id,
      montantSoumission: detailSoumission.montantSoumission,
      tauxPropose: detailSoumission.tauxPropose,
      operateur: detailSoumission.operateur,
      dateOperation: detailSoumission.dateOperation ? detailSoumission.dateOperation.format(DATE_TIME_FORMAT) : null,
      soumissionId: detailSoumission.soumissionId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const detailSoumission = this.createFromForm();
    if (detailSoumission.id !== undefined) {
      this.subscribeToSaveResponse(this.detailSoumissionService.update(detailSoumission));
    } else {
      this.subscribeToSaveResponse(this.detailSoumissionService.create(detailSoumission));
    }
  }

  private createFromForm(): IDetailSoumission {
    return {
      ...new DetailSoumission(),
      id: this.editForm.get(['id'])!.value,
      montantSoumission: this.editForm.get(['montantSoumission'])!.value,
      tauxPropose: this.editForm.get(['tauxPropose'])!.value,
      operateur: this.editForm.get(['operateur'])!.value,
      dateOperation: this.editForm.get(['dateOperation'])!.value
        ? moment(this.editForm.get(['dateOperation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      soumissionId: this.editForm.get(['soumissionId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDetailSoumission>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ISoumission): any {
    return item.id;
  }
}
