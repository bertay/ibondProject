import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IParametres } from 'app/shared/model/parametres.model';

type EntityResponseType = HttpResponse<IParametres>;
type EntityArrayResponseType = HttpResponse<IParametres[]>;

@Injectable({ providedIn: 'root' })
export class ParametresService {
  public resourceUrl = SERVER_API_URL + 'api/parametres';

  constructor(protected http: HttpClient) {}

  create(parametres: IParametres): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(parametres);
    return this.http
      .post<IParametres>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(parametres: IParametres): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(parametres);
    return this.http
      .put<IParametres>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IParametres>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IParametres[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(parametres: IParametres): IParametres {
    const copy: IParametres = Object.assign({}, parametres, {
      dateOperation: parametres.dateOperation && parametres.dateOperation.isValid() ? parametres.dateOperation.toJSON() : undefined,
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
      res.body.forEach((parametres: IParametres) => {
        parametres.dateOperation = parametres.dateOperation ? moment(parametres.dateOperation) : undefined;
      });
    }
    return res;
  }
}
