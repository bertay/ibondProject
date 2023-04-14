import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IReouverture } from 'app/shared/model/reouverture.model';

type EntityResponseType = HttpResponse<IReouverture>;
type EntityArrayResponseType = HttpResponse<IReouverture[]>;

@Injectable({ providedIn: 'root' })
export class ReouvertureService {
  public resourceUrl = SERVER_API_URL + 'api/reouvertures';

  constructor(protected http: HttpClient) {}

  create(reouverture: IReouverture): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reouverture);
    return this.http
      .post<IReouverture>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(reouverture: IReouverture): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reouverture);
    return this.http
      .put<IReouverture>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IReouverture>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IReouverture[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(reouverture: IReouverture): IReouverture {
    const copy: IReouverture = Object.assign({}, reouverture, {
      dateEmission:
        reouverture.dateEmission && reouverture.dateEmission.isValid() ? reouverture.dateEmission.format(DATE_FORMAT) : undefined,
      dateEcheance:
        reouverture.dateEcheance && reouverture.dateEcheance.isValid() ? reouverture.dateEcheance.format(DATE_FORMAT) : undefined,
      dateValeur: reouverture.dateValeur && reouverture.dateValeur.isValid() ? reouverture.dateValeur.format(DATE_FORMAT) : undefined,
      dateOperation: reouverture.dateOperation && reouverture.dateOperation.isValid() ? reouverture.dateOperation.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateEmission = res.body.dateEmission ? moment(res.body.dateEmission) : undefined;
      res.body.dateEcheance = res.body.dateEcheance ? moment(res.body.dateEcheance) : undefined;
      res.body.dateValeur = res.body.dateValeur ? moment(res.body.dateValeur) : undefined;
      res.body.dateOperation = res.body.dateOperation ? moment(res.body.dateOperation) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((reouverture: IReouverture) => {
        reouverture.dateEmission = reouverture.dateEmission ? moment(reouverture.dateEmission) : undefined;
        reouverture.dateEcheance = reouverture.dateEcheance ? moment(reouverture.dateEcheance) : undefined;
        reouverture.dateValeur = reouverture.dateValeur ? moment(reouverture.dateValeur) : undefined;
        reouverture.dateOperation = reouverture.dateOperation ? moment(reouverture.dateOperation) : undefined;
      });
    }
    return res;
  }
}
