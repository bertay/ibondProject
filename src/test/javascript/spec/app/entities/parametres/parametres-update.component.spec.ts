import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { ParametresUpdateComponent } from 'app/entities/parametres/parametres-update.component';
import { ParametresService } from 'app/entities/parametres/parametres.service';
import { Parametres } from 'app/shared/model/parametres.model';

describe('Component Tests', () => {
  describe('Parametres Management Update Component', () => {
    let comp: ParametresUpdateComponent;
    let fixture: ComponentFixture<ParametresUpdateComponent>;
    let service: ParametresService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [ParametresUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ParametresUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ParametresUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ParametresService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Parametres(123);
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
        const entity = new Parametres();
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
