import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IResultat, Resultat } from 'app/shared/model/resultat.model';
import { ResultatService } from './resultat.service';
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
  selector: 'jhi-resultat-update',
  templateUrl: './resultat-update.component.html',
})
export class ResultatUpdateComponent implements OnInit {
  isSaving = false;
  emissions: IEmission[] = [];
  reouvertures: IReouverture[] = [];
  rachats: IRachat[] = [];
  oncs: IOnc[] = [];

  editForm = this.fb.group({
    id: [],
    nbreSvtTotal: [null, [Validators.min(0)]],
    nbreSvtSoumis: [null, [Validators.min(0)]],
    montantTresor: [null, [Validators.required, Validators.min(0)]],
    uniteTresor: [],
    montantSoumis: [null, [Validators.required, Validators.min(0)]],
    uniteSoumis: [],
    montantServi: [null, [Validators.required, Validators.min(0)]],
    nbreTitreTotal: [null, [Validators.min(0)]],
    nbreTitreSoumis: [null, [Validators.min(0)]],
    tauxMinPropose: [null, [Validators.min(0), Validators.max(100)]],
    tauxMaxPropose: [null, [Validators.min(0), Validators.max(100)]],
    tauxLimite: [null, [Validators.min(0), Validators.max(100)]],
    tauxInteretMoyen: [null, [Validators.min(0), Validators.max(100)]],
    tauxRendementMoyen: [null, [Validators.min(0), Validators.max(100)]],
    tauxCouverture: [null, [Validators.min(0), Validators.max(100)]],
    emissionId: [],
    resultatId: [],
    rachatId: [],
    oncId: [],
  });

  constructor(
    protected resultatService: ResultatService,
    protected emissionService: EmissionService,
    protected reouvertureService: ReouvertureService,
    protected rachatService: RachatService,
    protected oncService: OncService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ resultat }) => {
      this.updateForm(resultat);

      this.emissionService.query().subscribe((res: HttpResponse<IEmission[]>) => (this.emissions = res.body || []));

      this.reouvertureService.query().subscribe((res: HttpResponse<IReouverture[]>) => (this.reouvertures = res.body || []));

      this.rachatService.query().subscribe((res: HttpResponse<IRachat[]>) => (this.rachats = res.body || []));

      this.oncService.query().subscribe((res: HttpResponse<IOnc[]>) => (this.oncs = res.body || []));
    });
  }

  updateForm(resultat: IResultat): void {
    this.editForm.patchValue({
      id: resultat.id,
      nbreSvtTotal: resultat.nbreSvtTotal,
      nbreSvtSoumis: resultat.nbreSvtSoumis,
      montantTresor: resultat.montantTresor,
      uniteTresor: resultat.uniteTresor,
      montantSoumis: resultat.montantSoumis,
      uniteSoumis: resultat.uniteSoumis,
      montantServi: resultat.montantServi,
      nbreTitreTotal: resultat.nbreTitreTotal,
      nbreTitreSoumis: resultat.nbreTitreSoumis,
      tauxMinPropose: resultat.tauxMinPropose,
      tauxMaxPropose: resultat.tauxMaxPropose,
      tauxLimite: resultat.tauxLimite,
      tauxInteretMoyen: resultat.tauxInteretMoyen,
      tauxRendementMoyen: resultat.tauxRendementMoyen,
      tauxCouverture: resultat.tauxCouverture,
      emissionId: resultat.emissionId,
      resultatId: resultat.resultatId,
      rachatId: resultat.rachatId,
      oncId: resultat.oncId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const resultat = this.createFromForm();
    if (resultat.id !== undefined) {
      this.subscribeToSaveResponse(this.resultatService.update(resultat));
    } else {
      this.subscribeToSaveResponse(this.resultatService.create(resultat));
    }
  }

  private createFromForm(): IResultat {
    return {
      ...new Resultat(),
      id: this.editForm.get(['id'])!.value,
      nbreSvtTotal: this.editForm.get(['nbreSvtTotal'])!.value,
      nbreSvtSoumis: this.editForm.get(['nbreSvtSoumis'])!.value,
      montantTresor: this.editForm.get(['montantTresor'])!.value,
      uniteTresor: this.editForm.get(['uniteTresor'])!.value,
      montantSoumis: this.editForm.get(['montantSoumis'])!.value,
      uniteSoumis: this.editForm.get(['uniteSoumis'])!.value,
      montantServi: this.editForm.get(['montantServi'])!.value,
      nbreTitreTotal: this.editForm.get(['nbreTitreTotal'])!.value,
      nbreTitreSoumis: this.editForm.get(['nbreTitreSoumis'])!.value,
      tauxMinPropose: this.editForm.get(['tauxMinPropose'])!.value,
      tauxMaxPropose: this.editForm.get(['tauxMaxPropose'])!.value,
      tauxLimite: this.editForm.get(['tauxLimite'])!.value,
      tauxInteretMoyen: this.editForm.get(['tauxInteretMoyen'])!.value,
      tauxRendementMoyen: this.editForm.get(['tauxRendementMoyen'])!.value,
      tauxCouverture: this.editForm.get(['tauxCouverture'])!.value,
      emissionId: this.editForm.get(['emissionId'])!.value,
      resultatId: this.editForm.get(['resultatId'])!.value,
      rachatId: this.editForm.get(['rachatId'])!.value,
      oncId: this.editForm.get(['oncId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IResultat>>): void {
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
