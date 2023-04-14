import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IbondgeneSharedModule } from 'app/shared/shared.module';
import { DetailCalendrierComponent } from './detail-calendrier.component';
import { DetailCalendrierDetailComponent } from './detail-calendrier-detail.component';
import { DetailCalendrierUpdateComponent } from './detail-calendrier-update.component';
import { DetailCalendrierDeleteDialogComponent } from './detail-calendrier-delete-dialog.component';
import { detailCalendrierRoute } from './detail-calendrier.route';

@NgModule({
  imports: [IbondgeneSharedModule, RouterModule.forChild(detailCalendrierRoute)],
  declarations: [
    DetailCalendrierComponent,
    DetailCalendrierDetailComponent,
    DetailCalendrierUpdateComponent,
    DetailCalendrierDeleteDialogComponent,
  ],
  entryComponents: [DetailCalendrierDeleteDialogComponent],
})
export class IbondgeneDetailCalendrierModule {}
