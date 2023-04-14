import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClassement } from 'app/shared/model/classement.model';

@Component({
  selector: 'jhi-classement-detail',
  templateUrl: './classement-detail.component.html',
})
export class ClassementDetailComponent implements OnInit {
  classement: IClassement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ classement }) => (this.classement = classement));
  }

  previousState(): void {
    window.history.back();
  }
}
