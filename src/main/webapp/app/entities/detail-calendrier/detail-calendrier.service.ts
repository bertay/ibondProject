import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDetailCalendrier } from 'app/shared/model/detail-calendrier.model';

type EntityResponseType = HttpResponse<IDetailCalendrier>;
type EntityArrayResponseType = HttpResponse<IDetailCalendrier[]>;

@Injectable({ providedIn: 'root' })
export class DetailCalendrierService {
  public resourceUrl = SERVER_API_URL + 'api/detail-calendriers';

  constructor(protected http: HttpClient) {}

  create(detailCalendrier: IDetailCalendrier): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(detailCalendrier);
    return this.http
      .post<IDetailCalendrier>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(detailCalendrier: IDetailCalendrier): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(detailCalendrier);
    return this.http
      .put<IDetailCalendrier>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDetailCalendrier>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDetailCalendrier[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(detailCalendrier: IDetailCalendrier): IDetailCalendrier {
    const copy: IDetailCalendrier = Object.assign({}, detailCalendrier, {
      dateAnnonce:
        detailCalendrier.dateAnnonce && detailCalendrier.dateAnnonce.isValid()
          ? detailCalendrier.dateAnnonce.format(DATE_FORMAT)
          : undefined,
      dateAdjudication:
        detailCalendrier.dateAdjudication && detailCalendrier.dateAdjudication.isValid()
          ? detailCalendrier.dateAdjudication.format(DATE_FORMAT)
          : undefined,
      dateEcheance:
        detailCalendrier.dateEcheance && detailCalendrier.dateEcheance.isValid()
          ? detailCalendrier.dateEcheance.format(DATE_FORMAT)
          : undefined,
      dateValeur:
        detailCalendrier.dateValeur && detailCalendrier.dateValeur.isValid() ? detailCalendrier.dateValeur.format(DATE_FORMAT) : undefined,
      dateOperation:
        detailCalendrier.dateOperation && detailCalendrier.dateOperation.isValid() ? detailCalendrier.dateOperation.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateAnnonce = res.body.dateAnnonce ? moment(res.body.dateAnnonce) : undefined;
      res.body.dateAdjudication = res.body.dateAdjudication ? moment(res.body.dateAdjudication) : undefined;
      res.body.dateEcheance = res.body.dateEcheance ? moment(res.body.dateEcheance) : undefined;
      res.body.dateValeur = res.body.dateValeur ? moment(res.body.dateValeur) : undefined;
      res.body.dateOperation = res.body.dateOperation ? moment(res.body.dateOperation) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((detailCalendrier: IDetailCalendrier) => {
        detailCalendrier.dateAnnonce = detailCalendrier.dateAnnonce ? moment(detailCalendrier.dateAnnonce) : undefined;
        detailCalendrier.dateAdjudication = detailCalendrier.dateAdjudication ? moment(detailCalendrier.dateAdjudication) : undefined;
        detailCalendrier.dateEcheance = detailCalendrier.dateEcheance ? moment(detailCalendrier.dateEcheance) : undefined;
        detailCalendrier.dateValeur = detailCalendrier.dateValeur ? moment(detailCalendrier.dateValeur) : undefined;
        detailCalendrier.dateOperation = detailCalendrier.dateOperation ? moment(detailCalendrier.dateOperation) : undefined;
      });
    }
    return res;
  }
}
