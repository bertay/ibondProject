import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPays, Pays } from 'app/shared/model/pays.model';
import { PaysService } from './pays.service';

@Component({
  selector: 'jhi-pays-update',
  templateUrl: './pays-update.component.html',
})
export class PaysUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codePays: [],
    designationFr: [],
    designationEn: [],
    designationPt: [],
    operateur: [null, [Validators.required]],
    dateOperation: [null, [Validators.required]],
  });

  constructor(protected paysService: PaysService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pays }) => {
      if (!pays.id) {
        const today = moment().startOf('day');
        pays.dateOperation = today;
      }

      this.updateForm(pays);
    });
  }

  updateForm(pays: IPays): void {
    this.editForm.patchValue({
      id: pays.id,
      codePays: pays.codePays,
      designationFr: pays.designationFr,
      designationEn: pays.designationEn,
      designationPt: pays.designationPt,
      operateur: pays.operateur,
      dateOperation: pays.dateOperation ? pays.dateOperation.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const pays = this.createFromForm();
    if (pays.id !== undefined) {
      this.subscribeToSaveResponse(this.paysService.update(pays));
    } else {
      this.subscribeToSaveResponse(this.paysService.create(pays));
    }
  }

  private createFromForm(): IPays {
    return {
      ...new Pays(),
      id: this.editForm.get(['id'])!.value,
      codePays: this.editForm.get(['codePays'])!.value,
      designationFr: this.editForm.get(['designationFr'])!.value,
      designationEn: this.editForm.get(['designationEn'])!.value,
      designationPt: this.editForm.get(['designationPt'])!.value,
      operateur: this.editForm.get(['operateur'])!.value,
      dateOperation: this.editForm.get(['dateOperation'])!.value
        ? moment(this.editForm.get(['dateOperation'])!.value, DATE_TIME_FORMAT)
        : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPays>>): void {
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
}
