import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IbondgeneTestModule } from '../../../test.module';
import { SvtComponent } from 'app/entities/svt/svt.component';
import { SvtService } from 'app/entities/svt/svt.service';
import { Svt } from 'app/shared/model/svt.model';

describe('Component Tests', () => {
  describe('Svt Management Component', () => {
    let comp: SvtComponent;
    let fixture: ComponentFixture<SvtComponent>;
    let service: SvtService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [SvtComponent],
      })
        .overrideTemplate(SvtComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SvtComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SvtService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Svt(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.svts && comp.svts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
