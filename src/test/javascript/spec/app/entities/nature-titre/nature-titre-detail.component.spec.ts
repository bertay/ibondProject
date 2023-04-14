import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { NatureTitreDetailComponent } from 'app/entities/nature-titre/nature-titre-detail.component';
import { NatureTitre } from 'app/shared/model/nature-titre.model';

describe('Component Tests', () => {
  describe('NatureTitre Management Detail Component', () => {
    let comp: NatureTitreDetailComponent;
    let fixture: ComponentFixture<NatureTitreDetailComponent>;
    const route = ({ data: of({ natureTitre: new NatureTitre(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [NatureTitreDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NatureTitreDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NatureTitreDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load natureTitre on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.natureTitre).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
