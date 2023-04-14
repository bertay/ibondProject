import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { ParametresDetailComponent } from 'app/entities/parametres/parametres-detail.component';
import { Parametres } from 'app/shared/model/parametres.model';

describe('Component Tests', () => {
  describe('Parametres Management Detail Component', () => {
    let comp: ParametresDetailComponent;
    let fixture: ComponentFixture<ParametresDetailComponent>;
    const route = ({ data: of({ parametres: new Parametres(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [ParametresDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ParametresDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ParametresDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load parametres on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.parametres).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
