import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IClassement } from 'app/shared/model/classement.model';

type EntityResponseType = HttpResponse<IClassement>;
type EntityArrayResponseType = HttpResponse<IClassement[]>;

@Injectable({ providedIn: 'root' })
export class ClassementService {
  public resourceUrl = SERVER_API_URL + 'api/classements';

  constructor(protected http: HttpClient) {}

  create(classement: IClassement): Observable<EntityResponseType> {
    return this.http.post<IClassement>(this.resourceUrl, classement, { observe: 'response' });
  }

  update(classement: IClassement): Observable<EntityResponseType> {
    return this.http.put<IClassement>(this.resourceUrl, classement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IClassement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IClassement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
