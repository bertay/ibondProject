import { Moment } from 'moment';
import { ISoumission } from 'app/shared/model/soumission.model';
import { IReouverture } from 'app/shared/model/reouverture.model';
import { IRachat } from 'app/shared/model/rachat.model';
import { IOnc } from 'app/shared/model/onc.model';
import { IClassement } from 'app/shared/model/classement.model';
import { IResultat } from 'app/shared/model/resultat.model';
import { IMembreSyndicat } from 'app/shared/model/membre-syndicat.model';
import { UniteNombre } from 'app/shared/model/enumerations/unite-nombre.model';
import { TypeValeur } from 'app/shared/model/enumerations/type-valeur.model';

export interface IEmission {
  id?: number;
  codeEmission?: string;
  designationFr?: string;
  designationEn?: string;
  designationPt?: string;
  dateEmission?: Moment;
  echeance?: Moment;
  duree?: string;
  remboursement?: string;
  formeTitre?: string;
  tauxInteret?: number;
  volumeEmission?: number;
  uniteVolume?: UniteNombre;
  valeurNominale?: number;
  devise?: TypeValeur;
  quantiteTitre?: number;
  rendement?: string;
  dateLimite?: Moment;
  lieuSouscription?: string;
  dateResultat?: Moment;
  dateReglement?: Moment;
  dateValeur?: Moment;
  operateur?: string;
  dateOperation?: Moment;
  soumissions?: ISoumission[];
  reouvertures?: IReouverture[];
  rachats?: IRachat[];
  oncs?: IOnc[];
  classements?: IClassement[];
  resultats?: IResultat[];
  membreSyndicats?: IMembreSyndicat[];
  avisEmissionId?: number;
  natureTitreId?: number;
}

export class Emission implements IEmission {
  constructor(
    public id?: number,
    public codeEmission?: string,
    public designationFr?: string,
    public designationEn?: string,
    public designationPt?: string,
    public dateEmission?: Moment,
    public echeance?: Moment,
    public duree?: string,
    public remboursement?: string,
    public formeTitre?: string,
    public tauxInteret?: number,
    public volumeEmission?: number,
    public uniteVolume?: UniteNombre,
    public valeurNominale?: number,
    public devise?: TypeValeur,
    public quantiteTitre?: number,
    public rendement?: string,
    public dateLimite?: Moment,
    public lieuSouscription?: string,
    public dateResultat?: Moment,
    public dateReglement?: Moment,
    public dateValeur?: Moment,
    public operateur?: string,
    public dateOperation?: Moment,
    public soumissions?: ISoumission[],
    public reouvertures?: IReouverture[],
    public rachats?: IRachat[],
    public oncs?: IOnc[],
    public classements?: IClassement[],
    public resultats?: IResultat[],
    public membreSyndicats?: IMembreSyndicat[],
    public avisEmissionId?: number,
    public natureTitreId?: number
  ) {}
}
