import { Moment } from 'moment';
import { TypeRang } from 'app/shared/model/enumerations/type-rang.model';

export interface IMembreSyndicat {
  id?: number;
  rang?: TypeRang;
  commission?: number;
  operateur?: string;
  dateOperation?: Moment;
  emissionId?: number;
}

export class MembreSyndicat implements IMembreSyndicat {
  constructor(
    public id?: number,
    public rang?: TypeRang,
    public commission?: number,
    public operateur?: string,
    public dateOperation?: Moment,
    public emissionId?: number
  ) {}
}
