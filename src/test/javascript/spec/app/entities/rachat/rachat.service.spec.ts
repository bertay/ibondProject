import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { RachatService } from 'app/entities/rachat/rachat.service';
import { IRachat, Rachat } from 'app/shared/model/rachat.model';
import { UniteNombre } from 'app/shared/model/enumerations/unite-nombre.model';
import { TypeValeur } from 'app/shared/model/enumerations/type-valeur.model';

describe('Service Tests', () => {
  describe('Rachat Service', () => {
    let injector: TestBed;
    let service: RachatService;
    let httpMock: HttpTestingController;
    let elemDefault: IRachat;
    let expectedResult: IRachat | IRachat[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RachatService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Rachat(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        0,
        0,
        UniteNombre.Milles,
        TypeValeur.FCFA,
        currentDate,
        currentDate,
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateEmission: currentDate.format(DATE_FORMAT),
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

      it('should create a Rachat', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateEmission: currentDate.format(DATE_FORMAT),
            dateEcheance: currentDate.format(DATE_FORMAT),
            dateValeur: currentDate.format(DATE_FORMAT),
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEmission: currentDate,
            dateEcheance: currentDate,
            dateValeur: currentDate,
            dateOperation: currentDate,
          },
          returnedFromService
        );

        service.create(new Rachat()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Rachat', () => {
        const returnedFromService = Object.assign(
          {
            codeValeur: 'BBBBBB',
            designationFr: 'BBBBBB',
            designationEn: 'BBBBBB',
            designationPt: 'BBBBBB',
            dateEmission: currentDate.format(DATE_FORMAT),
            tauxInteret: 1,
            montantNominal: 1,
            uniteMontant: 'BBBBBB',
            devise: 'BBBBBB',
            dateEcheance: currentDate.format(DATE_FORMAT),
            dateValeur: currentDate.format(DATE_FORMAT),
            operateur: 'BBBBBB',
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEmission: currentDate,
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

      it('should return a list of Rachat', () => {
        const returnedFromService = Object.assign(
          {
            codeValeur: 'BBBBBB',
            designationFr: 'BBBBBB',
            designationEn: 'BBBBBB',
            designationPt: 'BBBBBB',
            dateEmission: currentDate.format(DATE_FORMAT),
            tauxInteret: 1,
            montantNominal: 1,
            uniteMontant: 'BBBBBB',
            devise: 'BBBBBB',
            dateEcheance: currentDate.format(DATE_FORMAT),
            dateValeur: currentDate.format(DATE_FORMAT),
            operateur: 'BBBBBB',
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEmission: currentDate,
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

      it('should delete a Rachat', () => {
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
