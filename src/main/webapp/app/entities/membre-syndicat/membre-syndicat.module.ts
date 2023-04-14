import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IbondgeneSharedModule } from 'app/shared/shared.module';
import { MembreSyndicatComponent } from './membre-syndicat.component';
import { MembreSyndicatDetailComponent } from './membre-syndicat-detail.component';
import { MembreSyndicatUpdateComponent } from './membre-syndicat-update.component';
import { MembreSyndicatDeleteDialogComponent } from './membre-syndicat-delete-dialog.component';
import { membreSyndicatRoute } from './membre-syndicat.route';

@NgModule({
  imports: [IbondgeneSharedModule, RouterModule.forChild(membreSyndicatRoute)],
  declarations: [
    MembreSyndicatComponent,
    MembreSyndicatDetailComponent,
    MembreSyndicatUpdateComponent,
    MembreSyndicatDeleteDialogComponent,
  ],
  entryComponents: [MembreSyndicatDeleteDialogComponent],
})
export class IbondgeneMembreSyndicatModule {}
