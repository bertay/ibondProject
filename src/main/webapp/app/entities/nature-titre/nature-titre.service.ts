import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INatureTitre } from 'app/shared/model/nature-titre.model';

type EntityResponseType = HttpResponse<INatureTitre>;
type EntityArrayResponseType = HttpResponse<INatureTitre[]>;

@Injectable({ providedIn: 'root' })
export class NatureTitreService {
  public resourceUrl = SERVER_API_URL + 'api/nature-titres';

  constructor(protected http: HttpClient) {}

  create(natureTitre: INatureTitre): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(natureTitre);
    return this.http
      .post<INatureTitre>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(natureTitre: INatureTitre): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(natureTitre);
    return this.http
      .put<INatureTitre>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<INatureTitre>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INatureTitre[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(natureTitre: INatureTitre): INatureTitre {
    const copy: INatureTitre = Object.assign({}, natureTitre, {
      dateOperation: natureTitre.dateOperation && natureTitre.dateOperation.isValid() ? natureTitre.dateOperation.toJSON() : undefined,
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
      res.body.forEach((natureTitre: INatureTitre) => {
        natureTitre.dateOperation = natureTitre.dateOperation ? moment(natureTitre.dateOperation) : undefined;
      });
    }
    return res;
  }
}
