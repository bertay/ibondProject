import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IReouverture, Reouverture } from 'app/shared/model/reouverture.model';
import { ReouvertureService } from './reouverture.service';
import { ReouvertureComponent } from './reouverture.component';
import { ReouvertureDetailComponent } from './reouverture-detail.component';
import { ReouvertureUpdateComponent } from './reouverture-update.component';

@Injectable({ providedIn: 'root' })
export class ReouvertureResolve implements Resolve<IReouverture> {
  constructor(private service: ReouvertureService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IReouverture> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((reouverture: HttpResponse<Reouverture>) => {
          if (reouverture.body) {
            return of(reouverture.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Reouverture());
  }
}

export const reouvertureRoute: Routes = [
  {
    path: '',
    component: ReouvertureComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.reouverture.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReouvertureDetailComponent,
    resolve: {
      reouverture: ReouvertureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.reouverture.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReouvertureUpdateComponent,
    resolve: {
      reouverture: ReouvertureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.reouverture.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReouvertureUpdateComponent,
    resolve: {
      reouverture: ReouvertureResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.reouverture.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
