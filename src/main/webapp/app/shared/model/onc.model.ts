import { Moment } from 'moment';
import { ISoumission } from 'app/shared/model/soumission.model';
import { IAdjudication } from 'app/shared/model/adjudication.model';
import { IClassement } from 'app/shared/model/classement.model';
import { IResultat } from 'app/shared/model/resultat.model';

export interface IOnc {
  id?: number;
  operateur?: string;
  dateOperation?: Moment;
  soumissions?: ISoumission[];
  adjudications?: IAdjudication[];
  classements?: IClassement[];
  resultats?: IResultat[];
  emissionId?: number;
}

export class Onc implements IOnc {
  constructor(
    public id?: number,
    public operateur?: string,
    public dateOperation?: Moment,
    public soumissions?: ISoumission[],
    public adjudications?: IAdjudication[],
    public classements?: IClassement[],
    public resultats?: IResultat[],
    public emissionId?: number
  ) {}
}
