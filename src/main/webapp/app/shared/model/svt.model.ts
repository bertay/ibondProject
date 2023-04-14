import { Moment } from 'moment';
import { EtatSvt } from 'app/shared/model/enumerations/etat-svt.model';

export interface ISvt {
  id?: number;
  abreviationFr?: string;
  abreviationEn?: string;
  abreviationPt?: string;
  designationFr?: string;
  designationEn?: string;
  etat?: EtatSvt;
  operateur?: string;
  dateOperation?: Moment;
  paysId?: number;
}

export class Svt implements ISvt {
  constructor(
    public id?: number,
    public abreviationFr?: string,
    public abreviationEn?: string,
    public abreviationPt?: string,
    public designationFr?: string,
    public designationEn?: string,
    public etat?: EtatSvt,
    public operateur?: string,
    public dateOperation?: Moment,
    public paysId?: number
  ) {}
}
