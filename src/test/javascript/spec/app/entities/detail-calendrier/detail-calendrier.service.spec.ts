import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DetailCalendrierService } from 'app/entities/detail-calendrier/detail-calendrier.service';
import { IDetailCalendrier, DetailCalendrier } from 'app/shared/model/detail-calendrier.model';
import { UniteNombre } from 'app/shared/model/enumerations/unite-nombre.model';
import { TypeValeur } from 'app/shared/model/enumerations/type-valeur.model';
import { TypeOperation } from 'app/shared/model/enumerations/type-operation.model';

describe('Service Tests', () => {
  describe('DetailCalendrier Service', () => {
    let injector: TestBed;
    let service: DetailCalendrierService;
    let httpMock: HttpTestingController;
    let elemDefault: IDetailCalendrier;
    let expectedResult: IDetailCalendrier | IDetailCalendrier[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DetailCalendrierService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new DetailCalendrier(
        0,
        'AAAAAAA',
        0,
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        0,
        UniteNombre.Milles,
        TypeValeur.FCFA,
        currentDate,
        TypeOperation.Normale,
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateAnnonce: currentDate.format(DATE_FORMAT),
            dateAdjudication: currentDate.format(DATE_FORMAT),
            dateEcheance: currentDate.format(DATE_FORMAT),
            dateValeur: currentDate.format(DATE_FORMAT),
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a DetailCalendrier', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateAnnonce: currentDate.format(DATE_FORMAT),
            dateAdjudication: currentDate.format(DATE_FORMAT),
            dateEcheance: currentDate.format(DATE_FORMAT),
            dateValeur: currentDate.format(DATE_FORMAT),
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAnnonce: currentDate,
            dateAdjudication: currentDate,
            dateEcheance: currentDate,
            dateValeur: currentDate,
            dateOperation: currentDate,
          },
          returnedFromService
        );

        service.create(new DetailCalendrier()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DetailCalendrier', () => {
        const returnedFromService = Object.assign(
          {
            periode: 'BBBBBB',
            annee: 1,
            dateAnnonce: currentDate.format(DATE_FORMAT),
            dateAdjudication: currentDate.format(DATE_FORMAT),
            dateEcheance: currentDate.format(DATE_FORMAT),
            duree: 'BBBBBB',
            volumeEmission: 1,
            uniteVolume: 'BBBBBB',
            devise: 'BBBBBB',
            dateValeur: currentDate.format(DATE_FORMAT),
            nature: 'BBBBBB',
            operateur: 'BBBBBB',
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAnnonce: currentDate,
            dateAdjudication: currentDate,
            dateEcheance: currentDate,
            dateValeur: currentDate,
            dateOperation: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of DetailCalendrier', () => {
        const returnedFromService = Object.assign(
          {
            periode: 'BBBBBB',
            annee: 1,
            dateAnnonce: currentDate.format(DATE_FORMAT),
            dateAdjudication: currentDate.format(DATE_FORMAT),
            dateEcheance: currentDate.format(DATE_FORMAT),
            duree: 'BBBBBB',
            volumeEmission: 1,
            uniteVolume: 'BBBBBB',
            devise: 'BBBBBB',
            dateValeur: currentDate.format(DATE_FORMAT),
            nature: 'BBBBBB',
            operateur: 'BBBBBB',
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAnnonce: currentDate,
            dateAdjudication: currentDate,
            dateEcheance: currentDate,
            dateValeur: currentDate,
            dateOperation: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a DetailCalendrier', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
