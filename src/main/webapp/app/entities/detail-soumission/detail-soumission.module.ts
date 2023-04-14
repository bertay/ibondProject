import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IbondgeneSharedModule } from 'app/shared/shared.module';
import { DetailSoumissionComponent } from './detail-soumission.component';
import { DetailSoumissionDetailComponent } from './detail-soumission-detail.component';
import { DetailSoumissionUpdateComponent } from './detail-soumission-update.component';
import { DetailSoumissionDeleteDialogComponent } from './detail-soumission-delete-dialog.component';
import { detailSoumissionRoute } from './detail-soumission.route';

@NgModule({
  imports: [IbondgeneSharedModule, RouterModule.forChild(detailSoumissionRoute)],
  declarations: [
    DetailSoumissionComponent,
    DetailSoumissionDetailComponent,
    DetailSoumissionUpdateComponent,
    DetailSoumissionDeleteDialogComponent,
  ],
  entryComponents: [DetailSoumissionDeleteDialogComponent],
})
export class IbondgeneDetailSoumissionModule {}
