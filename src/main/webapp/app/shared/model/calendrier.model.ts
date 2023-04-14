import { Moment } from 'moment';
import { IDetailCalendrier } from 'app/shared/model/detail-calendrier.model';
import { NatureActe } from 'app/shared/model/enumerations/nature-acte.model';
import { EtatActe } from 'app/shared/model/enumerations/etat-acte.model';

export interface ICalendrier {
  id?: number;
  nature?: NatureActe;
  numero?: string;
  reference?: string;
  signataire?: string;
  titreCalendrierFr?: string;
  titreCalendrierEn?: string;
  titreCalendrierPt?: string;
  etatCalendrier?: EtatActe;
  operateur?: string;
  dateOperation?: Moment;
  detailCalendriers?: IDetailCalendrier[];
}

export class Calendrier implements ICalendrier {
  constructor(
    public id?: number,
    public nature?: NatureActe,
    public numero?: string,
    public reference?: string,
    public signataire?: string,
    public titreCalendrierFr?: string,
    public titreCalendrierEn?: string,
    public titreCalendrierPt?: string,
    public etatCalendrier?: EtatActe,
    public operateur?: string,
    public dateOperation?: Moment,
    public detailCalendriers?: IDetailCalendrier[]
  ) {}
}
