import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IParametres } from 'app/shared/model/parametres.model';

@Component({
  selector: 'jhi-parametres-detail',
  templateUrl: './parametres-detail.component.html',
})
export class ParametresDetailComponent implements OnInit {
  parametres: IParametres | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ parametres }) => (this.parametres = parametres));
  }

  previousState(): void {
    window.history.back();
  }
}
