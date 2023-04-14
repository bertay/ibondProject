import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IClassement, Classement } from 'app/shared/model/classement.model';
import { ClassementService } from './classement.service';
import { ClassementComponent } from './classement.component';
import { ClassementDetailComponent } from './classement-detail.component';
import { ClassementUpdateComponent } from './classement-update.component';

@Injectable({ providedIn: 'root' })
export class ClassementResolve implements Resolve<IClassement> {
  constructor(private service: ClassementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClassement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((classement: HttpResponse<Classement>) => {
          if (classement.body) {
            return of(classement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Classement());
  }
}

export const classementRoute: Routes = [
  {
    path: '',
    component: ClassementComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.classement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ClassementDetailComponent,
    resolve: {
      classement: ClassementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.classement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ClassementUpdateComponent,
    resolve: {
      classement: ClassementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.classement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ClassementUpdateComponent,
    resolve: {
      classement: ClassementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.classement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
