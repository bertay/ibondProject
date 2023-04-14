import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ClassementComponentsPage, ClassementDeleteDialog, ClassementUpdatePage } from './classement.page-object';

const expect = chai.expect;

describe('Classement e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let classementComponentsPage: ClassementComponentsPage;
  let classementUpdatePage: ClassementUpdatePage;
  let classementDeleteDialog: ClassementDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Classements', async () => {
    await navBarPage.goToEntity('classement');
    classementComponentsPage = new ClassementComponentsPage();
    await browser.wait(ec.visibilityOf(classementComponentsPage.title), 5000);
    expect(await classementComponentsPage.getTitle()).to.eq('ibondgeneApp.classement.home.title');
    await browser.wait(ec.or(ec.visibilityOf(classementComponentsPage.entities), ec.visibilityOf(classementComponentsPage.noResult)), 1000);
  });

  it('should load create Classement page', async () => {
    await classementComponentsPage.clickOnCreateButton();
    classementUpdatePage = new ClassementUpdatePage();
    expect(await classementUpdatePage.getPageTitle()).to.eq('ibondgeneApp.classement.home.createOrEditLabel');
    await classementUpdatePage.cancel();
  });

  it('should create and save Classements', async () => {
    const nbButtonsBeforeCreate = await classementComponentsPage.countDeleteButtons();

    await classementComponentsPage.clickOnCreateButton();

    await promise.all([
      classementUpdatePage.setRangInput('5'),
      classementUpdatePage.setObservationFrInput('observationFr'),
      classementUpdatePage.setObservationEnInput('observationEn'),
      classementUpdatePage.setObservationPtInput('observationPt'),
      classementUpdatePage.detailSoumissionSelectLastOption(),
      classementUpdatePage.emissionSelectLastOption(),
      classementUpdatePage.reouvertureSelectLastOption(),
      classementUpdatePage.rachatSelectLastOption(),
      classementUpdatePage.oncSelectLastOption(),
    ]);

    expect(await classementUpdatePage.getRangInput()).to.eq('5', 'Expected rang value to be equals to 5');
    expect(await classementUpdatePage.getObservationFrInput()).to.eq(
      'observationFr',
      'Expected ObservationFr value to be equals to observationFr'
    );
    expect(await classementUpdatePage.getObservationEnInput()).to.eq(
      'observationEn',
      'Expected ObservationEn value to be equals to observationEn'
    );
    expect(await classementUpdatePage.getObservationPtInput()).to.eq(
      'observationPt',
      'Expected ObservationPt value to be equals to observationPt'
    );

    await classementUpdatePage.save();
    expect(await classementUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await classementComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Classement', async () => {
    const nbButtonsBeforeDelete = await classementComponentsPage.countDeleteButtons();
    await classementComponentsPage.clickOnLastDeleteButton();

    classementDeleteDialog = new ClassementDeleteDialog();
    expect(await classementDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.classement.delete.question');
    await classementDeleteDialog.clickOnConfirmButton();

    expect(await classementComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
