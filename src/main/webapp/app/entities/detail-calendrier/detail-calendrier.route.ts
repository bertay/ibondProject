import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDetailCalendrier, DetailCalendrier } from 'app/shared/model/detail-calendrier.model';
import { DetailCalendrierService } from './detail-calendrier.service';
import { DetailCalendrierComponent } from './detail-calendrier.component';
import { DetailCalendrierDetailComponent } from './detail-calendrier-detail.component';
import { DetailCalendrierUpdateComponent } from './detail-calendrier-update.component';

@Injectable({ providedIn: 'root' })
export class DetailCalendrierResolve implements Resolve<IDetailCalendrier> {
  constructor(private service: DetailCalendrierService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDetailCalendrier> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((detailCalendrier: HttpResponse<DetailCalendrier>) => {
          if (detailCalendrier.body) {
            return of(detailCalendrier.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DetailCalendrier());
  }
}

export const detailCalendrierRoute: Routes = [
  {
    path: '',
    component: DetailCalendrierComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.detailCalendrier.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DetailCalendrierDetailComponent,
    resolve: {
      detailCalendrier: DetailCalendrierResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.detailCalendrier.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DetailCalendrierUpdateComponent,
    resolve: {
      detailCalendrier: DetailCalendrierResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.detailCalendrier.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DetailCalendrierUpdateComponent,
    resolve: {
      detailCalendrier: DetailCalendrierResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.detailCalendrier.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
