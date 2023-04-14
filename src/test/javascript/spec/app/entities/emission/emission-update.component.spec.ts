import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { EmissionUpdateComponent } from 'app/entities/emission/emission-update.component';
import { EmissionService } from 'app/entities/emission/emission.service';
import { Emission } from 'app/shared/model/emission.model';

describe('Component Tests', () => {
  describe('Emission Management Update Component', () => {
    let comp: EmissionUpdateComponent;
    let fixture: ComponentFixture<EmissionUpdateComponent>;
    let service: EmissionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [EmissionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EmissionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EmissionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EmissionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Emission(123);
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
        const entity = new Emission();
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
