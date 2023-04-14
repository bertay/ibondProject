import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PaysComponentsPage, PaysDeleteDialog, PaysUpdatePage } from './pays.page-object';

const expect = chai.expect;

describe('Pays e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let paysComponentsPage: PaysComponentsPage;
  let paysUpdatePage: PaysUpdatePage;
  let paysDeleteDialog: PaysDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Pays', async () => {
    await navBarPage.goToEntity('pays');
    paysComponentsPage = new PaysComponentsPage();
    await browser.wait(ec.visibilityOf(paysComponentsPage.title), 5000);
    expect(await paysComponentsPage.getTitle()).to.eq('ibondgeneApp.pays.home.title');
    await browser.wait(ec.or(ec.visibilityOf(paysComponentsPage.entities), ec.visibilityOf(paysComponentsPage.noResult)), 1000);
  });

  it('should load create Pays page', async () => {
    await paysComponentsPage.clickOnCreateButton();
    paysUpdatePage = new PaysUpdatePage();
    expect(await paysUpdatePage.getPageTitle()).to.eq('ibondgeneApp.pays.home.createOrEditLabel');
    await paysUpdatePage.cancel();
  });

  it('should create and save Pays', async () => {
    const nbButtonsBeforeCreate = await paysComponentsPage.countDeleteButtons();

    await paysComponentsPage.clickOnCreateButton();

    await promise.all([
      paysUpdatePage.setCodePaysInput('codePays'),
      paysUpdatePage.setDesignationFrInput('designationFr'),
      paysUpdatePage.setDesignationEnInput('designationEn'),
      paysUpdatePage.setDesignationPtInput('designationPt'),
      paysUpdatePage.setOperateurInput('operateur'),
      paysUpdatePage.setDateOperationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
    ]);

    expect(await paysUpdatePage.getCodePaysInput()).to.eq('codePays', 'Expected CodePays value to be equals to codePays');
    expect(await paysUpdatePage.getDesignationFrInput()).to.eq(
      'designationFr',
      'Expected DesignationFr value to be equals to designationFr'
    );
    expect(await paysUpdatePage.getDesignationEnInput()).to.eq(
      'designationEn',
      'Expected DesignationEn value to be equals to designationEn'
    );
    expect(await paysUpdatePage.getDesignationPtInput()).to.eq(
      'designationPt',
      'Expected DesignationPt value to be equals to designationPt'
    );
    expect(await paysUpdatePage.getOperateurInput()).to.eq('operateur', 'Expected Operateur value to be equals to operateur');
    expect(await paysUpdatePage.getDateOperationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateOperation value to be equals to 2000-12-31'
    );

    await paysUpdatePage.save();
    expect(await paysUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await paysComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Pays', async () => {
    const nbButtonsBeforeDelete = await paysComponentsPage.countDeleteButtons();
    await paysComponentsPage.clickOnLastDeleteButton();

    paysDeleteDialog = new PaysDeleteDialog();
    expect(await paysDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.pays.delete.question');
    await paysDeleteDialog.clickOnConfirmButton();

    expect(await paysComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
