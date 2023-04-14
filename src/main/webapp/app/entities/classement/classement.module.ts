import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IbondgeneSharedModule } from 'app/shared/shared.module';
import { ClassementComponent } from './classement.component';
import { ClassementDetailComponent } from './classement-detail.component';
import { ClassementUpdateComponent } from './classement-update.component';
import { ClassementDeleteDialogComponent } from './classement-delete-dialog.component';
import { classementRoute } from './classement.route';

@NgModule({
  imports: [IbondgeneSharedModule, RouterModule.forChild(classementRoute)],
  declarations: [ClassementComponent, ClassementDetailComponent, ClassementUpdateComponent, ClassementDeleteDialogComponent],
  entryComponents: [ClassementDeleteDialogComponent],
})
export class IbondgeneClassementModule {}
