import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { IbondgeneSharedModule } from 'app/shared/shared.module';
import { IbondgeneCoreModule } from 'app/core/core.module';
import { IbondgeneAppRoutingModule } from './app-routing.module';
import { IbondgeneHomeModule } from './home/home.module';
import { IbondgeneEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    IbondgeneSharedModule,
    IbondgeneCoreModule,
    IbondgeneHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    IbondgeneEntityModule,
    IbondgeneAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class IbondgeneAppModule {}
