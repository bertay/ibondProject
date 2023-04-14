import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IbondgeneTestModule } from '../../../test.module';
import { SoumissionComponent } from 'app/entities/soumission/soumission.component';
import { SoumissionService } from 'app/entities/soumission/soumission.service';
import { Soumission } from 'app/shared/model/soumission.model';

describe('Component Tests', () => {
  describe('Soumission Management Component', () => {
    let comp: SoumissionComponent;
    let fixture: ComponentFixture<SoumissionComponent>;
    let service: SoumissionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [SoumissionComponent],
      })
        .overrideTemplate(SoumissionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SoumissionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SoumissionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Soumission(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.soumissions && comp.soumissions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
