import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INatureTitre } from 'app/shared/model/nature-titre.model';

@Component({
  selector: 'jhi-nature-titre-detail',
  templateUrl: './nature-titre-detail.component.html',
})
export class NatureTitreDetailComponent implements OnInit {
  natureTitre: INatureTitre | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ natureTitre }) => (this.natureTitre = natureTitre));
  }

  previousState(): void {
    window.history.back();
  }
}
