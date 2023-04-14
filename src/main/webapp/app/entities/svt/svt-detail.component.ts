import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISvt } from 'app/shared/model/svt.model';

@Component({
  selector: 'jhi-svt-detail',
  templateUrl: './svt-detail.component.html',
})
export class SvtDetailComponent implements OnInit {
  svt: ISvt | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ svt }) => (this.svt = svt));
  }

  previousState(): void {
    window.history.back();
  }
}
