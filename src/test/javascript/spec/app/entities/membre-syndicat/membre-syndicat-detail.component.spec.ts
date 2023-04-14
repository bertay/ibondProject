import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IbondgeneTestModule } from '../../../test.module';
import { MembreSyndicatDetailComponent } from 'app/entities/membre-syndicat/membre-syndicat-detail.component';
import { MembreSyndicat } from 'app/shared/model/membre-syndicat.model';

describe('Component Tests', () => {
  describe('MembreSyndicat Management Detail Component', () => {
    let comp: MembreSyndicatDetailComponent;
    let fixture: ComponentFixture<MembreSyndicatDetailComponent>;
    const route = ({ data: of({ membreSyndicat: new MembreSyndicat(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IbondgeneTestModule],
        declarations: [MembreSyndicatDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MembreSyndicatDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MembreSyndicatDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load membreSyndicat on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.membreSyndicat).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
