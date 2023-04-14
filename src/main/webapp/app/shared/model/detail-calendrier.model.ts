import { Moment } from 'moment';
import { UniteNombre } from 'app/shared/model/enumerations/unite-nombre.model';
import { TypeValeur } from 'app/shared/model/enumerations/type-valeur.model';
import { TypeOperation } from 'app/shared/model/enumerations/type-operation.model';

export interface IDetailCalendrier {
  id?: number;
  periode?: string;
  annee?: number;
  dateAnnonce?: Moment;
  dateAdjudication?: Moment;
  dateEcheance?: Moment;
  duree?: string;
  volumeEmission?: number;
  uniteVolume?: UniteNombre;
  devise?: TypeValeur;
  dateValeur?: Moment;
  nature?: TypeOperation;
  operateur?: string;
  dateOperation?: Moment;
  calendrierId?: number;
}

export class DetailCalendrier implements IDetailCalendrier {
  constructor(
    public id?: number,
    public periode?: string,
    public annee?: number,
    public dateAnnonce?: Moment,
    public dateAdjudication?: Moment,
    public dateEcheance?: Moment,
    public duree?: string,
    public volumeEmission?: number,
    public uniteVolume?: UniteNombre,
    public devise?: TypeValeur,
    public dateValeur?: Moment,
    public nature?: TypeOperation,
    public operateur?: string,
    public dateOperation?: Moment,
    public calendrierId?: number
  ) {}
}
