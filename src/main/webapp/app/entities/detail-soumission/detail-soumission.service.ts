import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDetailSoumission } from 'app/shared/model/detail-soumission.model';

type EntityResponseType = HttpResponse<IDetailSoumission>;
type EntityArrayResponseType = HttpResponse<IDetailSoumission[]>;

@Injectable({ providedIn: 'root' })
export class DetailSoumissionService {
  public resourceUrl = SERVER_API_URL + 'api/detail-soumissions';

  constructor(protected http: HttpClient) {}

  create(detailSoumission: IDetailSoumission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(detailSoumission);
    return this.http
      .post<IDetailSoumission>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(detailSoumission: IDetailSoumission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(detailSoumission);
    return this.http
      .put<IDetailSoumission>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDetailSoumission>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDetailSoumission[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(detailSoumission: IDetailSoumission): IDetailSoumission {
    const copy: IDetailSoumission = Object.assign({}, detailSoumission, {
      dateOperation:
        detailSoumission.dateOperation && detailSoumission.dateOperation.isValid() ? detailSoumission.dateOperation.toJSON() : undefined,
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
      res.body.forEach((detailSoumission: IDetailSoumission) => {
        detailSoumission.dateOperation = detailSoumission.dateOperation ? moment(detailSoumission.dateOperation) : undefined;
      });
    }
    return res;
  }
}
