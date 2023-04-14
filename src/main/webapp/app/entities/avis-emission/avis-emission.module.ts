import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IbondgeneSharedModule } from 'app/shared/shared.module';
import { AvisEmissionComponent } from './avis-emission.component';
import { AvisEmissionDetailComponent } from './avis-emission-detail.component';
import { AvisEmissionUpdateComponent } from './avis-emission-update.component';
import { AvisEmissionDeleteDialogComponent } from './avis-emission-delete-dialog.component';
import { avisEmissionRoute } from './avis-emission.route';

@NgModule({
  imports: [IbondgeneSharedModule, RouterModule.forChild(avisEmissionRoute)],
  declarations: [AvisEmissionComponent, AvisEmissionDetailComponent, AvisEmissionUpdateComponent, AvisEmissionDeleteDialogComponent],
  entryComponents: [AvisEmissionDeleteDialogComponent],
})
export class IbondgeneAvisEmissionModule {}
