import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMembreSyndicat } from 'app/shared/model/membre-syndicat.model';

type EntityResponseType = HttpResponse<IMembreSyndicat>;
type EntityArrayResponseType = HttpResponse<IMembreSyndicat[]>;

@Injectable({ providedIn: 'root' })
export class MembreSyndicatService {
  public resourceUrl = SERVER_API_URL + 'api/membre-syndicats';

  constructor(protected http: HttpClient) {}

  create(membreSyndicat: IMembreSyndicat): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(membreSyndicat);
    return this.http
      .post<IMembreSyndicat>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(membreSyndicat: IMembreSyndicat): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(membreSyndicat);
    return this.http
      .put<IMembreSyndicat>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMembreSyndicat>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMembreSyndicat[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(membreSyndicat: IMembreSyndicat): IMembreSyndicat {
    const copy: IMembreSyndicat = Object.assign({}, membreSyndicat, {
      dateOperation:
        membreSyndicat.dateOperation && membreSyndicat.dateOperation.isValid() ? membreSyndicat.dateOperation.toJSON() : undefined,
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
      res.body.forEach((membreSyndicat: IMembreSyndicat) => {
        membreSyndicat.dateOperation = membreSyndicat.dateOperation ? moment(membreSyndicat.dateOperation) : undefined;
      });
    }
    return res;
  }
}
