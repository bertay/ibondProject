import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IbondgeneSharedModule } from 'app/shared/shared.module';
import { EmissionComponent } from './emission.component';
import { EmissionDetailComponent } from './emission-detail.component';
import { EmissionUpdateComponent } from './emission-update.component';
import { EmissionDeleteDialogComponent } from './emission-delete-dialog.component';
import { emissionRoute } from './emission.route';

@NgModule({
  imports: [IbondgeneSharedModule, RouterModule.forChild(emissionRoute)],
  declarations: [EmissionComponent, EmissionDetailComponent, EmissionUpdateComponent, EmissionDeleteDialogComponent],
  entryComponents: [EmissionDeleteDialogComponent],
})
export class IbondgeneEmissionModule {}
