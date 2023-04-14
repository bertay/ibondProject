import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { ReouvertureDetailComponent } from 'app/entities/reouverture/reouverture-detail.component';
import { Reouverture } from 'app/shared/model/reouverture.model';

describe('Component Tests', () => {
  describe('Reouverture Management Detail Component', () => {
    let comp: ReouvertureDetailComponent;
    let fixture: ComponentFixture<ReouvertureDetailComponent>;
    const route = ({ data: of({ reouverture: new Reouverture(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [ReouvertureDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ReouvertureDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReouvertureDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load reouverture on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.reouverture).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
