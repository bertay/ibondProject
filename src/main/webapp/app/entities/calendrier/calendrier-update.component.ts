import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICalendrier, Calendrier } from 'app/shared/model/calendrier.model';
import { CalendrierService } from './calendrier.service';

@Component({
  selector: 'jhi-calendrier-update',
  templateUrl: './calendrier-update.component.html',
})
export class CalendrierUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nature: [],
    numero: [],
    reference: [],
    signataire: [],
    titreCalendrierFr: [],
    titreCalendrierEn: [],
    titreCalendrierPt: [],
    etatCalendrier: [],
    operateur: [null, [Validators.required]],
    dateOperation: [null, [Validators.required]],
  });

  constructor(protected calendrierService: CalendrierService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ calendrier }) => {
      if (!calendrier.id) {
        const today = moment().startOf('day');
        calendrier.dateOperation = today;
      }

      this.updateForm(calendrier);
    });
  }

  updateForm(calendrier: ICalendrier): void {
    this.editForm.patchValue({
      id: calendrier.id,
      nature: calendrier.nature,
      numero: calendrier.numero,
      reference: calendrier.reference,
      signataire: calendrier.signataire,
      titreCalendrierFr: calendrier.titreCalendrierFr,
      titreCalendrierEn: calendrier.titreCalendrierEn,
      titreCalendrierPt: calendrier.titreCalendrierPt,
      etatCalendrier: calendrier.etatCalendrier,
      operateur: calendrier.operateur,
      dateOperation: calendrier.dateOperation ? calendrier.dateOperation.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const calendrier = this.createFromForm();
    if (calendrier.id !== undefined) {
      this.subscribeToSaveResponse(this.calendrierService.update(calendrier));
    } else {
      this.subscribeToSaveResponse(this.calendrierService.create(calendrier));
    }
  }

  private createFromForm(): ICalendrier {
    return {
      ...new Calendrier(),
      id: this.editForm.get(['id'])!.value,
      nature: this.editForm.get(['nature'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      reference: this.editForm.get(['reference'])!.value,
      signataire: this.editForm.get(['signataire'])!.value,
      titreCalendrierFr: this.editForm.get(['titreCalendrierFr'])!.value,
      titreCalendrierEn: this.editForm.get(['titreCalendrierEn'])!.value,
      titreCalendrierPt: this.editForm.get(['titreCalendrierPt'])!.value,
      etatCalendrier: this.editForm.get(['etatCalendrier'])!.value,
      operateur: this.editForm.get(['operateur'])!.value,
      dateOperation: this.editForm.get(['dateOperation'])!.value
        ? moment(this.editForm.get(['dateOperation'])!.value, DATE_TIME_FORMAT)
        : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICalendrier>>): void {
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
