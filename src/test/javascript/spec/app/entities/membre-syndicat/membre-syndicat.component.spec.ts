import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IbondgeneTestModule } from '../../../test.module';
import { MembreSyndicatComponent } from 'app/entities/membre-syndicat/membre-syndicat.component';
import { MembreSyndicatService } from 'app/entities/membre-syndicat/membre-syndicat.service';
import { MembreSyndicat } from 'app/shared/model/membre-syndicat.model';

describe('Component Tests', () => {
  describe('MembreSyndicat Management Component', () => {
    let comp: MembreSyndicatComponent;
    let fixture: ComponentFixture<MembreSyndicatComponent>;
    let service: MembreSyndicatService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [MembreSyndicatComponent],
      })
        .overrideTemplate(MembreSyndicatComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MembreSyndicatComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MembreSyndicatService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MembreSyndicat(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.membreSyndicats && comp.membreSyndicats[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
