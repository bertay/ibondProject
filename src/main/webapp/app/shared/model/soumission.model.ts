import { Moment } from 'moment';
import { IDetailSoumission } from 'app/shared/model/detail-soumission.model';

export interface ISoumission {
  id?: number;
  numAnonymat?: string;
  dateSoumission?: Moment;
  nbreSoumission?: number;
  operateur?: string;
  dateOperation?: Moment;
  detailSoumissions?: IDetailSoumission[];
  emissionId?: number;
  reouvertureId?: number;
  rachatId?: number;
  oncId?: number;
}

export class Soumission implements ISoumission {
  constructor(
    public id?: number,
    public numAnonymat?: string,
    public dateSoumission?: Moment,
    public nbreSoumission?: number,
    public operateur?: string,
    public dateOperation?: Moment,
    public detailSoumissions?: IDetailSoumission[],
    public emissionId?: number,
    public reouvertureId?: number,
    public rachatId?: number,
    public oncId?: number
  ) {}
}
