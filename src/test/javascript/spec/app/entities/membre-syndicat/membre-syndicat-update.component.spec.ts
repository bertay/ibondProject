import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { MembreSyndicatUpdateComponent } from 'app/entities/membre-syndicat/membre-syndicat-update.component';
import { MembreSyndicatService } from 'app/entities/membre-syndicat/membre-syndicat.service';
import { MembreSyndicat } from 'app/shared/model/membre-syndicat.model';

describe('Component Tests', () => {
  describe('MembreSyndicat Management Update Component', () => {
    let comp: MembreSyndicatUpdateComponent;
    let fixture: ComponentFixture<MembreSyndicatUpdateComponent>;
    let service: MembreSyndicatService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [MembreSyndicatUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MembreSyndicatUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MembreSyndicatUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MembreSyndicatService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MembreSyndicat(123);
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
        const entity = new MembreSyndicat();
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
