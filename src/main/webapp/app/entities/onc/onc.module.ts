import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IbondgeneSharedModule } from 'app/shared/shared.module';
import { OncComponent } from './onc.component';
import { OncDetailComponent } from './onc-detail.component';
import { OncUpdateComponent } from './onc-update.component';
import { OncDeleteDialogComponent } from './onc-delete-dialog.component';
import { oncRoute } from './onc.route';

@NgModule({
  imports: [IbondgeneSharedModule, RouterModule.forChild(oncRoute)],
  declarations: [OncComponent, OncDetailComponent, OncUpdateComponent, OncDeleteDialogComponent],
  entryComponents: [OncDeleteDialogComponent],
})
export class IbondgeneOncModule {}
