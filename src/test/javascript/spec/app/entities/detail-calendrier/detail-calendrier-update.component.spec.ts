import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { DetailCalendrierUpdateComponent } from 'app/entities/detail-calendrier/detail-calendrier-update.component';
import { DetailCalendrierService } from 'app/entities/detail-calendrier/detail-calendrier.service';
import { DetailCalendrier } from 'app/shared/model/detail-calendrier.model';

describe('Component Tests', () => {
  describe('DetailCalendrier Management Update Component', () => {
    let comp: DetailCalendrierUpdateComponent;
    let fixture: ComponentFixture<DetailCalendrierUpdateComponent>;
    let service: DetailCalendrierService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [DetailCalendrierUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DetailCalendrierUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DetailCalendrierUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DetailCalendrierService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DetailCalendrier(123);
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
        const entity = new DetailCalendrier();
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
