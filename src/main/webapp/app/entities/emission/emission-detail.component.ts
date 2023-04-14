import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEmission } from 'app/shared/model/emission.model';

@Component({
  selector: 'jhi-emission-detail',
  templateUrl: './emission-detail.component.html',
})
export class EmissionDetailComponent implements OnInit {
  emission: IEmission | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ emission }) => (this.emission = emission));
  }

  previousState(): void {
    window.history.back();
  }
}
