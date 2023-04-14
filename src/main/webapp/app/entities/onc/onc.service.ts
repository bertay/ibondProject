import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOnc } from 'app/shared/model/onc.model';

type EntityResponseType = HttpResponse<IOnc>;
type EntityArrayResponseType = HttpResponse<IOnc[]>;

@Injectable({ providedIn: 'root' })
export class OncService {
  public resourceUrl = SERVER_API_URL + 'api/oncs';

  constructor(protected http: HttpClient) {}

  create(onc: IOnc): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onc);
    return this.http
      .post<IOnc>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(onc: IOnc): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onc);
    return this.http
      .put<IOnc>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOnc>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOnc[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(onc: IOnc): IOnc {
    const copy: IOnc = Object.assign({}, onc, {
      dateOperation: onc.dateOperation && onc.dateOperation.isValid() ? onc.dateOperation.toJSON() : undefined,
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
      res.body.forEach((onc: IOnc) => {
        onc.dateOperation = onc.dateOperation ? moment(onc.dateOperation) : undefined;
      });
    }
    return res;
  }
}
