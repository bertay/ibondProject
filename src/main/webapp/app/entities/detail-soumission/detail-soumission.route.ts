import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDetailSoumission, DetailSoumission } from 'app/shared/model/detail-soumission.model';
import { DetailSoumissionService } from './detail-soumission.service';
import { DetailSoumissionComponent } from './detail-soumission.component';
import { DetailSoumissionDetailComponent } from './detail-soumission-detail.component';
import { DetailSoumissionUpdateComponent } from './detail-soumission-update.component';

@Injectable({ providedIn: 'root' })
export class DetailSoumissionResolve implements Resolve<IDetailSoumission> {
  constructor(private service: DetailSoumissionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDetailSoumission> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((detailSoumission: HttpResponse<DetailSoumission>) => {
          if (detailSoumission.body) {
            return of(detailSoumission.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DetailSoumission());
  }
}

export const detailSoumissionRoute: Routes = [
  {
    path: '',
    component: DetailSoumissionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.detailSoumission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DetailSoumissionDetailComponent,
    resolve: {
      detailSoumission: DetailSoumissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.detailSoumission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DetailSoumissionUpdateComponent,
    resolve: {
      detailSoumission: DetailSoumissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.detailSoumission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DetailSoumissionUpdateComponent,
    resolve: {
      detailSoumission: DetailSoumissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.detailSoumission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
