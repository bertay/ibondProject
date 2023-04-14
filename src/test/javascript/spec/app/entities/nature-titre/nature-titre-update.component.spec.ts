import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { NatureTitreUpdateComponent } from 'app/entities/nature-titre/nature-titre-update.component';
import { NatureTitreService } from 'app/entities/nature-titre/nature-titre.service';
import { NatureTitre } from 'app/shared/model/nature-titre.model';

describe('Component Tests', () => {
  describe('NatureTitre Management Update Component', () => {
    let comp: NatureTitreUpdateComponent;
    let fixture: ComponentFixture<NatureTitreUpdateComponent>;
    let service: NatureTitreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [NatureTitreUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NatureTitreUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NatureTitreUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NatureTitreService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NatureTitre(123);
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
        const entity = new NatureTitre();
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
