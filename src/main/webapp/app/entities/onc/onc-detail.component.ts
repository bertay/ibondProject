import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOnc } from 'app/shared/model/onc.model';

@Component({
  selector: 'jhi-onc-detail',
  templateUrl: './onc-detail.component.html',
})
export class OncDetailComponent implements OnInit {
  onc: IOnc | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onc }) => (this.onc = onc));
  }

  previousState(): void {
    window.history.back();
  }
}
