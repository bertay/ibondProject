import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRachat, Rachat } from 'app/shared/model/rachat.model';
import { RachatService } from './rachat.service';
import { IEmission } from 'app/shared/model/emission.model';
import { EmissionService } from 'app/entities/emission/emission.service';

@Component({
  selector: 'jhi-rachat-update',
  templateUrl: './rachat-update.component.html',
})
export class RachatUpdateComponent implements OnInit {
  isSaving = false;
  emissions: IEmission[] = [];
  dateEmissionDp: any;
  dateEcheanceDp: any;
  dateValeurDp: any;

  editForm = this.fb.group({
    id: [],
    codeValeur: [null, [Validators.required]],
    designationFr: [],
    designationEn: [],
    designationPt: [],
    dateEmission: [null, [Validators.required]],
    tauxInteret: [null, [Validators.required, Validators.min(0), Validators.max(100)]],
    montantNominal: [null, [Validators.required, Validators.min(0)]],
    uniteMontant: [null, [Validators.required]],
    devise: [null, [Validators.required]],
    dateEcheance: [null, [Validators.required]],
    dateValeur: [],
    operateur: [null, [Validators.required]],
    dateOperation: [null, [Validators.required]],
    emissionId: [],
  });

  constructor(
    protected rachatService: RachatService,
    protected emissionService: EmissionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rachat }) => {
      if (!rachat.id) {
        const today = moment().startOf('day');
        rachat.dateOperation = today;
      }

      this.updateForm(rachat);

      this.emissionService.query().subscribe((res: HttpResponse<IEmission[]>) => (this.emissions = res.body || []));
    });
  }

  updateForm(rachat: IRachat): void {
    this.editForm.patchValue({
      id: rachat.id,
      codeValeur: rachat.codeValeur,
      designationFr: rachat.designationFr,
      designationEn: rachat.designationEn,
      designationPt: rachat.designationPt,
      dateEmission: rachat.dateEmission,
      tauxInteret: rachat.tauxInteret,
      montantNominal: rachat.montantNominal,
      uniteMontant: rachat.uniteMontant,
      devise: rachat.devise,
      dateEcheance: rachat.dateEcheance,
      dateValeur: rachat.dateValeur,
      operateur: rachat.operateur,
      dateOperation: rachat.dateOperation ? rachat.dateOperation.format(DATE_TIME_FORMAT) : null,
      emissionId: rachat.emissionId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rachat = this.createFromForm();
    if (rachat.id !== undefined) {
      this.subscribeToSaveResponse(this.rachatService.update(rachat));
    } else {
      this.subscribeToSaveResponse(this.rachatService.create(rachat));
    }
  }

  private createFromForm(): IRachat {
    return {
      ...new Rachat(),
      id: this.editForm.get(['id'])!.value,
      codeValeur: this.editForm.get(['codeValeur'])!.value,
      designationFr: this.editForm.get(['designationFr'])!.value,
      designationEn: this.editForm.get(['designationEn'])!.value,
      designationPt: this.editForm.get(['designationPt'])!.value,
      dateEmission: this.editForm.get(['dateEmission'])!.value,
      tauxInteret: this.editForm.get(['tauxInteret'])!.value,
      montantNominal: this.editForm.get(['montantNominal'])!.value,
      uniteMontant: this.editForm.get(['uniteMontant'])!.value,
      devise: this.editForm.get(['devise'])!.value,
      dateEcheance: this.editForm.get(['dateEcheance'])!.value,
      dateValeur: this.editForm.get(['dateValeur'])!.value,
      operateur: this.editForm.get(['operateur'])!.value,
      dateOperation: this.editForm.get(['dateOperation'])!.value
        ? moment(this.editForm.get(['dateOperation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      emissionId: this.editForm.get(['emissionId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRachat>>): void {
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
