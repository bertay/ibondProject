import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IReouverture, Reouverture } from 'app/shared/model/reouverture.model';
import { ReouvertureService } from './reouverture.service';
import { IEmission } from 'app/shared/model/emission.model';
import { EmissionService } from 'app/entities/emission/emission.service';

@Component({
  selector: 'jhi-reouverture-update',
  templateUrl: './reouverture-update.component.html',
})
export class ReouvertureUpdateComponent implements OnInit {
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
    encoursEmission: [null, [Validators.required, Validators.min(0)]],
    uniteVolume: [null, [Validators.required]],
    montantSollicite: [null, [Validators.required, Validators.min(0)]],
    uniteMontant: [null, [Validators.required]],
    devise: [null, [Validators.required]],
    dateEcheance: [null, [Validators.required]],
    dateValeur: [],
    operateur: [null, [Validators.required]],
    dateOperation: [null, [Validators.required]],
    emissionId: [],
  });

  constructor(
    protected reouvertureService: ReouvertureService,
    protected emissionService: EmissionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reouverture }) => {
      if (!reouverture.id) {
        const today = moment().startOf('day');
        reouverture.dateOperation = today;
      }

      this.updateForm(reouverture);

      this.emissionService.query().subscribe((res: HttpResponse<IEmission[]>) => (this.emissions = res.body || []));
    });
  }

  updateForm(reouverture: IReouverture): void {
    this.editForm.patchValue({
      id: reouverture.id,
      codeValeur: reouverture.codeValeur,
      designationFr: reouverture.designationFr,
      designationEn: reouverture.designationEn,
      designationPt: reouverture.designationPt,
      dateEmission: reouverture.dateEmission,
      tauxInteret: reouverture.tauxInteret,
      encoursEmission: reouverture.encoursEmission,
      uniteVolume: reouverture.uniteVolume,
      montantSollicite: reouverture.montantSollicite,
      uniteMontant: reouverture.uniteMontant,
      devise: reouverture.devise,
      dateEcheance: reouverture.dateEcheance,
      dateValeur: reouverture.dateValeur,
      operateur: reouverture.operateur,
      dateOperation: reouverture.dateOperation ? reouverture.dateOperation.format(DATE_TIME_FORMAT) : null,
      emissionId: reouverture.emissionId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reouverture = this.createFromForm();
    if (reouverture.id !== undefined) {
      this.subscribeToSaveResponse(this.reouvertureService.update(reouverture));
    } else {
      this.subscribeToSaveResponse(this.reouvertureService.create(reouverture));
    }
  }

  private createFromForm(): IReouverture {
    return {
      ...new Reouverture(),
      id: this.editForm.get(['id'])!.value,
      codeValeur: this.editForm.get(['codeValeur'])!.value,
      designationFr: this.editForm.get(['designationFr'])!.value,
      designationEn: this.editForm.get(['designationEn'])!.value,
      designationPt: this.editForm.get(['designationPt'])!.value,
      dateEmission: this.editForm.get(['dateEmission'])!.value,
      tauxInteret: this.editForm.get(['tauxInteret'])!.value,
      encoursEmission: this.editForm.get(['encoursEmission'])!.value,
      uniteVolume: this.editForm.get(['uniteVolume'])!.value,
      montantSollicite: this.editForm.get(['montantSollicite'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReouverture>>): void {
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
