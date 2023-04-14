import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { SoumissionUpdateComponent } from 'app/entities/soumission/soumission-update.component';
import { SoumissionService } from 'app/entities/soumission/soumission.service';
import { Soumission } from 'app/shared/model/soumission.model';

describe('Component Tests', () => {
  describe('Soumission Management Update Component', () => {
    let comp: SoumissionUpdateComponent;
    let fixture: ComponentFixture<SoumissionUpdateComponent>;
    let service: SoumissionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [SoumissionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SoumissionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SoumissionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SoumissionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Soumission(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Soumission();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
