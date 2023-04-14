import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISoumission } from 'app/shared/model/soumission.model';

@Component({
  selector: 'jhi-soumission-detail',
  templateUrl: './soumission-detail.component.html',
})
export class SoumissionDetailComponent implements OnInit {
  soumission: ISoumission | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ soumission }) => (this.soumission = soumission));
  }

  previousState(): void {
    window.history.back();
  }
}
