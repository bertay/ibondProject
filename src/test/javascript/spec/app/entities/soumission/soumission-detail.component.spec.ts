import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { SoumissionDetailComponent } from 'app/entities/soumission/soumission-detail.component';
import { Soumission } from 'app/shared/model/soumission.model';

describe('Component Tests', () => {
  describe('Soumission Management Detail Component', () => {
    let comp: SoumissionDetailComponent;
    let fixture: ComponentFixture<SoumissionDetailComponent>;
    const route = ({ data: of({ soumission: new Soumission(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [SoumissionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SoumissionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SoumissionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load soumission on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.soumission).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
