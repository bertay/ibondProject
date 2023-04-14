import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEmission } from 'app/shared/model/emission.model';

type EntityResponseType = HttpResponse<IEmission>;
type EntityArrayResponseType = HttpResponse<IEmission[]>;

@Injectable({ providedIn: 'root' })
export class EmissionService {
  public resourceUrl = SERVER_API_URL + 'api/emissions';

  constructor(protected http: HttpClient) {}

  create(emission: IEmission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(emission);
    return this.http
      .post<IEmission>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(emission: IEmission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(emission);
    return this.http
      .put<IEmission>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEmission>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEmission[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(emission: IEmission): IEmission {
    const copy: IEmission = Object.assign({}, emission, {
      dateEmission: emission.dateEmission && emission.dateEmission.isValid() ? emission.dateEmission.format(DATE_FORMAT) : undefined,
      echeance: emission.echeance && emission.echeance.isValid() ? emission.echeance.format(DATE_FORMAT) : undefined,
      dateLimite: emission.dateLimite && emission.dateLimite.isValid() ? emission.dateLimite.toJSON() : undefined,
      dateResultat: emission.dateResultat && emission.dateResultat.isValid() ? emission.dateResultat.toJSON() : undefined,
      dateReglement: emission.dateReglement && emission.dateReglement.isValid() ? emission.dateReglement.toJSON() : undefined,
      dateValeur: emission.dateValeur && emission.dateValeur.isValid() ? emission.dateValeur.format(DATE_FORMAT) : undefined,
      dateOperation: emission.dateOperation && emission.dateOperation.isValid() ? emission.dateOperation.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateEmission = res.body.dateEmission ? moment(res.body.dateEmission) : undefined;
      res.body.echeance = res.body.echeance ? moment(res.body.echeance) : undefined;
      res.body.dateLimite = res.body.dateLimite ? moment(res.body.dateLimite) : undefined;
      res.body.dateResultat = res.body.dateResultat ? moment(res.body.dateResultat) : undefined;
      res.body.dateReglement = res.body.dateReglement ? moment(res.body.dateReglement) : undefined;
      res.body.dateValeur = res.body.dateValeur ? moment(res.body.dateValeur) : undefined;
      res.body.dateOperation = res.body.dateOperation ? moment(res.body.dateOperation) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((emission: IEmission) => {
        emission.dateEmission = emission.dateEmission ? moment(emission.dateEmission) : undefined;
        emission.echeance = emission.echeance ? moment(emission.echeance) : undefined;
        emission.dateLimite = emission.dateLimite ? moment(emission.dateLimite) : undefined;
        emission.dateResultat = emission.dateResultat ? moment(emission.dateResultat) : undefined;
        emission.dateReglement = emission.dateReglement ? moment(emission.dateReglement) : undefined;
        emission.dateValeur = emission.dateValeur ? moment(emission.dateValeur) : undefined;
        emission.dateOperation = emission.dateOperation ? moment(emission.dateOperation) : undefined;
      });
    }
    return res;
  }
}
