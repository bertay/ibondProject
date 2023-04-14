import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMembreSyndicat, MembreSyndicat } from 'app/shared/model/membre-syndicat.model';
import { MembreSyndicatService } from './membre-syndicat.service';
import { IEmission } from 'app/shared/model/emission.model';
import { EmissionService } from 'app/entities/emission/emission.service';

@Component({
  selector: 'jhi-membre-syndicat-update',
  templateUrl: './membre-syndicat-update.component.html',
})
export class MembreSyndicatUpdateComponent implements OnInit {
  isSaving = false;
  emissions: IEmission[] = [];

  editForm = this.fb.group({
    id: [],
    rang: [],
    commission: [],
    operateur: [null, [Validators.required]],
    dateOperation: [null, [Validators.required]],
    emissionId: [],
  });

  constructor(
    protected membreSyndicatService: MembreSyndicatService,
    protected emissionService: EmissionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ membreSyndicat }) => {
      if (!membreSyndicat.id) {
        const today = moment().startOf('day');
        membreSyndicat.dateOperation = today;
      }

      this.updateForm(membreSyndicat);

      this.emissionService.query().subscribe((res: HttpResponse<IEmission[]>) => (this.emissions = res.body || []));
    });
  }

  updateForm(membreSyndicat: IMembreSyndicat): void {
    this.editForm.patchValue({
      id: membreSyndicat.id,
      rang: membreSyndicat.rang,
      commission: membreSyndicat.commission,
      operateur: membreSyndicat.operateur,
      dateOperation: membreSyndicat.dateOperation ? membreSyndicat.dateOperation.format(DATE_TIME_FORMAT) : null,
      emissionId: membreSyndicat.emissionId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const membreSyndicat = this.createFromForm();
    if (membreSyndicat.id !== undefined) {
      this.subscribeToSaveResponse(this.membreSyndicatService.update(membreSyndicat));
    } else {
      this.subscribeToSaveResponse(this.membreSyndicatService.create(membreSyndicat));
    }
  }

  private createFromForm(): IMembreSyndicat {
    return {
      ...new MembreSyndicat(),
      id: this.editForm.get(['id'])!.value,
      rang: this.editForm.get(['rang'])!.value,
      commission: this.editForm.get(['commission'])!.value,
      operateur: this.editForm.get(['operateur'])!.value,
      dateOperation: this.editForm.get(['dateOperation'])!.value
        ? moment(this.editForm.get(['dateOperation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      emissionId: this.editForm.get(['emissionId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMembreSyndicat>>): void {
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
