export interface IClassement {
  id?: number;
  rang?: number;
  observationFr?: string;
  observationEn?: string;
  observationPt?: string;
  detailSoumissionId?: number;
  emissionId?: number;
  reouvertureId?: number;
  rachatId?: number;
  oncId?: number;
}

export class Classement implements IClassement {
  constructor(
    public id?: number,
    public rang?: number,
    public observationFr?: string,
    public observationEn?: string,
    public observationPt?: string,
    public detailSoumissionId?: number,
    public emissionId?: number,
    public reouvertureId?: number,
    public rachatId?: number,
    public oncId?: number
  ) {}
}
