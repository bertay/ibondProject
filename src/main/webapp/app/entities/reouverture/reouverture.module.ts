import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IbondgeneSharedModule } from 'app/shared/shared.module';
import { ReouvertureComponent } from './reouverture.component';
import { ReouvertureDetailComponent } from './reouverture-detail.component';
import { ReouvertureUpdateComponent } from './reouverture-update.component';
import { ReouvertureDeleteDialogComponent } from './reouverture-delete-dialog.component';
import { reouvertureRoute } from './reouverture.route';

@NgModule({
  imports: [IbondgeneSharedModule, RouterModule.forChild(reouvertureRoute)],
  declarations: [ReouvertureComponent, ReouvertureDetailComponent, ReouvertureUpdateComponent, ReouvertureDeleteDialogComponent],
  entryComponents: [ReouvertureDeleteDialogComponent],
})
export class IbondgeneReouvertureModule {}
