import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { AvisEmissionService } from 'app/entities/avis-emission/avis-emission.service';
import { IAvisEmission, AvisEmission } from 'app/shared/model/avis-emission.model';
import { NatureActe } from 'app/shared/model/enumerations/nature-acte.model';
import { EtatActe } from 'app/shared/model/enumerations/etat-acte.model';

describe('Service Tests', () => {
  describe('AvisEmission Service', () => {
    let injector: TestBed;
    let service: AvisEmissionService;
    let httpMock: HttpTestingController;
    let elemDefault: IAvisEmission;
    let expectedResult: IAvisEmission | IAvisEmission[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AvisEmissionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new AvisEmission(
        0,
        NatureActe.Avis,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        EtatActe.Projet,
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

      it('should create a AvisEmission', () => {
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

        service.create(new AvisEmission()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AvisEmission', () => {
        const returnedFromService = Object.assign(
          {
            nature: 'BBBBBB',
            numero: 'BBBBBB',
            reference: 'BBBBBB',
            signataire: 'BBBBBB',
            objetAvisFr: 'BBBBBB',
            objetAvisEn: 'BBBBBB',
            objetAvisPt: 'BBBBBB',
            etatAvis: 'BBBBBB',
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

      it('should return a list of AvisEmission', () => {
        const returnedFromService = Object.assign(
          {
            nature: 'BBBBBB',
            numero: 'BBBBBB',
            reference: 'BBBBBB',
            signataire: 'BBBBBB',
            objetAvisFr: 'BBBBBB',
            objetAvisEn: 'BBBBBB',
            objetAvisPt: 'BBBBBB',
            etatAvis: 'BBBBBB',
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

      it('should delete a AvisEmission', () => {
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
