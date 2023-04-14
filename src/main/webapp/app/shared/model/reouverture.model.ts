import { Moment } from 'moment';
import { ISoumission } from 'app/shared/model/soumission.model';
import { IAdjudication } from 'app/shared/model/adjudication.model';
import { IClassement } from 'app/shared/model/classement.model';
import { IResultat } from 'app/shared/model/resultat.model';
import { UniteNombre } from 'app/shared/model/enumerations/unite-nombre.model';
import { TypeValeur } from 'app/shared/model/enumerations/type-valeur.model';

export interface IReouverture {
  id?: number;
  codeValeur?: string;
  designationFr?: string;
  designationEn?: string;
  designationPt?: string;
  dateEmission?: Moment;
  tauxInteret?: number;
  encoursEmission?: number;
  uniteVolume?: UniteNombre;
  montantSollicite?: number;
  uniteMontant?: UniteNombre;
  devise?: TypeValeur;
  dateEcheance?: Moment;
  dateValeur?: Moment;
  operateur?: string;
  dateOperation?: Moment;
  soumissions?: ISoumission[];
  adjudications?: IAdjudication[];
  classements?: IClassement[];
  resultats?: IResultat[];
  emissionId?: number;
}

export class Reouverture implements IReouverture {
  constructor(
    public id?: number,
    public codeValeur?: string,
    public designationFr?: string,
    public designationEn?: string,
    public designationPt?: string,
    public dateEmission?: Moment,
    public tauxInteret?: number,
    public encoursEmission?: number,
    public uniteVolume?: UniteNombre,
    public montantSollicite?: number,
    public uniteMontant?: UniteNombre,
    public devise?: TypeValeur,
    public dateEcheance?: Moment,
    public dateValeur?: Moment,
    public operateur?: string,
    public dateOperation?: Moment,
    public soumissions?: ISoumission[],
    public adjudications?: IAdjudication[],
    public classements?: IClassement[],
    public resultats?: IResultat[],
    public emissionId?: number
  ) {}
}
