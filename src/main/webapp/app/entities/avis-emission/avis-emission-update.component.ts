import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IAvisEmission, AvisEmission } from 'app/shared/model/avis-emission.model';
import { AvisEmissionService } from './avis-emission.service';

@Component({
  selector: 'jhi-avis-emission-update',
  templateUrl: './avis-emission-update.component.html',
})
export class AvisEmissionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nature: [],
    numero: [],
    reference: [],
    signataire: [],
    objetAvisFr: [],
    objetAvisEn: [],
    objetAvisPt: [],
    etatAvis: [],
    operateur: [null, [Validators.required]],
    dateOperation: [null, [Validators.required]],
  });

  constructor(protected avisEmissionService: AvisEmissionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ avisEmission }) => {
      if (!avisEmission.id) {
        const today = moment().startOf('day');
        avisEmission.dateOperation = today;
      }

      this.updateForm(avisEmission);
    });
  }

  updateForm(avisEmission: IAvisEmission): void {
    this.editForm.patchValue({
      id: avisEmission.id,
      nature: avisEmission.nature,
      numero: avisEmission.numero,
      reference: avisEmission.reference,
      signataire: avisEmission.signataire,
      objetAvisFr: avisEmission.objetAvisFr,
      objetAvisEn: avisEmission.objetAvisEn,
      objetAvisPt: avisEmission.objetAvisPt,
      etatAvis: avisEmission.etatAvis,
      operateur: avisEmission.operateur,
      dateOperation: avisEmission.dateOperation ? avisEmission.dateOperation.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const avisEmission = this.createFromForm();
    if (avisEmission.id !== undefined) {
      this.subscribeToSaveResponse(this.avisEmissionService.update(avisEmission));
    } else {
      this.subscribeToSaveResponse(this.avisEmissionService.create(avisEmission));
    }
  }

  private createFromForm(): IAvisEmission {
    return {
      ...new AvisEmission(),
      id: this.editForm.get(['id'])!.value,
      nature: this.editForm.get(['nature'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      reference: this.editForm.get(['reference'])!.value,
      signataire: this.editForm.get(['signataire'])!.value,
      objetAvisFr: this.editForm.get(['objetAvisFr'])!.value,
      objetAvisEn: this.editForm.get(['objetAvisEn'])!.value,
      objetAvisPt: this.editForm.get(['objetAvisPt'])!.value,
      etatAvis: this.editForm.get(['etatAvis'])!.value,
      operateur: this.editForm.get(['operateur'])!.value,
      dateOperation: this.editForm.get(['dateOperation'])!.value
        ? moment(this.editForm.get(['dateOperation'])!.value, DATE_TIME_FORMAT)
        : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAvisEmission>>): void {
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
