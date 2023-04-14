import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDetailCalendrier } from 'app/shared/model/detail-calendrier.model';

@Component({
  selector: 'jhi-detail-calendrier-detail',
  templateUrl: './detail-calendrier-detail.component.html',
})
export class DetailCalendrierDetailComponent implements OnInit {
  detailCalendrier: IDetailCalendrier | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detailCalendrier }) => (this.detailCalendrier = detailCalendrier));
  }

  previousState(): void {
    window.history.back();
  }
}
