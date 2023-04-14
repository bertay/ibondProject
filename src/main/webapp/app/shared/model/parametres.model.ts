import { Moment } from 'moment';

export interface IParametres {
  id?: number;
  adresseServeur?: string;
  timbreService1?: string;
  timbreService2?: string;
  timbreService3?: string;
  operateur?: string;
  dateOperation?: Moment;
}

export class Parametres implements IParametres {
  constructor(
    public id?: number,
    public adresseServeur?: string,
    public timbreService1?: string,
    public timbreService2?: string,
    public timbreService3?: string,
    public operateur?: string,
    public dateOperation?: Moment
  ) {}
}
