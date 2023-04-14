import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { NatureTitreService } from 'app/entities/nature-titre/nature-titre.service';
import { INatureTitre, NatureTitre } from 'app/shared/model/nature-titre.model';
import { TypeValeur } from 'app/shared/model/enumerations/type-valeur.model';
import { UniteDuree } from 'app/shared/model/enumerations/unite-duree.model';

describe('Service Tests', () => {
  describe('NatureTitre Service', () => {
    let injector: TestBed;
    let service: NatureTitreService;
    let httpMock: HttpTestingController;
    let elemDefault: INatureTitre;
    let expectedResult: INatureTitre | INatureTitre[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(NatureTitreService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new NatureTitre(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        TypeValeur.FCFA,
        UniteDuree.Jours,
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a NatureTitre', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOperation: currentDate,
          },
          returnedFromService
        );

        service.create(new NatureTitre()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a NatureTitre', () => {
        const returnedFromService = Object.assign(
          {
            codeNature: 'BBBBBB',
            designationFr: 'BBBBBB',
            designationEn: 'BBBBBB',
            designationPt: 'BBBBBB',
            nominalUnitaire: 1,
            uniteValeur: 'BBBBBB',
            natureEcheance: 'BBBBBB',
            operateur: 'BBBBBB',
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOperation: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of NatureTitre', () => {
        const returnedFromService = Object.assign(
          {
            codeNature: 'BBBBBB',
            designationFr: 'BBBBBB',
            designationEn: 'BBBBBB',
            designationPt: 'BBBBBB',
            nominalUnitaire: 1,
            uniteValeur: 'BBBBBB',
            natureEcheance: 'BBBBBB',
            operateur: 'BBBBBB',
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
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

      it('should delete a NatureTitre', () => {
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
