import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMembreSyndicat, MembreSyndicat } from 'app/shared/model/membre-syndicat.model';
import { MembreSyndicatService } from './membre-syndicat.service';
import { MembreSyndicatComponent } from './membre-syndicat.component';
import { MembreSyndicatDetailComponent } from './membre-syndicat-detail.component';
import { MembreSyndicatUpdateComponent } from './membre-syndicat-update.component';

@Injectable({ providedIn: 'root' })
export class MembreSyndicatResolve implements Resolve<IMembreSyndicat> {
  constructor(private service: MembreSyndicatService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMembreSyndicat> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((membreSyndicat: HttpResponse<MembreSyndicat>) => {
          if (membreSyndicat.body) {
            return of(membreSyndicat.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MembreSyndicat());
  }
}

export const membreSyndicatRoute: Routes = [
  {
    path: '',
    component: MembreSyndicatComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.membreSyndicat.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MembreSyndicatDetailComponent,
    resolve: {
      membreSyndicat: MembreSyndicatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.membreSyndicat.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MembreSyndicatUpdateComponent,
    resolve: {
      membreSyndicat: MembreSyndicatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.membreSyndicat.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MembreSyndicatUpdateComponent,
    resolve: {
      membreSyndicat: MembreSyndicatResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.membreSyndicat.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
