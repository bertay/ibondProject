export interface IAdjudication {
  id?: number;
  rang?: number;
  observationFr?: string;
  observationEn?: string;
  observationPt?: string;
  reouvertureId?: number;
  rachatId?: number;
  oncId?: number;
}

export class Adjudication implements IAdjudication {
  constructor(
    public id?: number,
    public rang?: number,
    public observationFr?: string,
    public observationEn?: string,
    public observationPt?: string,
    public reouvertureId?: number,
    public rachatId?: number,
    public oncId?: number
  ) {}
}
