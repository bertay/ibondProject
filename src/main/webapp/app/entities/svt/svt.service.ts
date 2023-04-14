import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISvt } from 'app/shared/model/svt.model';

type EntityResponseType = HttpResponse<ISvt>;
type EntityArrayResponseType = HttpResponse<ISvt[]>;

@Injectable({ providedIn: 'root' })
export class SvtService {
  public resourceUrl = SERVER_API_URL + 'api/svts';

  constructor(protected http: HttpClient) {}

  create(svt: ISvt): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(svt);
    return this.http
      .post<ISvt>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(svt: ISvt): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(svt);
    return this.http
      .put<ISvt>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISvt>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISvt[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(svt: ISvt): ISvt {
    const copy: ISvt = Object.assign({}, svt, {
      dateOperation: svt.dateOperation && svt.dateOperation.isValid() ? svt.dateOperation.toJSON() : undefined,
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
      res.body.forEach((svt: ISvt) => {
        svt.dateOperation = svt.dateOperation ? moment(svt.dateOperation) : undefined;
      });
    }
    return res;
  }
}
