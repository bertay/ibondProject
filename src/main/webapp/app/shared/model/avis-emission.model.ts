import { Moment } from 'moment';
import { IEmission } from 'app/shared/model/emission.model';
import { NatureActe } from 'app/shared/model/enumerations/nature-acte.model';
import { EtatActe } from 'app/shared/model/enumerations/etat-acte.model';

export interface IAvisEmission {
  id?: number;
  nature?: NatureActe;
  numero?: string;
  reference?: string;
  signataire?: string;
  objetAvisFr?: string;
  objetAvisEn?: string;
  objetAvisPt?: string;
  etatAvis?: EtatActe;
  operateur?: string;
  dateOperation?: Moment;
  emissions?: IEmission[];
}

export class AvisEmission implements IAvisEmission {
  constructor(
    public id?: number,
    public nature?: NatureActe,
    public numero?: string,
    public reference?: string,
    public signataire?: string,
    public objetAvisFr?: string,
    public objetAvisEn?: string,
    public objetAvisPt?: string,
    public etatAvis?: EtatActe,
    public operateur?: string,
    public dateOperation?: Moment,
    public emissions?: IEmission[]
  ) {}
}
