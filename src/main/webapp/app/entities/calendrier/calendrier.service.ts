import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICalendrier } from 'app/shared/model/calendrier.model';

type EntityResponseType = HttpResponse<ICalendrier>;
type EntityArrayResponseType = HttpResponse<ICalendrier[]>;

@Injectable({ providedIn: 'root' })
export class CalendrierService {
  public resourceUrl = SERVER_API_URL + 'api/calendriers';

  constructor(protected http: HttpClient) {}

  create(calendrier: ICalendrier): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(calendrier);
    return this.http
      .post<ICalendrier>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(calendrier: ICalendrier): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(calendrier);
    return this.http
      .put<ICalendrier>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICalendrier>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICalendrier[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(calendrier: ICalendrier): ICalendrier {
    const copy: ICalendrier = Object.assign({}, calendrier, {
      dateOperation: calendrier.dateOperation && calendrier.dateOperation.isValid() ? calendrier.dateOperation.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateOperation = res.body.dateOperation ? moment(res.body.dateOperation) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((calendrier: ICalendrier) => {
        calendrier.dateOperation = calendrier.dateOperation ? moment(calendrier.dateOperation) : undefined;
      });
    }
    return res;
  }
}
