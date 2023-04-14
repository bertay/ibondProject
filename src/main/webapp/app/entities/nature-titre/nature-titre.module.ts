import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IbondgeneSharedModule } from 'app/shared/shared.module';
import { NatureTitreComponent } from './nature-titre.component';
import { NatureTitreDetailComponent } from './nature-titre-detail.component';
import { NatureTitreUpdateComponent } from './nature-titre-update.component';
import { NatureTitreDeleteDialogComponent } from './nature-titre-delete-dialog.component';
import { natureTitreRoute } from './nature-titre.route';

@NgModule({
  imports: [IbondgeneSharedModule, RouterModule.forChild(natureTitreRoute)],
  declarations: [NatureTitreComponent, NatureTitreDetailComponent, NatureTitreUpdateComponent, NatureTitreDeleteDialogComponent],
  entryComponents: [NatureTitreDeleteDialogComponent],
})
export class IbondgeneNatureTitreModule {}
