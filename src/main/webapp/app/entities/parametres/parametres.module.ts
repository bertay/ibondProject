import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IbondgeneSharedModule } from 'app/shared/shared.module';
import { ParametresComponent } from './parametres.component';
import { ParametresDetailComponent } from './parametres-detail.component';
import { ParametresUpdateComponent } from './parametres-update.component';
import { ParametresDeleteDialogComponent } from './parametres-delete-dialog.component';
import { parametresRoute } from './parametres.route';

@NgModule({
  imports: [IbondgeneSharedModule, RouterModule.forChild(parametresRoute)],
  declarations: [ParametresComponent, ParametresDetailComponent, ParametresUpdateComponent, ParametresDeleteDialogComponent],
  entryComponents: [ParametresDeleteDialogComponent],
})
export class IbondgeneParametresModule {}
