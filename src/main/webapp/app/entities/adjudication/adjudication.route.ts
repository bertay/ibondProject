import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAdjudication, Adjudication } from 'app/shared/model/adjudication.model';
import { AdjudicationService } from './adjudication.service';
import { AdjudicationComponent } from './adjudication.component';
import { AdjudicationDetailComponent } from './adjudication-detail.component';
import { AdjudicationUpdateComponent } from './adjudication-update.component';

@Injectable({ providedIn: 'root' })
export class AdjudicationResolve implements Resolve<IAdjudication> {
  constructor(private service: AdjudicationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAdjudication> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((adjudication: HttpResponse<Adjudication>) => {
          if (adjudication.body) {
            return of(adjudication.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Adjudication());
  }
}

export const adjudicationRoute: Routes = [
  {
    path: '',
    component: AdjudicationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.adjudication.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AdjudicationDetailComponent,
    resolve: {
      adjudication: AdjudicationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.adjudication.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AdjudicationUpdateComponent,
    resolve: {
      adjudication: AdjudicationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.adjudication.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AdjudicationUpdateComponent,
    resolve: {
      adjudication: AdjudicationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.adjudication.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
