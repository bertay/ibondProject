import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPays } from 'app/shared/model/pays.model';

type EntityResponseType = HttpResponse<IPays>;
type EntityArrayResponseType = HttpResponse<IPays[]>;

@Injectable({ providedIn: 'root' })
export class PaysService {
  public resourceUrl = SERVER_API_URL + 'api/pays';

  constructor(protected http: HttpClient) {}

  create(pays: IPays): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pays);
    return this.http
      .post<IPays>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(pays: IPays): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(pays);
    return this.http
      .put<IPays>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPays>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPays[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(pays: IPays): IPays {
    const copy: IPays = Object.assign({}, pays, {
      dateOperation: pays.dateOperation && pays.dateOperation.isValid() ? pays.dateOperation.toJSON() : undefined,
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
      res.body.forEach((pays: IPays) => {
        pays.dateOperation = pays.dateOperation ? moment(pays.dateOperation) : undefined;
      });
    }
    return res;
  }
}
