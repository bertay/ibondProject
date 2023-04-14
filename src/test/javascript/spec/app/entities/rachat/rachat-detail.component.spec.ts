import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { RachatDetailComponent } from 'app/entities/rachat/rachat-detail.component';
import { Rachat } from 'app/shared/model/rachat.model';

describe('Component Tests', () => {
  describe('Rachat Management Detail Component', () => {
    let comp: RachatDetailComponent;
    let fixture: ComponentFixture<RachatDetailComponent>;
    const route = ({ data: of({ rachat: new Rachat(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [RachatDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RachatDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RachatDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load rachat on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.rachat).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
