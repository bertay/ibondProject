import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOnc, Onc } from 'app/shared/model/onc.model';
import { OncService } from './onc.service';
import { OncComponent } from './onc.component';
import { OncDetailComponent } from './onc-detail.component';
import { OncUpdateComponent } from './onc-update.component';

@Injectable({ providedIn: 'root' })
export class OncResolve implements Resolve<IOnc> {
  constructor(private service: OncService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOnc> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((onc: HttpResponse<Onc>) => {
          if (onc.body) {
            return of(onc.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Onc());
  }
}

export const oncRoute: Routes = [
  {
    path: '',
    component: OncComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.onc.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OncDetailComponent,
    resolve: {
      onc: OncResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.onc.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OncUpdateComponent,
    resolve: {
      onc: OncResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.onc.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OncUpdateComponent,
    resolve: {
      onc: OncResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.onc.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
