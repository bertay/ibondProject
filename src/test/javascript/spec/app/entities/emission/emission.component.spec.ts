import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IbondgeneTestModule } from '../../../test.module';
import { EmissionComponent } from 'app/entities/emission/emission.component';
import { EmissionService } from 'app/entities/emission/emission.service';
import { Emission } from 'app/shared/model/emission.model';

describe('Component Tests', () => {
  describe('Emission Management Component', () => {
    let comp: EmissionComponent;
    let fixture: ComponentFixture<EmissionComponent>;
    let service: EmissionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [EmissionComponent],
      })
        .overrideTemplate(EmissionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EmissionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EmissionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Emission(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.emissions && comp.emissions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
