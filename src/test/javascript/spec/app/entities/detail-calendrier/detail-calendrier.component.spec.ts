import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IbondgeneTestModule } from '../../../test.module';
import { DetailCalendrierComponent } from 'app/entities/detail-calendrier/detail-calendrier.component';
import { DetailCalendrierService } from 'app/entities/detail-calendrier/detail-calendrier.service';
import { DetailCalendrier } from 'app/shared/model/detail-calendrier.model';

describe('Component Tests', () => {
  describe('DetailCalendrier Management Component', () => {
    let comp: DetailCalendrierComponent;
    let fixture: ComponentFixture<DetailCalendrierComponent>;
    let service: DetailCalendrierService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [DetailCalendrierComponent],
      })
        .overrideTemplate(DetailCalendrierComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DetailCalendrierComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DetailCalendrierService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DetailCalendrier(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.detailCalendriers && comp.detailCalendriers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
