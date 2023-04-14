import { Moment } from 'moment';
import { IClassement } from 'app/shared/model/classement.model';

export interface IDetailSoumission {
  id?: number;
  montantSoumission?: number;
  tauxPropose?: number;
  operateur?: string;
  dateOperation?: Moment;
  classements?: IClassement[];
  soumissionId?: number;
}

export class DetailSoumission implements IDetailSoumission {
  constructor(
    public id?: number,
    public montantSoumission?: number,
    public tauxPropose?: number,
    public operateur?: string,
    public dateOperation?: Moment,
    public classements?: IClassement[],
    public soumissionId?: number
  ) {}
}
