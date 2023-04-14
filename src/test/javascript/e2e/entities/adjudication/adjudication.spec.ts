import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AdjudicationComponentsPage, AdjudicationDeleteDialog, AdjudicationUpdatePage } from './adjudication.page-object';

const expect = chai.expect;

describe('Adjudication e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let adjudicationComponentsPage: AdjudicationComponentsPage;
  let adjudicationUpdatePage: AdjudicationUpdatePage;
  let adjudicationDeleteDialog: AdjudicationDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Adjudications', async () => {
    await navBarPage.goToEntity('adjudication');
    adjudicationComponentsPage = new AdjudicationComponentsPage();
    await browser.wait(ec.visibilityOf(adjudicationComponentsPage.title), 5000);
    expect(await adjudicationComponentsPage.getTitle()).to.eq('ibondgeneApp.adjudication.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(adjudicationComponentsPage.entities), ec.visibilityOf(adjudicationComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Adjudication page', async () => {
    await adjudicationComponentsPage.clickOnCreateButton();
    adjudicationUpdatePage = new AdjudicationUpdatePage();
    expect(await adjudicationUpdatePage.getPageTitle()).to.eq('ibondgeneApp.adjudication.home.createOrEditLabel');
    await adjudicationUpdatePage.cancel();
  });

  it('should create and save Adjudications', async () => {
    const nbButtonsBeforeCreate = await adjudicationComponentsPage.countDeleteButtons();

    await adjudicationComponentsPage.clickOnCreateButton();

    await promise.all([
      adjudicationUpdatePage.setRangInput('5'),
      adjudicationUpdatePage.setObservationFrInput('observationFr'),
      adjudicationUpdatePage.setObservationEnInput('observationEn'),
      adjudicationUpdatePage.setObservationPtInput('observationPt'),
      adjudicationUpdatePage.reouvertureSelectLastOption(),
      adjudicationUpdatePage.rachatSelectLastOption(),
      adjudicationUpdatePage.oncSelectLastOption(),
    ]);

    expect(await adjudicationUpdatePage.getRangInput()).to.eq('5', 'Expected rang value to be equals to 5');
    expect(await adjudicationUpdatePage.getObservationFrInput()).to.eq(
      'observationFr',
      'Expected ObservationFr value to be equals to observationFr'
    );
    expect(await adjudicationUpdatePage.getObservationEnInput()).to.eq(
      'observationEn',
      'Expected ObservationEn value to be equals to observationEn'
    );
    expect(await adjudicationUpdatePage.getObservationPtInput()).to.eq(
      'observationPt',
      'Expected ObservationPt value to be equals to observationPt'
    );

    await adjudicationUpdatePage.save();
    expect(await adjudicationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await adjudicationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Adjudication', async () => {
    const nbButtonsBeforeDelete = await adjudicationComponentsPage.countDeleteButtons();
    await adjudicationComponentsPage.clickOnLastDeleteButton();

    adjudicationDeleteDialog = new AdjudicationDeleteDialog();
    expect(await adjudicationDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.adjudication.delete.question');
    await adjudicationDeleteDialog.clickOnConfirmButton();

    expect(await adjudicationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
