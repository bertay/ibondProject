import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ResultatService } from 'app/entities/resultat/resultat.service';
import { IResultat, Resultat } from 'app/shared/model/resultat.model';
import { UniteNombre } from 'app/shared/model/enumerations/unite-nombre.model';

describe('Service Tests', () => {
  describe('Resultat Service', () => {
    let injector: TestBed;
    let service: ResultatService;
    let httpMock: HttpTestingController;
    let elemDefault: IResultat;
    let expectedResult: IResultat | IResultat[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ResultatService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Resultat(0, 0, 0, 0, UniteNombre.Milles, 0, UniteNombre.Milles, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Resultat', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Resultat()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Resultat', () => {
        const returnedFromService = Object.assign(
          {
            nbreSvtTotal: 1,
            nbreSvtSoumis: 1,
            montantTresor: 1,
            uniteTresor: 'BBBBBB',
            montantSoumis: 1,
            uniteSoumis: 'BBBBBB',
            montantServi: 1,
            nbreTitreTotal: 1,
            nbreTitreSoumis: 1,
            tauxMinPropose: 1,
            tauxMaxPropose: 1,
            tauxLimite: 1,
            tauxInteretMoyen: 1,
            tauxRendementMoyen: 1,
            tauxCouverture: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Resultat', () => {
        const returnedFromService = Object.assign(
          {
            nbreSvtTotal: 1,
            nbreSvtSoumis: 1,
            montantTresor: 1,
            uniteTresor: 'BBBBBB',
            montantSoumis: 1,
            uniteSoumis: 'BBBBBB',
            montantServi: 1,
            nbreTitreTotal: 1,
            nbreTitreSoumis: 1,
            tauxMinPropose: 1,
            tauxMaxPropose: 1,
            tauxLimite: 1,
            tauxInteretMoyen: 1,
            tauxRendementMoyen: 1,
            tauxCouverture: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Resultat', () => {
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
