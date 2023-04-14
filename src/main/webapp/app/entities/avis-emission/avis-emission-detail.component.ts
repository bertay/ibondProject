import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAvisEmission } from 'app/shared/model/avis-emission.model';

@Component({
  selector: 'jhi-avis-emission-detail',
  templateUrl: './avis-emission-detail.component.html',
})
export class AvisEmissionDetailComponent implements OnInit {
  avisEmission: IAvisEmission | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ avisEmission }) => (this.avisEmission = avisEmission));
  }

  previousState(): void {
    window.history.back();
  }
}
