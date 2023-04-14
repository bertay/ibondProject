import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PaysService } from 'app/entities/pays/pays.service';
import { IPays, Pays } from 'app/shared/model/pays.model';

describe('Service Tests', () => {
  describe('Pays Service', () => {
    let injector: TestBed;
    let service: PaysService;
    let httpMock: HttpTestingController;
    let elemDefault: IPays;
    let expectedResult: IPays | IPays[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PaysService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Pays(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate);
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

      it('should create a Pays', () => {
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

        service.create(new Pays()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Pays', () => {
        const returnedFromService = Object.assign(
          {
            codePays: 'BBBBBB',
            designationFr: 'BBBBBB',
            designationEn: 'BBBBBB',
            designationPt: 'BBBBBB',
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

      it('should return a list of Pays', () => {
        const returnedFromService = Object.assign(
          {
            codePays: 'BBBBBB',
            designationFr: 'BBBBBB',
            designationEn: 'BBBBBB',
            designationPt: 'BBBBBB',
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

      it('should delete a Pays', () => {
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
