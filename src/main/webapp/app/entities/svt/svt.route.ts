import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISvt, Svt } from 'app/shared/model/svt.model';
import { SvtService } from './svt.service';
import { SvtComponent } from './svt.component';
import { SvtDetailComponent } from './svt-detail.component';
import { SvtUpdateComponent } from './svt-update.component';

@Injectable({ providedIn: 'root' })
export class SvtResolve implements Resolve<ISvt> {
  constructor(private service: SvtService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISvt> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((svt: HttpResponse<Svt>) => {
          if (svt.body) {
            return of(svt.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Svt());
  }
}

export const svtRoute: Routes = [
  {
    path: '',
    component: SvtComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.svt.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SvtDetailComponent,
    resolve: {
      svt: SvtResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.svt.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SvtUpdateComponent,
    resolve: {
      svt: SvtResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.svt.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SvtUpdateComponent,
    resolve: {
      svt: SvtResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.svt.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
