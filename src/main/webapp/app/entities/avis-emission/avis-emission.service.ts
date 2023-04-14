import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAvisEmission } from 'app/shared/model/avis-emission.model';

type EntityResponseType = HttpResponse<IAvisEmission>;
type EntityArrayResponseType = HttpResponse<IAvisEmission[]>;

@Injectable({ providedIn: 'root' })
export class AvisEmissionService {
  public resourceUrl = SERVER_API_URL + 'api/avis-emissions';

  constructor(protected http: HttpClient) {}

  create(avisEmission: IAvisEmission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(avisEmission);
    return this.http
      .post<IAvisEmission>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(avisEmission: IAvisEmission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(avisEmission);
    return this.http
      .put<IAvisEmission>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAvisEmission>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAvisEmission[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(avisEmission: IAvisEmission): IAvisEmission {
    const copy: IAvisEmission = Object.assign({}, avisEmission, {
      dateOperation: avisEmission.dateOperation && avisEmission.dateOperation.isValid() ? avisEmission.dateOperation.toJSON() : undefined,
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
      res.body.forEach((avisEmission: IAvisEmission) => {
        avisEmission.dateOperation = avisEmission.dateOperation ? moment(avisEmission.dateOperation) : undefined;
      });
    }
    return res;
  }
}
