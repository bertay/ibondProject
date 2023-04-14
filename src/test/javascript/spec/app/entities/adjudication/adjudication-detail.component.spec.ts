import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { AdjudicationDetailComponent } from 'app/entities/adjudication/adjudication-detail.component';
import { Adjudication } from 'app/shared/model/adjudication.model';

describe('Component Tests', () => {
  describe('Adjudication Management Detail Component', () => {
    let comp: AdjudicationDetailComponent;
    let fixture: ComponentFixture<AdjudicationDetailComponent>;
    const route = ({ data: of({ adjudication: new Adjudication(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [AdjudicationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AdjudicationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AdjudicationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load adjudication on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.adjudication).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
