import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { AvisEmissionUpdateComponent } from 'app/entities/avis-emission/avis-emission-update.component';
import { AvisEmissionService } from 'app/entities/avis-emission/avis-emission.service';
import { AvisEmission } from 'app/shared/model/avis-emission.model';

describe('Component Tests', () => {
  describe('AvisEmission Management Update Component', () => {
    let comp: AvisEmissionUpdateComponent;
    let fixture: ComponentFixture<AvisEmissionUpdateComponent>;
    let service: AvisEmissionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [AvisEmissionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AvisEmissionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AvisEmissionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AvisEmissionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AvisEmission(123);
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
        const entity = new AvisEmission();
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
