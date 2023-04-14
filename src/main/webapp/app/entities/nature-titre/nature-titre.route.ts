import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INatureTitre, NatureTitre } from 'app/shared/model/nature-titre.model';
import { NatureTitreService } from './nature-titre.service';
import { NatureTitreComponent } from './nature-titre.component';
import { NatureTitreDetailComponent } from './nature-titre-detail.component';
import { NatureTitreUpdateComponent } from './nature-titre-update.component';

@Injectable({ providedIn: 'root' })
export class NatureTitreResolve implements Resolve<INatureTitre> {
  constructor(private service: NatureTitreService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INatureTitre> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((natureTitre: HttpResponse<NatureTitre>) => {
          if (natureTitre.body) {
            return of(natureTitre.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NatureTitre());
  }
}

export const natureTitreRoute: Routes = [
  {
    path: '',
    component: NatureTitreComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.natureTitre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NatureTitreDetailComponent,
    resolve: {
      natureTitre: NatureTitreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.natureTitre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NatureTitreUpdateComponent,
    resolve: {
      natureTitre: NatureTitreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.natureTitre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NatureTitreUpdateComponent,
    resolve: {
      natureTitre: NatureTitreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ibondgeneApp.natureTitre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
