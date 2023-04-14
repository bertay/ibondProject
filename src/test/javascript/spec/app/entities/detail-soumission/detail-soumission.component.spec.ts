import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IbondgeneTestModule } from '../../../test.module';
import { DetailSoumissionComponent } from 'app/entities/detail-soumission/detail-soumission.component';
import { DetailSoumissionService } from 'app/entities/detail-soumission/detail-soumission.service';
import { DetailSoumission } from 'app/shared/model/detail-soumission.model';

describe('Component Tests', () => {
  describe('DetailSoumission Management Component', () => {
    let comp: DetailSoumissionComponent;
    let fixture: ComponentFixture<DetailSoumissionComponent>;
    let service: DetailSoumissionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [DetailSoumissionComponent],
      })
        .overrideTemplate(DetailSoumissionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DetailSoumissionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DetailSoumissionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DetailSoumission(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.detailSoumissions && comp.detailSoumissions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
