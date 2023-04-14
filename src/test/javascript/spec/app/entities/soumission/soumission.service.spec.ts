import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SoumissionService } from 'app/entities/soumission/soumission.service';
import { ISoumission, Soumission } from 'app/shared/model/soumission.model';

describe('Service Tests', () => {
  describe('Soumission Service', () => {
    let injector: TestBed;
    let service: SoumissionService;
    let httpMock: HttpTestingController;
    let elemDefault: ISoumission;
    let expectedResult: ISoumission | ISoumission[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SoumissionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Soumission(0, 'AAAAAAA', currentDate, 0, 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateSoumission: currentDate.format(DATE_TIME_FORMAT),
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Soumission', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateSoumission: currentDate.format(DATE_TIME_FORMAT),
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateSoumission: currentDate,
            dateOperation: currentDate,
          },
          returnedFromService
        );

        service.create(new Soumission()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Soumission', () => {
        const returnedFromService = Object.assign(
          {
            numAnonymat: 'BBBBBB',
            dateSoumission: currentDate.format(DATE_TIME_FORMAT),
            nbreSoumission: 1,
            operateur: 'BBBBBB',
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateSoumission: currentDate,
            dateOperation: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Soumission', () => {
        const returnedFromService = Object.assign(
          {
            numAnonymat: 'BBBBBB',
            dateSoumission: currentDate.format(DATE_TIME_FORMAT),
            nbreSoumission: 1,
            operateur: 'BBBBBB',
            dateOperation: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateSoumission: currentDate,
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

      it('should delete a Soumission', () => {
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
