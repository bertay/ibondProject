import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAvisEmission, AvisEmission } from 'app/shared/model/avis-emission.model';
import { AvisEmissionService } from './avis-emission.service';
import { AvisEmissionComponent } from './avis-emission.component';
import { AvisEmissionDetailComponent } from './avis-emission-detail.component';
import { AvisEmissionUpdateComponent } from './avis-emission-update.component';

@Injectable({ providedIn: 'root' })
export class AvisEmissionResolve implements Resolve<IAvisEmission> {
  constructor(private service: AvisEmissionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAvisEmission> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((avisEmission: HttpResponse<AvisEmission>) => {
          if (avisEmission.body) {
            return of(avisEmission.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AvisEmission());
  }
}

export const avisEmissionRoute: Routes = [
  {
    path: '',
    component: AvisEmissionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.avisEmission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AvisEmissionDetailComponent,
    resolve: {
      avisEmission: AvisEmissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.avisEmission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AvisEmissionUpdateComponent,
    resolve: {
      avisEmission: AvisEmissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.avisEmission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AvisEmissionUpdateComponent,
    resolve: {
      avisEmission: AvisEmissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.avisEmission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
