import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { ClassementUpdateComponent } from 'app/entities/classement/classement-update.component';
import { ClassementService } from 'app/entities/classement/classement.service';
import { Classement } from 'app/shared/model/classement.model';

describe('Component Tests', () => {
  describe('Classement Management Update Component', () => {
    let comp: ClassementUpdateComponent;
    let fixture: ComponentFixture<ClassementUpdateComponent>;
    let service: ClassementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [ClassementUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ClassementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClassementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClassementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Classement(123);
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
        const entity = new Classement();
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
