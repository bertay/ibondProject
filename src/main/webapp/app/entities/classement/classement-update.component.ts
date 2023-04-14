import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IClassement, Classement } from 'app/shared/model/classement.model';
import { ClassementService } from './classement.service';
import { IDetailSoumission } from 'app/shared/model/detail-soumission.model';
import { DetailSoumissionService } from 'app/entities/detail-soumission/detail-soumission.service';
import { IEmission } from 'app/shared/model/emission.model';
import { EmissionService } from 'app/entities/emission/emission.service';
import { IReouverture } from 'app/shared/model/reouverture.model';
import { ReouvertureService } from 'app/entities/reouverture/reouverture.service';
import { IRachat } from 'app/shared/model/rachat.model';
import { RachatService } from 'app/entities/rachat/rachat.service';
import { IOnc } from 'app/shared/model/onc.model';
import { OncService } from 'app/entities/onc/onc.service';

type SelectableEntity = IDetailSoumission | IEmission | IReouverture | IRachat | IOnc;

@Component({
  selector: 'jhi-classement-update',
  templateUrl: './classement-update.component.html',
})
export class ClassementUpdateComponent implements OnInit {
  isSaving = false;
  detailsoumissions: IDetailSoumission[] = [];
  emissions: IEmission[] = [];
  reouvertures: IReouverture[] = [];
  rachats: IRachat[] = [];
  oncs: IOnc[] = [];

  editForm = this.fb.group({
    id: [],
    rang: [null, [Validators.required, Validators.min(0)]],
    observationFr: [],
    observationEn: [],
    observationPt: [],
    detailSoumissionId: [],
    emissionId: [],
    reouvertureId: [],
    rachatId: [],
    oncId: [],
  });

  constructor(
    protected classementService: ClassementService,
    protected detailSoumissionService: DetailSoumissionService,
    protected emissionService: EmissionService,
    protected reouvertureService: ReouvertureService,
    protected rachatService: RachatService,
    protected oncService: OncService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ classement }) => {
      this.updateForm(classement);

      this.detailSoumissionService.query().subscribe((res: HttpResponse<IDetailSoumission[]>) => (this.detailsoumissions = res.body || []));

      this.emissionService.query().subscribe((res: HttpResponse<IEmission[]>) => (this.emissions = res.body || []));

      this.reouvertureService.query().subscribe((res: HttpResponse<IReouverture[]>) => (this.reouvertures = res.body || []));

      this.rachatService.query().subscribe((res: HttpResponse<IRachat[]>) => (this.rachats = res.body || []));

      this.oncService.query().subscribe((res: HttpResponse<IOnc[]>) => (this.oncs = res.body || []));
    });
  }

  updateForm(classement: IClassement): void {
    this.editForm.patchValue({
      id: classement.id,
      rang: classement.rang,
      observationFr: classement.observationFr,
      observationEn: classement.observationEn,
      observationPt: classement.observationPt,
      detailSoumissionId: classement.detailSoumissionId,
      emissionId: classement.emissionId,
      reouvertureId: classement.reouvertureId,
      rachatId: classement.rachatId,
      oncId: classement.oncId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const classement = this.createFromForm();
    if (classement.id !== undefined) {
      this.subscribeToSaveResponse(this.classementService.update(classement));
    } else {
      this.subscribeToSaveResponse(this.classementService.create(classement));
    }
  }

  private createFromForm(): IClassement {
    return {
      ...new Classement(),
      id: this.editForm.get(['id'])!.value,
      rang: this.editForm.get(['rang'])!.value,
      observationFr: this.editForm.get(['observationFr'])!.value,
      observationEn: this.editForm.get(['observationEn'])!.value,
      observationPt: this.editForm.get(['observationPt'])!.value,
      detailSoumissionId: this.editForm.get(['detailSoumissionId'])!.value,
      emissionId: this.editForm.get(['emissionId'])!.value,
      reouvertureId: this.editForm.get(['reouvertureId'])!.value,
      rachatId: this.editForm.get(['rachatId'])!.value,
      oncId: this.editForm.get(['oncId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClassement>>): void {
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
