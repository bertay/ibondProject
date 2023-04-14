import { Moment } from 'moment';
import { ISvt } from 'app/shared/model/svt.model';

export interface IPays {
  id?: number;
  codePays?: string;
  designationFr?: string;
  designationEn?: string;
  designationPt?: string;
  operateur?: string;
  dateOperation?: Moment;
  svts?: ISvt[];
}

export class Pays implements IPays {
  constructor(
    public id?: number,
    public codePays?: string,
    public designationFr?: string,
    public designationEn?: string,
    public designationPt?: string,
    public operateur?: string,
    public dateOperation?: Moment,
    public svts?: ISvt[]
  ) {}
}
