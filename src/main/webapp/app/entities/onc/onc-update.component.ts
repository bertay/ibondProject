import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOnc, Onc } from 'app/shared/model/onc.model';
import { OncService } from './onc.service';
import { IEmission } from 'app/shared/model/emission.model';
import { EmissionService } from 'app/entities/emission/emission.service';

@Component({
  selector: 'jhi-onc-update',
  templateUrl: './onc-update.component.html',
})
export class OncUpdateComponent implements OnInit {
  isSaving = false;
  emissions: IEmission[] = [];

  editForm = this.fb.group({
    id: [],
    operateur: [null, [Validators.required]],
    dateOperation: [null, [Validators.required]],
    emissionId: [],
  });

  constructor(
    protected oncService: OncService,
    protected emissionService: EmissionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onc }) => {
      if (!onc.id) {
        const today = moment().startOf('day');
        onc.dateOperation = today;
      }

      this.updateForm(onc);

      this.emissionService.query().subscribe((res: HttpResponse<IEmission[]>) => (this.emissions = res.body || []));
    });
  }

  updateForm(onc: IOnc): void {
    this.editForm.patchValue({
      id: onc.id,
      operateur: onc.operateur,
      dateOperation: onc.dateOperation ? onc.dateOperation.format(DATE_TIME_FORMAT) : null,
      emissionId: onc.emissionId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onc = this.createFromForm();
    if (onc.id !== undefined) {
      this.subscribeToSaveResponse(this.oncService.update(onc));
    } else {
      this.subscribeToSaveResponse(this.oncService.create(onc));
    }
  }

  private createFromForm(): IOnc {
    return {
      ...new Onc(),
      id: this.editForm.get(['id'])!.value,
      operateur: this.editForm.get(['operateur'])!.value,
      dateOperation: this.editForm.get(['dateOperation'])!.value
        ? moment(this.editForm.get(['dateOperation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      emissionId: this.editForm.get(['emissionId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnc>>): void {
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

  trackById(index: number, item: IEmission): any {
    return item.id;
  }
}
