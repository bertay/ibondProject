import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { SvtDetailComponent } from 'app/entities/svt/svt-detail.component';
import { Svt } from 'app/shared/model/svt.model';

describe('Component Tests', () => {
  describe('Svt Management Detail Component', () => {
    let comp: SvtDetailComponent;
    let fixture: ComponentFixture<SvtDetailComponent>;
    const route = ({ data: of({ svt: new Svt(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [SvtDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SvtDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SvtDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load svt on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.svt).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
