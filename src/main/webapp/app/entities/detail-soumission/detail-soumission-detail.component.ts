import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDetailSoumission } from 'app/shared/model/detail-soumission.model';

@Component({
  selector: 'jhi-detail-soumission-detail',
  templateUrl: './detail-soumission-detail.component.html',
})
export class DetailSoumissionDetailComponent implements OnInit {
  detailSoumission: IDetailSoumission | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detailSoumission }) => (this.detailSoumission = detailSoumission));
  }

  previousState(): void {
    window.history.back();
  }
}
