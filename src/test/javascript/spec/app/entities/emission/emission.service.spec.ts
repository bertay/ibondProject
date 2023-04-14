import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { EmissionService } from 'app/entities/emission/emission.service';
import { IEmission, Emission } from 'app/shared/model/emission.model';
import { UniteNombre } from 'app/shared/model/enumerations/unite-nombre.model';
import { TypeValeur } from 'app/shared/model/enumerations/type-valeur.model';

describe('Service Tests', () => {
  describe('Emission Service', () => {
    let injector: TestBed;
    let service: EmissionService;
    let httpMock: HttpTestingController;
    let elemDefault: IEmission;
    let expectedResult: IEmission | IEmission[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EmissionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Emission(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        UniteNombre.Milles,
        0,
        TypeValeur.FCFA,
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        currentDate,
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
            echeance: currentDate.format(DATE_FORMAT),
            dateLimite: currentDate.format(DATE_TIME_FORMAT),
            dateResultat: currentDate.format(DATE_TIME_FORMAT),
            dateReglement: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a Emission', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateEmission: currentDate.format(DATE_FORMAT),
            echeance: currentDate.format(DATE_FORMAT),
            dateLimite: currentDate.format(DATE_TIME_FORMAT),
            dateResultat: currentDate.format(DATE_TIME_FORMAT),
            dateReglement: currentDate.format(DATE_TIME_FORMAT),
            dateValeur: currentDate.format(DATE_FORMAT),
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEmission: currentDate,
            echeance: currentDate,
            dateLimite: currentDate,
            dateResultat: currentDate,
            dateReglement: currentDate,
            dateValeur: currentDate,
            dateOperation: currentDate,
          },
          returnedFromService
        );

        service.create(new Emission()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Emission', () => {
        const returnedFromService = Object.assign(
          {
            codeEmission: 'BBBBBB',
            designationFr: 'BBBBBB',
            designationEn: 'BBBBBB',
            designationPt: 'BBBBBB',
            dateEmission: currentDate.format(DATE_FORMAT),
            echeance: currentDate.format(DATE_FORMAT),
            duree: 'BBBBBB',
            remboursement: 'BBBBBB',
            formeTitre: 'BBBBBB',
            tauxInteret: 1,
            volumeEmission: 1,
            uniteVolume: 'BBBBBB',
            valeurNominale: 1,
            devise: 'BBBBBB',
            quantiteTitre: 1,
            rendement: 'BBBBBB',
            dateLimite: currentDate.format(DATE_TIME_FORMAT),
            lieuSouscription: 'BBBBBB',
            dateResultat: currentDate.format(DATE_TIME_FORMAT),
            dateReglement: currentDate.format(DATE_TIME_FORMAT),
            dateValeur: currentDate.format(DATE_FORMAT),
            operateur: 'BBBBBB',
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEmission: currentDate,
            echeance: currentDate,
            dateLimite: currentDate,
            dateResultat: currentDate,
            dateReglement: currentDate,
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

      it('should return a list of Emission', () => {
        const returnedFromService = Object.assign(
          {
            codeEmission: 'BBBBBB',
            designationFr: 'BBBBBB',
            designationEn: 'BBBBBB',
            designationPt: 'BBBBBB',
            dateEmission: currentDate.format(DATE_FORMAT),
            echeance: currentDate.format(DATE_FORMAT),
            duree: 'BBBBBB',
            remboursement: 'BBBBBB',
            formeTitre: 'BBBBBB',
            tauxInteret: 1,
            volumeEmission: 1,
            uniteVolume: 'BBBBBB',
            valeurNominale: 1,
            devise: 'BBBBBB',
            quantiteTitre: 1,
            rendement: 'BBBBBB',
            dateLimite: currentDate.format(DATE_TIME_FORMAT),
            lieuSouscription: 'BBBBBB',
            dateResultat: currentDate.format(DATE_TIME_FORMAT),
            dateReglement: currentDate.format(DATE_TIME_FORMAT),
            dateValeur: currentDate.format(DATE_FORMAT),
            operateur: 'BBBBBB',
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateEmission: currentDate,
            echeance: currentDate,
            dateLimite: currentDate,
            dateResultat: currentDate,
            dateReglement: currentDate,
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

      it('should delete a Emission', () => {
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
