import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IParametres, Parametres } from 'app/shared/model/parametres.model';
import { ParametresService } from './parametres.service';
import { ParametresComponent } from './parametres.component';
import { ParametresDetailComponent } from './parametres-detail.component';
import { ParametresUpdateComponent } from './parametres-update.component';

@Injectable({ providedIn: 'root' })
export class ParametresResolve implements Resolve<IParametres> {
  constructor(private service: ParametresService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IParametres> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((parametres: HttpResponse<Parametres>) => {
          if (parametres.body) {
            return of(parametres.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Parametres());
  }
}

export const parametresRoute: Routes = [
  {
    path: '',
    component: ParametresComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.parametres.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ParametresDetailComponent,
    resolve: {
      parametres: ParametresResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.parametres.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ParametresUpdateComponent,
    resolve: {
      parametres: ParametresResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.parametres.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ParametresUpdateComponent,
    resolve: {
      parametres: ParametresResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.parametres.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
