import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { RachatUpdateComponent } from 'app/entities/rachat/rachat-update.component';
import { RachatService } from 'app/entities/rachat/rachat.service';
import { Rachat } from 'app/shared/model/rachat.model';

describe('Component Tests', () => {
  describe('Rachat Management Update Component', () => {
    let comp: RachatUpdateComponent;
    let fixture: ComponentFixture<RachatUpdateComponent>;
    let service: RachatService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [RachatUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RachatUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RachatUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RachatService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Rachat(123);
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
        const entity = new Rachat();
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
