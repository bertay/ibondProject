import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { AdjudicationUpdateComponent } from 'app/entities/adjudication/adjudication-update.component';
import { AdjudicationService } from 'app/entities/adjudication/adjudication.service';
import { Adjudication } from 'app/shared/model/adjudication.model';

describe('Component Tests', () => {
  describe('Adjudication Management Update Component', () => {
    let comp: AdjudicationUpdateComponent;
    let fixture: ComponentFixture<AdjudicationUpdateComponent>;
    let service: AdjudicationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [AdjudicationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AdjudicationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AdjudicationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AdjudicationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Adjudication(123);
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
        const entity = new Adjudication();
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
