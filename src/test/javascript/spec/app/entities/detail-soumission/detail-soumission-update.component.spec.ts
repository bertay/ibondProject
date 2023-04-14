import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { DetailSoumissionUpdateComponent } from 'app/entities/detail-soumission/detail-soumission-update.component';
import { DetailSoumissionService } from 'app/entities/detail-soumission/detail-soumission.service';
import { DetailSoumission } from 'app/shared/model/detail-soumission.model';

describe('Component Tests', () => {
  describe('DetailSoumission Management Update Component', () => {
    let comp: DetailSoumissionUpdateComponent;
    let fixture: ComponentFixture<DetailSoumissionUpdateComponent>;
    let service: DetailSoumissionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [DetailSoumissionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DetailSoumissionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DetailSoumissionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DetailSoumissionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DetailSoumission(123);
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
        const entity = new DetailSoumission();
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
