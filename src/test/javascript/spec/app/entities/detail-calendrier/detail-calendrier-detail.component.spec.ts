import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { DetailCalendrierDetailComponent } from 'app/entities/detail-calendrier/detail-calendrier-detail.component';
import { DetailCalendrier } from 'app/shared/model/detail-calendrier.model';

describe('Component Tests', () => {
  describe('DetailCalendrier Management Detail Component', () => {
    let comp: DetailCalendrierDetailComponent;
    let fixture: ComponentFixture<DetailCalendrierDetailComponent>;
    const route = ({ data: of({ detailCalendrier: new DetailCalendrier(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [DetailCalendrierDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DetailCalendrierDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DetailCalendrierDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load detailCalendrier on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.detailCalendrier).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
