import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IbondgeneSharedModule } from 'app/shared/shared.module';
import { RachatComponent } from './rachat.component';
import { RachatDetailComponent } from './rachat-detail.component';
import { RachatUpdateComponent } from './rachat-update.component';
import { RachatDeleteDialogComponent } from './rachat-delete-dialog.component';
import { rachatRoute } from './rachat.route';

@NgModule({
  imports: [IbondgeneSharedModule, RouterModule.forChild(rachatRoute)],
  declarations: [RachatComponent, RachatDetailComponent, RachatUpdateComponent, RachatDeleteDialogComponent],
  entryComponents: [RachatDeleteDialogComponent],
})
export class IbondgeneRachatModule {}
