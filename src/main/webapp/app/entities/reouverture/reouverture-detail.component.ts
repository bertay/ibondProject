import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReouverture } from 'app/shared/model/reouverture.model';

@Component({
  selector: 'jhi-reouverture-detail',
  templateUrl: './reouverture-detail.component.html',
})
export class ReouvertureDetailComponent implements OnInit {
  reouverture: IReouverture | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reouverture }) => (this.reouverture = reouverture));
  }

  previousState(): void {
    window.history.back();
  }
}
