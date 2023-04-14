import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IbondgeneTestModule } from '../../../test.module';
import { AdjudicationComponent } from 'app/entities/adjudication/adjudication.component';
import { AdjudicationService } from 'app/entities/adjudication/adjudication.service';
import { Adjudication } from 'app/shared/model/adjudication.model';

describe('Component Tests', () => {
  describe('Adjudication Management Component', () => {
    let comp: AdjudicationComponent;
    let fixture: ComponentFixture<AdjudicationComponent>;
    let service: AdjudicationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [AdjudicationComponent],
      })
        .overrideTemplate(AdjudicationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdjudicationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdjudicationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Adjudication(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.adjudications && comp.adjudications[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
