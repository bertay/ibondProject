import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IbondgeneTestModule } from '../../../test.module';
import { ReouvertureComponent } from 'app/entities/reouverture/reouverture.component';
import { ReouvertureService } from 'app/entities/reouverture/reouverture.service';
import { Reouverture } from 'app/shared/model/reouverture.model';

describe('Component Tests', () => {
  describe('Reouverture Management Component', () => {
    let comp: ReouvertureComponent;
    let fixture: ComponentFixture<ReouvertureComponent>;
    let service: ReouvertureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [ReouvertureComponent],
      })
        .overrideTemplate(ReouvertureComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReouvertureComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReouvertureService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Reouverture(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.reouvertures && comp.reouvertures[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
