import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { ClassementDetailComponent } from 'app/entities/classement/classement-detail.component';
import { Classement } from 'app/shared/model/classement.model';

describe('Component Tests', () => {
  describe('Classement Management Detail Component', () => {
    let comp: ClassementDetailComponent;
    let fixture: ComponentFixture<ClassementDetailComponent>;
    const route = ({ data: of({ classement: new Classement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [ClassementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ClassementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ClassementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load classement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.classement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
