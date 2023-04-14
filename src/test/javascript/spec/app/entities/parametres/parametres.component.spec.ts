import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IbondgeneTestModule } from '../../../test.module';
import { ParametresComponent } from 'app/entities/parametres/parametres.component';
import { ParametresService } from 'app/entities/parametres/parametres.service';
import { Parametres } from 'app/shared/model/parametres.model';

describe('Component Tests', () => {
  describe('Parametres Management Component', () => {
    let comp: ParametresComponent;
    let fixture: ComponentFixture<ParametresComponent>;
    let service: ParametresService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [ParametresComponent],
      })
        .overrideTemplate(ParametresComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParametresComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParametresService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Parametres(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.parametres && comp.parametres[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
