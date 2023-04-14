import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IbondgeneTestModule } from '../../../test.module';
import { ClassementComponent } from 'app/entities/classement/classement.component';
import { ClassementService } from 'app/entities/classement/classement.service';
import { Classement } from 'app/shared/model/classement.model';

describe('Component Tests', () => {
  describe('Classement Management Component', () => {
    let comp: ClassementComponent;
    let fixture: ComponentFixture<ClassementComponent>;
    let service: ClassementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [ClassementComponent],
      })
        .overrideTemplate(ClassementComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClassementComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClassementService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Classement(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.classements && comp.classements[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
