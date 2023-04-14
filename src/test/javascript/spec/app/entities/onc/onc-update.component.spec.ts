import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { OncUpdateComponent } from 'app/entities/onc/onc-update.component';
import { OncService } from 'app/entities/onc/onc.service';
import { Onc } from 'app/shared/model/onc.model';

describe('Component Tests', () => {
  describe('Onc Management Update Component', () => {
    let comp: OncUpdateComponent;
    let fixture: ComponentFixture<OncUpdateComponent>;
    let service: OncService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [OncUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OncUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OncUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OncService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Onc(123);
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
        const entity = new Onc();
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
