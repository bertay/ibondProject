import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { OncDetailComponent } from 'app/entities/onc/onc-detail.component';
import { Onc } from 'app/shared/model/onc.model';

describe('Component Tests', () => {
  describe('Onc Management Detail Component', () => {
    let comp: OncDetailComponent;
    let fixture: ComponentFixture<OncDetailComponent>;
    const route = ({ data: of({ onc: new Onc(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [OncDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OncDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OncDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load onc on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.onc).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
