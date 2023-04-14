import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { DetailSoumissionDetailComponent } from 'app/entities/detail-soumission/detail-soumission-detail.component';
import { DetailSoumission } from 'app/shared/model/detail-soumission.model';

describe('Component Tests', () => {
  describe('DetailSoumission Management Detail Component', () => {
    let comp: DetailSoumissionDetailComponent;
    let fixture: ComponentFixture<DetailSoumissionDetailComponent>;
    const route = ({ data: of({ detailSoumission: new DetailSoumission(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [DetailSoumissionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DetailSoumissionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DetailSoumissionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load detailSoumission on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.detailSoumission).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
