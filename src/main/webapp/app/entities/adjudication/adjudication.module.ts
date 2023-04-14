import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IbondgeneSharedModule } from 'app/shared/shared.module';
import { AdjudicationComponent } from './adjudication.component';
import { AdjudicationDetailComponent } from './adjudication-detail.component';
import { AdjudicationUpdateComponent } from './adjudication-update.component';
import { AdjudicationDeleteDialogComponent } from './adjudication-delete-dialog.component';
import { adjudicationRoute } from './adjudication.route';

@NgModule({
  imports: [IbondgeneSharedModule, RouterModule.forChild(adjudicationRoute)],
  declarations: [AdjudicationComponent, AdjudicationDetailComponent, AdjudicationUpdateComponent, AdjudicationDeleteDialogComponent],
  entryComponents: [AdjudicationDeleteDialogComponent],
})
export class IbondgeneAdjudicationModule {}
