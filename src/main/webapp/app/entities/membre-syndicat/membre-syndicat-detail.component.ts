import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMembreSyndicat } from 'app/shared/model/membre-syndicat.model';

@Component({
  selector: 'jhi-membre-syndicat-detail',
  templateUrl: './membre-syndicat-detail.component.html',
})
export class MembreSyndicatDetailComponent implements OnInit {
  membreSyndicat: IMembreSyndicat | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ membreSyndicat }) => (this.membreSyndicat = membreSyndicat));
  }

  previousState(): void {
    window.history.back();
  }
}
