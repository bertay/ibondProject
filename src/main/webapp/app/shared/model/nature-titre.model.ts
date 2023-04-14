import { Moment } from 'moment';
import { IEmission } from 'app/shared/model/emission.model';
import { TypeValeur } from 'app/shared/model/enumerations/type-valeur.model';
import { UniteDuree } from 'app/shared/model/enumerations/unite-duree.model';

export interface INatureTitre {
  id?: number;
  codeNature?: string;
  designationFr?: string;
  designationEn?: string;
  designationPt?: string;
  nominalUnitaire?: number;
  uniteValeur?: TypeValeur;
  natureEcheance?: UniteDuree;
  operateur?: string;
  dateOperation?: Moment;
  emissions?: IEmission[];
}

export class NatureTitre implements INatureTitre {
  constructor(
    public id?: number,
    public codeNature?: string,
    public designationFr?: string,
    public designationEn?: string,
    public designationPt?: string,
    public nominalUnitaire?: number,
    public uniteValeur?: TypeValeur,
    public natureEcheance?: UniteDuree,
    public operateur?: string,
    public dateOperation?: Moment,
    public emissions?: IEmission[]
  ) {}
}
