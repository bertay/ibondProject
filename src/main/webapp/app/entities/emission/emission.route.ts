import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEmission, Emission } from 'app/shared/model/emission.model';
import { EmissionService } from './emission.service';
import { EmissionComponent } from './emission.component';
import { EmissionDetailComponent } from './emission-detail.component';
import { EmissionUpdateComponent } from './emission-update.component';

@Injectable({ providedIn: 'root' })
export class EmissionResolve implements Resolve<IEmission> {
  constructor(private service: EmissionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEmission> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((emission: HttpResponse<Emission>) => {
          if (emission.body) {
            return of(emission.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Emission());
  }
}

export const emissionRoute: Routes = [
  {
    path: '',
    component: EmissionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.emission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EmissionDetailComponent,
    resolve: {
      emission: EmissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.emission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EmissionUpdateComponent,
    resolve: {
      emission: EmissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.emission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EmissionUpdateComponent,
    resolve: {
      emission: EmissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.emission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
