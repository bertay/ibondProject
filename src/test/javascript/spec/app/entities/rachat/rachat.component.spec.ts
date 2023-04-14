import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IbondgeneTestModule } from '../../../test.module';
import { RachatComponent } from 'app/entities/rachat/rachat.component';
import { RachatService } from 'app/entities/rachat/rachat.service';
import { Rachat } from 'app/shared/model/rachat.model';

describe('Component Tests', () => {
  describe('Rachat Management Component', () => {
    let comp: RachatComponent;
    let fixture: ComponentFixture<RachatComponent>;
    let service: RachatService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [RachatComponent],
      })
        .overrideTemplate(RachatComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RachatComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RachatService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Rachat(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.rachats && comp.rachats[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
