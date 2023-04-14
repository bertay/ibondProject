import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAdjudication } from 'app/shared/model/adjudication.model';

@Component({
  selector: 'jhi-adjudication-detail',
  templateUrl: './adjudication-detail.component.html',
})
export class AdjudicationDetailComponent implements OnInit {
  adjudication: IAdjudication | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adjudication }) => (this.adjudication = adjudication));
  }

  previousState(): void {
    window.history.back();
  }
}
