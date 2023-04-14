import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { MembreSyndicatService } from 'app/entities/membre-syndicat/membre-syndicat.service';
import { IMembreSyndicat, MembreSyndicat } from 'app/shared/model/membre-syndicat.model';
import { TypeRang } from 'app/shared/model/enumerations/type-rang.model';

describe('Service Tests', () => {
  describe('MembreSyndicat Service', () => {
    let injector: TestBed;
    let service: MembreSyndicatService;
    let httpMock: HttpTestingController;
    let elemDefault: IMembreSyndicat;
    let expectedResult: IMembreSyndicat | IMembreSyndicat[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MembreSyndicatService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new MembreSyndicat(0, TypeRang.Chef_file, 0, 'AAAAAAA', currentDate);
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

      it('should create a MembreSyndicat', () => {
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

        service.create(new MembreSyndicat()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a MembreSyndicat', () => {
        const returnedFromService = Object.assign(
          {
            rang: 'BBBBBB',
            commission: 1,
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

      it('should return a list of MembreSyndicat', () => {
        const returnedFromService = Object.assign(
          {
            rang: 'BBBBBB',
            commission: 1,
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

      it('should delete a MembreSyndicat', () => {
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
