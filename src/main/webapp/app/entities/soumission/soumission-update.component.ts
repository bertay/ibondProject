import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISoumission, Soumission } from 'app/shared/model/soumission.model';
import { SoumissionService } from './soumission.service';
import { IEmission } from 'app/shared/model/emission.model';
import { EmissionService } from 'app/entities/emission/emission.service';
import { IReouverture } from 'app/shared/model/reouverture.model';
import { ReouvertureService } from 'app/entities/reouverture/reouverture.service';
import { IRachat } from 'app/shared/model/rachat.model';
import { RachatService } from 'app/entities/rachat/rachat.service';
import { IOnc } from 'app/shared/model/onc.model';
import { OncService } from 'app/entities/onc/onc.service';

type SelectableEntity = IEmission | IReouverture | IRachat | IOnc;

@Component({
  selector: 'jhi-soumission-update',
  templateUrl: './soumission-update.component.html',
})
export class SoumissionUpdateComponent implements OnInit {
  isSaving = false;
  emissions: IEmission[] = [];
  reouvertures: IReouverture[] = [];
  rachats: IRachat[] = [];
  oncs: IOnc[] = [];

  editForm = this.fb.group({
    id: [],
    numAnonymat: [null, [Validators.required]],
    dateSoumission: [null, [Validators.required]],
    nbreSoumission: [null, [Validators.min(0)]],
    operateur: [null, [Validators.required]],
    dateOperation: [null, [Validators.required]],
    emissionId: [],
    reouvertureId: [],
    rachatId: [],
    oncId: [],
  });

  constructor(
    protected soumissionService: SoumissionService,
    protected emissionService: EmissionService,
    protected reouvertureService: ReouvertureService,
    protected rachatService: RachatService,
    protected oncService: OncService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ soumission }) => {
      if (!soumission.id) {
        const today = moment().startOf('day');
        soumission.dateSoumission = today;
        soumission.dateOperation = today;
      }

      this.updateForm(soumission);

      this.emissionService.query().subscribe((res: HttpResponse<IEmission[]>) => (this.emissions = res.body || []));

      this.reouvertureService.query().subscribe((res: HttpResponse<IReouverture[]>) => (this.reouvertures = res.body || []));

      this.rachatService.query().subscribe((res: HttpResponse<IRachat[]>) => (this.rachats = res.body || []));

      this.oncService.query().subscribe((res: HttpResponse<IOnc[]>) => (this.oncs = res.body || []));
    });
  }

  updateForm(soumission: ISoumission): void {
    this.editForm.patchValue({
      id: soumission.id,
      numAnonymat: soumission.numAnonymat,
      dateSoumission: soumission.dateSoumission ? soumission.dateSoumission.format(DATE_TIME_FORMAT) : null,
      nbreSoumission: soumission.nbreSoumission,
      operateur: soumission.operateur,
      dateOperation: soumission.dateOperation ? soumission.dateOperation.format(DATE_TIME_FORMAT) : null,
      emissionId: soumission.emissionId,
      reouvertureId: soumission.reouvertureId,
      rachatId: soumission.rachatId,
      oncId: soumission.oncId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const soumission = this.createFromForm();
    if (soumission.id !== undefined) {
      this.subscribeToSaveResponse(this.soumissionService.update(soumission));
    } else {
      this.subscribeToSaveResponse(this.soumissionService.create(soumission));
    }
  }

  private createFromForm(): ISoumission {
    return {
      ...new Soumission(),
      id: this.editForm.get(['id'])!.value,
      numAnonymat: this.editForm.get(['numAnonymat'])!.value,
      dateSoumission: this.editForm.get(['dateSoumission'])!.value
        ? moment(this.editForm.get(['dateSoumission'])!.value, DATE_TIME_FORMAT)
        : undefined,
      nbreSoumission: this.editForm.get(['nbreSoumission'])!.value,
      operateur: this.editForm.get(['operateur'])!.value,
      dateOperation: this.editForm.get(['dateOperation'])!.value
        ? moment(this.editForm.get(['dateOperation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      emissionId: this.editForm.get(['emissionId'])!.value,
      reouvertureId: this.editForm.get(['reouvertureId'])!.value,
      rachatId: this.editForm.get(['rachatId'])!.value,
      oncId: this.editForm.get(['oncId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISoumission>>): void {
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
