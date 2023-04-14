import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IbondgeneSharedModule } from 'app/shared/shared.module';
import { SvtComponent } from './svt.component';
import { SvtDetailComponent } from './svt-detail.component';
import { SvtUpdateComponent } from './svt-update.component';
import { SvtDeleteDialogComponent } from './svt-delete-dialog.component';
import { svtRoute } from './svt.route';

@NgModule({
  imports: [IbondgeneSharedModule, RouterModule.forChild(svtRoute)],
  declarations: [SvtComponent, SvtDetailComponent, SvtUpdateComponent, SvtDeleteDialogComponent],
  entryComponents: [SvtDeleteDialogComponent],
})
export class IbondgeneSvtModule {}
