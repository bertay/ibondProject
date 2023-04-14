import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IbondgeneTestModule } from '../../../test.module';
import { AvisEmissionComponent } from 'app/entities/avis-emission/avis-emission.component';
import { AvisEmissionService } from 'app/entities/avis-emission/avis-emission.service';
import { AvisEmission } from 'app/shared/model/avis-emission.model';

describe('Component Tests', () => {
  describe('AvisEmission Management Component', () => {
    let comp: AvisEmissionComponent;
    let fixture: ComponentFixture<AvisEmissionComponent>;
    let service: AvisEmissionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [AvisEmissionComponent],
      })
        .overrideTemplate(AvisEmissionComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AvisEmissionComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AvisEmissionService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new AvisEmission(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.avisEmissions && comp.avisEmissions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
