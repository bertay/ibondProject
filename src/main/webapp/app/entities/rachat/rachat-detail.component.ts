import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRachat } from 'app/shared/model/rachat.model';

@Component({
  selector: 'jhi-rachat-detail',
  templateUrl: './rachat-detail.component.html',
})
export class RachatDetailComponent implements OnInit {
  rachat: IRachat | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rachat }) => (this.rachat = rachat));
  }

  previousState(): void {
    window.history.back();
  }
}
