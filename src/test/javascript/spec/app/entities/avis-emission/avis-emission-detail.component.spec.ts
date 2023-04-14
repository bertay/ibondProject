import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { AvisEmissionDetailComponent } from 'app/entities/avis-emission/avis-emission-detail.component';
import { AvisEmission } from 'app/shared/model/avis-emission.model';

describe('Component Tests', () => {
  describe('AvisEmission Management Detail Component', () => {
    let comp: AvisEmissionDetailComponent;
    let fixture: ComponentFixture<AvisEmissionDetailComponent>;
    const route = ({ data: of({ avisEmission: new AvisEmission(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [AvisEmissionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AvisEmissionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AvisEmissionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load avisEmission on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.avisEmission).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
