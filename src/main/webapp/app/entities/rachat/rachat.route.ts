import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRachat, Rachat } from 'app/shared/model/rachat.model';
import { RachatService } from './rachat.service';
import { RachatComponent } from './rachat.component';
import { RachatDetailComponent } from './rachat-detail.component';
import { RachatUpdateComponent } from './rachat-update.component';

@Injectable({ providedIn: 'root' })
export class RachatResolve implements Resolve<IRachat> {
  constructor(private service: RachatService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRachat> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((rachat: HttpResponse<Rachat>) => {
          if (rachat.body) {
            return of(rachat.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Rachat());
  }
}

export const rachatRoute: Routes = [
  {
    path: '',
    component: RachatComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.rachat.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RachatDetailComponent,
    resolve: {
      rachat: RachatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.rachat.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RachatUpdateComponent,
    resolve: {
      rachat: RachatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.rachat.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RachatUpdateComponent,
    resolve: {
      rachat: RachatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.rachat.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
