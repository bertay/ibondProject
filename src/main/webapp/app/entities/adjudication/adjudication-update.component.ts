import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAdjudication, Adjudication } from 'app/shared/model/adjudication.model';
import { AdjudicationService } from './adjudication.service';
import { IReouverture } from 'app/shared/model/reouverture.model';
import { ReouvertureService } from 'app/entities/reouverture/reouverture.service';
import { IRachat } from 'app/shared/model/rachat.model';
import { RachatService } from 'app/entities/rachat/rachat.service';
import { IOnc } from 'app/shared/model/onc.model';
import { OncService } from 'app/entities/onc/onc.service';

type SelectableEntity = IReouverture | IRachat | IOnc;

@Component({
  selector: 'jhi-adjudication-update',
  templateUrl: './adjudication-update.component.html',
})
export class AdjudicationUpdateComponent implements OnInit {
  isSaving = false;
  reouvertures: IReouverture[] = [];
  rachats: IRachat[] = [];
  oncs: IOnc[] = [];

  editForm = this.fb.group({
    id: [],
    rang: [null, [Validators.required, Validators.min(0)]],
    observationFr: [],
    observationEn: [],
    observationPt: [],
    reouvertureId: [],
    rachatId: [],
    oncId: [],
  });

  constructor(
    protected adjudicationService: AdjudicationService,
    protected reouvertureService: ReouvertureService,
    protected rachatService: RachatService,
    protected oncService: OncService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adjudication }) => {
      this.updateForm(adjudication);

      this.reouvertureService.query().subscribe((res: HttpResponse<IReouverture[]>) => (this.reouvertures = res.body || []));

      this.rachatService.query().subscribe((res: HttpResponse<IRachat[]>) => (this.rachats = res.body || []));

      this.oncService.query().subscribe((res: HttpResponse<IOnc[]>) => (this.oncs = res.body || []));
    });
  }

  updateForm(adjudication: IAdjudication): void {
    this.editForm.patchValue({
      id: adjudication.id,
      rang: adjudication.rang,
      observationFr: adjudication.observationFr,
      observationEn: adjudication.observationEn,
      observationPt: adjudication.observationPt,
      reouvertureId: adjudication.reouvertureId,
      rachatId: adjudication.rachatId,
      oncId: adjudication.oncId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const adjudication = this.createFromForm();
    if (adjudication.id !== undefined) {
      this.subscribeToSaveResponse(this.adjudicationService.update(adjudication));
    } else {
      this.subscribeToSaveResponse(this.adjudicationService.create(adjudication));
    }
  }

  private createFromForm(): IAdjudication {
    return {
      ...new Adjudication(),
      id: this.editForm.get(['id'])!.value,
      rang: this.editForm.get(['rang'])!.value,
      observationFr: this.editForm.get(['observationFr'])!.value,
      observationEn: this.editForm.get(['observationEn'])!.value,
      observationPt: this.editForm.get(['observationPt'])!.value,
      reouvertureId: this.editForm.get(['reouvertureId'])!.value,
      rachatId: this.editForm.get(['rachatId'])!.value,
      oncId: this.editForm.get(['oncId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdjudication>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
