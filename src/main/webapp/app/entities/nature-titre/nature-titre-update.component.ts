import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { INatureTitre, NatureTitre } from 'app/shared/model/nature-titre.model';
import { NatureTitreService } from './nature-titre.service';

@Component({
  selector: 'jhi-nature-titre-update',
  templateUrl: './nature-titre-update.component.html',
})
export class NatureTitreUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeNature: [null, [Validators.required]],
    designationFr: [],
    designationEn: [],
    designationPt: [],
    nominalUnitaire: [null, [Validators.required, Validators.min(0)]],
    uniteValeur: [null, [Validators.required]],
    natureEcheance: [null, [Validators.required]],
    operateur: [null, [Validators.required]],
    dateOperation: [null, [Validators.required]],
  });

  constructor(protected natureTitreService: NatureTitreService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ natureTitre }) => {
      if (!natureTitre.id) {
        const today = moment().startOf('day');
        natureTitre.dateOperation = today;
      }

      this.updateForm(natureTitre);
    });
  }

  updateForm(natureTitre: INatureTitre): void {
    this.editForm.patchValue({
      id: natureTitre.id,
      codeNature: natureTitre.codeNature,
      designationFr: natureTitre.designationFr,
      designationEn: natureTitre.designationEn,
      designationPt: natureTitre.designationPt,
      nominalUnitaire: natureTitre.nominalUnitaire,
      uniteValeur: natureTitre.uniteValeur,
      natureEcheance: natureTitre.natureEcheance,
      operateur: natureTitre.operateur,
      dateOperation: natureTitre.dateOperation ? natureTitre.dateOperation.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const natureTitre = this.createFromForm();
    if (natureTitre.id !== undefined) {
      this.subscribeToSaveResponse(this.natureTitreService.update(natureTitre));
    } else {
      this.subscribeToSaveResponse(this.natureTitreService.create(natureTitre));
    }
  }

  private createFromForm(): INatureTitre {
    return {
      ...new NatureTitre(),
      id: this.editForm.get(['id'])!.value,
      codeNature: this.editForm.get(['codeNature'])!.value,
      designationFr: this.editForm.get(['designationFr'])!.value,
      designationEn: this.editForm.get(['designationEn'])!.value,
      designationPt: this.editForm.get(['designationPt'])!.value,
      nominalUnitaire: this.editForm.get(['nominalUnitaire'])!.value,
      uniteValeur: this.editForm.get(['uniteValeur'])!.value,
      natureEcheance: this.editForm.get(['natureEcheance'])!.value,
      operateur: this.editForm.get(['operateur'])!.value,
      dateOperation: this.editForm.get(['dateOperation'])!.value
        ? moment(this.editForm.get(['dateOperation'])!.value, DATE_TIME_FORMAT)
        : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INatureTitre>>): void {
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
