import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { SvtUpdateComponent } from 'app/entities/svt/svt-update.component';
import { SvtService } from 'app/entities/svt/svt.service';
import { Svt } from 'app/shared/model/svt.model';

describe('Component Tests', () => {
  describe('Svt Management Update Component', () => {
    let comp: SvtUpdateComponent;
    let fixture: ComponentFixture<SvtUpdateComponent>;
    let service: SvtService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [SvtUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SvtUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SvtUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SvtService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Svt(123);
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
        const entity = new Svt();
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
