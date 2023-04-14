import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { EmissionDetailComponent } from 'app/entities/emission/emission-detail.component';
import { Emission } from 'app/shared/model/emission.model';

describe('Component Tests', () => {
  describe('Emission Management Detail Component', () => {
    let comp: EmissionDetailComponent;
    let fixture: ComponentFixture<EmissionDetailComponent>;
    const route = ({ data: of({ emission: new Emission(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [EmissionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EmissionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EmissionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load emission on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.emission).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
