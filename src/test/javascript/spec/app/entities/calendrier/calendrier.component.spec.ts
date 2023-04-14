import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IbondgeneTestModule } from '../../../test.module';
import { CalendrierComponent } from 'app/entities/calendrier/calendrier.component';
import { CalendrierService } from 'app/entities/calendrier/calendrier.service';
import { Calendrier } from 'app/shared/model/calendrier.model';

describe('Component Tests', () => {
  describe('Calendrier Management Component', () => {
    let comp: CalendrierComponent;
    let fixture: ComponentFixture<CalendrierComponent>;
    let service: CalendrierService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [CalendrierComponent],
      })
        .overrideTemplate(CalendrierComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CalendrierComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CalendrierService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Calendrier(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.calendriers && comp.calendriers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
