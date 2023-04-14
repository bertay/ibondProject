import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IbondgeneTestModule } from '../../../test.module';
import { OncComponent } from 'app/entities/onc/onc.component';
import { OncService } from 'app/entities/onc/onc.service';
import { Onc } from 'app/shared/model/onc.model';

describe('Component Tests', () => {
  describe('Onc Management Component', () => {
    let comp: OncComponent;
    let fixture: ComponentFixture<OncComponent>;
    let service: OncService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [OncComponent],
      })
        .overrideTemplate(OncComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OncComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OncService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Onc(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.oncs && comp.oncs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
