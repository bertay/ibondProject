import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { ReouvertureUpdateComponent } from 'app/entities/reouverture/reouverture-update.component';
import { ReouvertureService } from 'app/entities/reouverture/reouverture.service';
import { Reouverture } from 'app/shared/model/reouverture.model';

describe('Component Tests', () => {
  describe('Reouverture Management Update Component', () => {
    let comp: ReouvertureUpdateComponent;
    let fixture: ComponentFixture<ReouvertureUpdateComponent>;
    let service: ReouvertureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [ReouvertureUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ReouvertureUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReouvertureUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReouvertureService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Reouverture(123);
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
        const entity = new Reouverture();
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
