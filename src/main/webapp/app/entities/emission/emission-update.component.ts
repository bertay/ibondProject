import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEmission, Emission } from 'app/shared/model/emission.model';
import { EmissionService } from './emission.service';
import { IAvisEmission } from 'app/shared/model/avis-emission.model';
import { AvisEmissionService } from 'app/entities/avis-emission/avis-emission.service';
import { INatureTitre } from 'app/shared/model/nature-titre.model';
import { NatureTitreService } from 'app/entities/nature-titre/nature-titre.service';

type SelectableEntity = IAvisEmission | INatureTitre;

@Component({
  selector: 'jhi-emission-update',
  templateUrl: './emission-update.component.html',
})
export class EmissionUpdateComponent implements OnInit {
  isSaving = false;
  avisemissions: IAvisEmission[] = [];
  naturetitres: INatureTitre[] = [];
  dateEmissionDp: any;
  echeanceDp: any;
  dateValeurDp: any;

  editForm = this.fb.group({
    id: [],
    codeEmission: [null, [Validators.required]],
    designationFr: [],
    designationEn: [],
    designationPt: [],
    dateEmission: [null, [Validators.required]],
    echeance: [null, [Validators.required]],
    duree: [],
    remboursement: [],
    formeTitre: [],
    tauxInteret: [null, [Validators.required, Validators.min(0), Validators.max(100)]],
    volumeEmission: [null, [Validators.required, Validators.min(0)]],
    uniteVolume: [null, [Validators.required]],
    valeurNominale: [null, [Validators.required, Validators.min(0)]],
    devise: [null, [Validators.required]],
    quantiteTitre: [null, [Validators.min(0)]],
    rendement: [],
    dateLimite: [null, [Validators.required]],
    lieuSouscription: [null, [Validators.required]],
    dateResultat: [],
    dateReglement: [],
    dateValeur: [],
    operateur: [null, [Validators.required]],
    dateOperation: [null, [Validators.required]],
    avisEmissionId: [],
    natureTitreId: [],
  });

  constructor(
    protected emissionService: EmissionService,
    protected avisEmissionService: AvisEmissionService,
    protected natureTitreService: NatureTitreService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ emission }) => {
      if (!emission.id) {
        const today = moment().startOf('day');
        emission.dateLimite = today;
        emission.dateResultat = today;
        emission.dateReglement = today;
        emission.dateOperation = today;
      }

      this.updateForm(emission);

      this.avisEmissionService.query().subscribe((res: HttpResponse<IAvisEmission[]>) => (this.avisemissions = res.body || []));

      this.natureTitreService.query().subscribe((res: HttpResponse<INatureTitre[]>) => (this.naturetitres = res.body || []));
    });
  }

  updateForm(emission: IEmission): void {
    this.editForm.patchValue({
      id: emission.id,
      codeEmission: emission.codeEmission,
      designationFr: emission.designationFr,
      designationEn: emission.designationEn,
      designationPt: emission.designationPt,
      dateEmission: emission.dateEmission,
      echeance: emission.echeance,
      duree: emission.duree,
      remboursement: emission.remboursement,
      formeTitre: emission.formeTitre,
      tauxInteret: emission.tauxInteret,
      volumeEmission: emission.volumeEmission,
      uniteVolume: emission.uniteVolume,
      valeurNominale: emission.valeurNominale,
      devise: emission.devise,
      quantiteTitre: emission.quantiteTitre,
      rendement: emission.rendement,
      dateLimite: emission.dateLimite ? emission.dateLimite.format(DATE_TIME_FORMAT) : null,
      lieuSouscription: emission.lieuSouscription,
      dateResultat: emission.dateResultat ? emission.dateResultat.format(DATE_TIME_FORMAT) : null,
      dateReglement: emission.dateReglement ? emission.dateReglement.format(DATE_TIME_FORMAT) : null,
      dateValeur: emission.dateValeur,
      operateur: emission.operateur,
      dateOperation: emission.dateOperation ? emission.dateOperation.format(DATE_TIME_FORMAT) : null,
      avisEmissionId: emission.avisEmissionId,
      natureTitreId: emission.natureTitreId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const emission = this.createFromForm();
    if (emission.id !== undefined) {
      this.subscribeToSaveResponse(this.emissionService.update(emission));
    } else {
      this.subscribeToSaveResponse(this.emissionService.create(emission));
    }
  }

  private createFromForm(): IEmission {
    return {
      ...new Emission(),
      id: this.editForm.get(['id'])!.value,
      codeEmission: this.editForm.get(['codeEmission'])!.value,
      designationFr: this.editForm.get(['designationFr'])!.value,
      designationEn: this.editForm.get(['designationEn'])!.value,
      designationPt: this.editForm.get(['designationPt'])!.value,
      dateEmission: this.editForm.get(['dateEmission'])!.value,
      echeance: this.editForm.get(['echeance'])!.value,
      duree: this.editForm.get(['duree'])!.value,
      remboursement: this.editForm.get(['remboursement'])!.value,
      formeTitre: this.editForm.get(['formeTitre'])!.value,
      tauxInteret: this.editForm.get(['tauxInteret'])!.value,
      volumeEmission: this.editForm.get(['volumeEmission'])!.value,
      uniteVolume: this.editForm.get(['uniteVolume'])!.value,
      valeurNominale: this.editForm.get(['valeurNominale'])!.value,
      devise: this.editForm.get(['devise'])!.value,
      quantiteTitre: this.editForm.get(['quantiteTitre'])!.value,
      rendement: this.editForm.get(['rendement'])!.value,
      dateLimite: this.editForm.get(['dateLimite'])!.value ? moment(this.editForm.get(['dateLimite'])!.value, DATE_TIME_FORMAT) : undefined,
      lieuSouscription: this.editForm.get(['lieuSouscription'])!.value,
      dateResultat: this.editForm.get(['dateResultat'])!.value
        ? moment(this.editForm.get(['dateResultat'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dateReglement: this.editForm.get(['dateReglement'])!.value
        ? moment(this.editForm.get(['dateReglement'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dateValeur: this.editForm.get(['dateValeur'])!.value,
      operateur: this.editForm.get(['operateur'])!.value,
      dateOperation: this.editForm.get(['dateOperation'])!.value
        ? moment(this.editForm.get(['dateOperation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      avisEmissionId: this.editForm.get(['avisEmissionId'])!.value,
      natureTitreId: this.editForm.get(['natureTitreId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmission>>): void {
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
