import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IParametres, Parametres } from 'app/shared/model/parametres.model';
import { ParametresService } from './parametres.service';

@Component({
  selector: 'jhi-parametres-update',
  templateUrl: './parametres-update.component.html',
})
export class ParametresUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    adresseServeur: [],
    timbreService1: [],
    timbreService2: [],
    timbreService3: [],
    operateur: [null, [Validators.required]],
    dateOperation: [null, [Validators.required]],
  });

  constructor(protected parametresService: ParametresService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ parametres }) => {
      if (!parametres.id) {
        const today = moment().startOf('day');
        parametres.dateOperation = today;
      }

      this.updateForm(parametres);
    });
  }

  updateForm(parametres: IParametres): void {
    this.editForm.patchValue({
      id: parametres.id,
      adresseServeur: parametres.adresseServeur,
      timbreService1: parametres.timbreService1,
      timbreService2: parametres.timbreService2,
      timbreService3: parametres.timbreService3,
      operateur: parametres.operateur,
      dateOperation: parametres.dateOperation ? parametres.dateOperation.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const parametres = this.createFromForm();
    if (parametres.id !== undefined) {
      this.subscribeToSaveResponse(this.parametresService.update(parametres));
    } else {
      this.subscribeToSaveResponse(this.parametresService.create(parametres));
    }
  }

  private createFromForm(): IParametres {
    return {
      ...new Parametres(),
      id: this.editForm.get(['id'])!.value,
      adresseServeur: this.editForm.get(['adresseServeur'])!.value,
      timbreService1: this.editForm.get(['timbreService1'])!.value,
      timbreService2: this.editForm.get(['timbreService2'])!.value,
      timbreService3: this.editForm.get(['timbreService3'])!.value,
      operateur: this.editForm.get(['operateur'])!.value,
      dateOperation: this.editForm.get(['dateOperation'])!.value
        ? moment(this.editForm.get(['dateOperation'])!.value, DATE_TIME_FORMAT)
        : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParametres>>): void {
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
