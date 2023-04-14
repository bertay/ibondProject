import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDetailCalendrier, DetailCalendrier } from 'app/shared/model/detail-calendrier.model';
import { DetailCalendrierService } from './detail-calendrier.service';
import { ICalendrier } from 'app/shared/model/calendrier.model';
import { CalendrierService } from 'app/entities/calendrier/calendrier.service';

@Component({
  selector: 'jhi-detail-calendrier-update',
  templateUrl: './detail-calendrier-update.component.html',
})
export class DetailCalendrierUpdateComponent implements OnInit {
  isSaving = false;
  calendriers: ICalendrier[] = [];
  dateAnnonceDp: any;
  dateAdjudicationDp: any;
  dateEcheanceDp: any;
  dateValeurDp: any;

  editForm = this.fb.group({
    id: [],
    periode: [null, [Validators.required]],
    annee: [null, [Validators.required]],
    dateAnnonce: [null, [Validators.required]],
    dateAdjudication: [],
    dateEcheance: [null, [Validators.required]],
    duree: [],
    volumeEmission: [null, [Validators.required, Validators.min(0)]],
    uniteVolume: [null, [Validators.required]],
    devise: [null, [Validators.required]],
    dateValeur: [],
    nature: [null, [Validators.required]],
    operateur: [null, [Validators.required]],
    dateOperation: [null, [Validators.required]],
    calendrierId: [],
  });

  constructor(
    protected detailCalendrierService: DetailCalendrierService,
    protected calendrierService: CalendrierService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detailCalendrier }) => {
      if (!detailCalendrier.id) {
        const today = moment().startOf('day');
        detailCalendrier.dateOperation = today;
      }

      this.updateForm(detailCalendrier);

      this.calendrierService.query().subscribe((res: HttpResponse<ICalendrier[]>) => (this.calendriers = res.body || []));
    });
  }

  updateForm(detailCalendrier: IDetailCalendrier): void {
    this.editForm.patchValue({
      id: detailCalendrier.id,
      periode: detailCalendrier.periode,
      annee: detailCalendrier.annee,
      dateAnnonce: detailCalendrier.dateAnnonce,
      dateAdjudication: detailCalendrier.dateAdjudication,
      dateEcheance: detailCalendrier.dateEcheance,
      duree: detailCalendrier.duree,
      volumeEmission: detailCalendrier.volumeEmission,
      uniteVolume: detailCalendrier.uniteVolume,
      devise: detailCalendrier.devise,
      dateValeur: detailCalendrier.dateValeur,
      nature: detailCalendrier.nature,
      operateur: detailCalendrier.operateur,
      dateOperation: detailCalendrier.dateOperation ? detailCalendrier.dateOperation.format(DATE_TIME_FORMAT) : null,
      calendrierId: detailCalendrier.calendrierId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const detailCalendrier = this.createFromForm();
    if (detailCalendrier.id !== undefined) {
      this.subscribeToSaveResponse(this.detailCalendrierService.update(detailCalendrier));
    } else {
      this.subscribeToSaveResponse(this.detailCalendrierService.create(detailCalendrier));
    }
  }

  private createFromForm(): IDetailCalendrier {
    return {
      ...new DetailCalendrier(),
      id: this.editForm.get(['id'])!.value,
      periode: this.editForm.get(['periode'])!.value,
      annee: this.editForm.get(['annee'])!.value,
      dateAnnonce: this.editForm.get(['dateAnnonce'])!.value,
      dateAdjudication: this.editForm.get(['dateAdjudication'])!.value,
      dateEcheance: this.editForm.get(['dateEcheance'])!.value,
      duree: this.editForm.get(['duree'])!.value,
      volumeEmission: this.editForm.get(['volumeEmission'])!.value,
      uniteVolume: this.editForm.get(['uniteVolume'])!.value,
      devise: this.editForm.get(['devise'])!.value,
      dateValeur: this.editForm.get(['dateValeur'])!.value,
      nature: this.editForm.get(['nature'])!.value,
      operateur: this.editForm.get(['operateur'])!.value,
      dateOperation: this.editForm.get(['dateOperation'])!.value
        ? moment(this.editForm.get(['dateOperation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      calendrierId: this.editForm.get(['calendrierId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDetailCalendrier>>): void {
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

  trackById(index: number, item: ICalendrier): any {
    return item.id;
  }
}
