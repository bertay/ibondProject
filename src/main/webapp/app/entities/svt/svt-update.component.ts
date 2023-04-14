import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISvt, Svt } from 'app/shared/model/svt.model';
import { SvtService } from './svt.service';
import { IPays } from 'app/shared/model/pays.model';
import { PaysService } from 'app/entities/pays/pays.service';

@Component({
  selector: 'jhi-svt-update',
  templateUrl: './svt-update.component.html',
})
export class SvtUpdateComponent implements OnInit {
  isSaving = false;
  pays: IPays[] = [];

  editForm = this.fb.group({
    id: [],
    abreviationFr: [],
    abreviationEn: [],
    abreviationPt: [],
    designationFr: [null, [Validators.required]],
    designationEn: [],
    etat: [],
    operateur: [null, [Validators.required]],
    dateOperation: [null, [Validators.required]],
    paysId: [],
  });

  constructor(
    protected svtService: SvtService,
    protected paysService: PaysService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ svt }) => {
      if (!svt.id) {
        const today = moment().startOf('day');
        svt.dateOperation = today;
      }

      this.updateForm(svt);

      this.paysService.query().subscribe((res: HttpResponse<IPays[]>) => (this.pays = res.body || []));
    });
  }

  updateForm(svt: ISvt): void {
    this.editForm.patchValue({
      id: svt.id,
      abreviationFr: svt.abreviationFr,
      abreviationEn: svt.abreviationEn,
      abreviationPt: svt.abreviationPt,
      designationFr: svt.designationFr,
      designationEn: svt.designationEn,
      etat: svt.etat,
      operateur: svt.operateur,
      dateOperation: svt.dateOperation ? svt.dateOperation.format(DATE_TIME_FORMAT) : null,
      paysId: svt.paysId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const svt = this.createFromForm();
    if (svt.id !== undefined) {
      this.subscribeToSaveResponse(this.svtService.update(svt));
    } else {
      this.subscribeToSaveResponse(this.svtService.create(svt));
    }
  }

  private createFromForm(): ISvt {
    return {
      ...new Svt(),
      id: this.editForm.get(['id'])!.value,
      abreviationFr: this.editForm.get(['abreviationFr'])!.value,
      abreviationEn: this.editForm.get(['abreviationEn'])!.value,
      abreviationPt: this.editForm.get(['abreviationPt'])!.value,
      designationFr: this.editForm.get(['designationFr'])!.value,
      designationEn: this.editForm.get(['designationEn'])!.value,
      etat: this.editForm.get(['etat'])!.value,
      operateur: this.editForm.get(['operateur'])!.value,
      dateOperation: this.editForm.get(['dateOperation'])!.value
        ? moment(this.editForm.get(['dateOperation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      paysId: this.editForm.get(['paysId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISvt>>): void {
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

  trackById(index: number, item: IPays): any {
    return item.id;
  }
}
