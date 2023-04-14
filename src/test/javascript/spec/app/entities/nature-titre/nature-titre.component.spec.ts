import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IbondgeneTestModule } from '../../../test.module';
import { NatureTitreComponent } from 'app/entities/nature-titre/nature-titre.component';
import { NatureTitreService } from 'app/entities/nature-titre/nature-titre.service';
import { NatureTitre } from 'app/shared/model/nature-titre.model';

describe('Component Tests', () => {
  describe('NatureTitre Management Component', () => {
    let comp: NatureTitreComponent;
    let fixture: ComponentFixture<NatureTitreComponent>;
    let service: NatureTitreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [NatureTitreComponent],
      })
        .overrideTemplate(NatureTitreComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NatureTitreComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NatureTitreService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new NatureTitre(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.natureTitres && comp.natureTitres[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
