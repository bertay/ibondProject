import { UniteNombre } from 'app/shared/model/enumerations/unite-nombre.model';

export interface IResultat {
  id?: number;
  nbreSvtTotal?: number;
  nbreSvtSoumis?: number;
  montantTresor?: number;
  uniteTresor?: UniteNombre;
  montantSoumis?: number;
  uniteSoumis?: UniteNombre;
  montantServi?: number;
  nbreTitreTotal?: number;
  nbreTitreSoumis?: number;
  tauxMinPropose?: number;
  tauxMaxPropose?: number;
  tauxLimite?: number;
  tauxInteretMoyen?: number;
  tauxRendementMoyen?: number;
  tauxCouverture?: number;
  emissionId?: number;
  resultatId?: number;
  rachatId?: number;
  oncId?: number;
}

export class Resultat implements IResultat {
  constructor(
    public id?: number,
    public nbreSvtTotal?: number,
    public nbreSvtSoumis?: number,
    public montantTresor?: number,
    public uniteTresor?: UniteNombre,
    public montantSoumis?: number,
    public uniteSoumis?: UniteNombre,
    public montantServi?: number,
    public nbreTitreTotal?: number,
    public nbreTitreSoumis?: number,
    public tauxMinPropose?: number,
    public tauxMaxPropose?: number,
    public tauxLimite?: number,
    public tauxInteretMoyen?: number,
    public tauxRendementMoyen?: number,
    public tauxCouverture?: number,
    public emissionId?: number,
    public resultatId?: number,
    public rachatId?: number,
    public oncId?: number
  ) {}
}
