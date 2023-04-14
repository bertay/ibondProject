import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISoumission } from 'app/shared/model/soumission.model';

type EntityResponseType = HttpResponse<ISoumission>;
type EntityArrayResponseType = HttpResponse<ISoumission[]>;

@Injectable({ providedIn: 'root' })
export class SoumissionService {
  public resourceUrl = SERVER_API_URL + 'api/soumissions';

  constructor(protected http: HttpClient) {}

  create(soumission: ISoumission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(soumission);
    return this.http
      .post<ISoumission>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(soumission: ISoumission): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(soumission);
    return this.http
      .put<ISoumission>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISoumission>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISoumission[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(soumission: ISoumission): ISoumission {
    const copy: ISoumission = Object.assign({}, soumission, {
      dateSoumission: soumission.dateSoumission && soumission.dateSoumission.isValid() ? soumission.dateSoumission.toJSON() : undefined,
      dateOperation: soumission.dateOperation && soumission.dateOperation.isValid() ? soumission.dateOperation.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateSoumission = res.body.dateSoumission ? moment(res.body.dateSoumission) : undefined;
      res.body.dateOperation = res.body.dateOperation ? moment(res.body.dateOperation) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((soumission: ISoumission) => {
        soumission.dateSoumission = soumission.dateSoumission ? moment(soumission.dateSoumission) : undefined;
        soumission.dateOperation = soumission.dateOperation ? moment(soumission.dateOperation) : undefined;
      });
    }
    return res;
  }
}
