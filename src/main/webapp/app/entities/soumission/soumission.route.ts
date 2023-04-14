import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISoumission, Soumission } from 'app/shared/model/soumission.model';
import { SoumissionService } from './soumission.service';
import { SoumissionComponent } from './soumission.component';
import { SoumissionDetailComponent } from './soumission-detail.component';
import { SoumissionUpdateComponent } from './soumission-update.component';

@Injectable({ providedIn: 'root' })
export class SoumissionResolve implements Resolve<ISoumission> {
  constructor(private service: SoumissionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISoumission> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((soumission: HttpResponse<Soumission>) => {
          if (soumission.body) {
            return of(soumission.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Soumission());
  }
}

export const soumissionRoute: Routes = [
  {
    path: '',
    component: SoumissionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.soumission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SoumissionDetailComponent,
    resolve: {
      soumission: SoumissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.soumission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SoumissionUpdateComponent,
    resolve: {
      soumission: SoumissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.soumission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SoumissionUpdateComponent,
    resolve: {
      soumission: SoumissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.soumission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
