import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRachat } from 'app/shared/model/rachat.model';

type EntityResponseType = HttpResponse<IRachat>;
type EntityArrayResponseType = HttpResponse<IRachat[]>;

@Injectable({ providedIn: 'root' })
export class RachatService {
  public resourceUrl = SERVER_API_URL + 'api/rachats';

  constructor(protected http: HttpClient) {}

  create(rachat: IRachat): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rachat);
    return this.http
      .post<IRachat>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(rachat: IRachat): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rachat);
    return this.http
      .put<IRachat>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRachat>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRachat[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(rachat: IRachat): IRachat {
    const copy: IRachat = Object.assign({}, rachat, {
      dateEmission: rachat.dateEmission && rachat.dateEmission.isValid() ? rachat.dateEmission.format(DATE_FORMAT) : undefined,
      dateEcheance: rachat.dateEcheance && rachat.dateEcheance.isValid() ? rachat.dateEcheance.format(DATE_FORMAT) : undefined,
      dateValeur: rachat.dateValeur && rachat.dateValeur.isValid() ? rachat.dateValeur.format(DATE_FORMAT) : undefined,
      dateOperation: rachat.dateOperation && rachat.dateOperation.isValid() ? rachat.dateOperation.toJSON() : undefined,
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
      res.body.forEach((rachat: IRachat) => {
        rachat.dateEmission = rachat.dateEmission ? moment(rachat.dateEmission) : undefined;
        rachat.dateEcheance = rachat.dateEcheance ? moment(rachat.dateEcheance) : undefined;
        rachat.dateValeur = rachat.dateValeur ? moment(rachat.dateValeur) : undefined;
        rachat.dateOperation = rachat.dateOperation ? moment(rachat.dateOperation) : undefined;
      });
    }
    return res;
  }
}
