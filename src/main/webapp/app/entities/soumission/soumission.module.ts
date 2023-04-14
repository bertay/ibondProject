import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IbondgeneSharedModule } from 'app/shared/shared.module';
import { SoumissionComponent } from './soumission.component';
import { SoumissionDetailComponent } from './soumission-detail.component';
import { SoumissionUpdateComponent } from './soumission-update.component';
import { SoumissionDeleteDialogComponent } from './soumission-delete-dialog.component';
import { soumissionRoute } from './soumission.route';

@NgModule({
  imports: [IbondgeneSharedModule, RouterModule.forChild(soumissionRoute)],
  declarations: [SoumissionComponent, SoumissionDetailComponent, SoumissionUpdateComponent, SoumissionDeleteDialogComponent],
  entryComponents: [SoumissionDeleteDialogComponent],
})
export class IbondgeneSoumissionModule {}
