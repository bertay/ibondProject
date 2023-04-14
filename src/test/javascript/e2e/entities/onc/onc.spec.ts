import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OncComponentsPage, OncDeleteDialog, OncUpdatePage } from './onc.page-object';

const expect = chai.expect;

describe('Onc e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let oncComponentsPage: OncComponentsPage;
  let oncUpdatePage: OncUpdatePage;
  let oncDeleteDialog: OncDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Oncs', async () => {
    await navBarPage.goToEntity('onc');
    oncComponentsPage = new OncComponentsPage();
    await browser.wait(ec.visibilityOf(oncComponentsPage.title), 5000);
    expect(await oncComponentsPage.getTitle()).to.eq('ibondgeneApp.onc.home.title');
    await browser.wait(ec.or(ec.visibilityOf(oncComponentsPage.entities), ec.visibilityOf(oncComponentsPage.noResult)), 1000);
  });

  it('should load create Onc page', async () => {
    await oncComponentsPage.clickOnCreateButton();
    oncUpdatePage = new OncUpdatePage();
    expect(await oncUpdatePage.getPageTitle()).to.eq('ibondgeneApp.onc.home.createOrEditLabel');
    await oncUpdatePage.cancel();
  });

  it('should create and save Oncs', async () => {
    const nbButtonsBeforeCreate = await oncComponentsPage.countDeleteButtons();

    await oncComponentsPage.clickOnCreateButton();

    await promise.all([
      oncUpdatePage.setOperateurInput('operateur'),
      oncUpdatePage.setDateOperationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      oncUpdatePage.emissionSelectLastOption(),
    ]);

    expect(await oncUpdatePage.getOperateurInput()).to.eq('operateur', 'Expected Operateur value to be equals to operateur');
    expect(await oncUpdatePage.getDateOperationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateOperation value to be equals to 2000-12-31'
    );

    await oncUpdatePage.save();
    expect(await oncUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await oncComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Onc', async () => {
    const nbButtonsBeforeDelete = await oncComponentsPage.countDeleteButtons();
    await oncComponentsPage.clickOnLastDeleteButton();

    oncDeleteDialog = new OncDeleteDialog();
    expect(await oncDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.onc.delete.question');
    await oncDeleteDialog.clickOnConfirmButton();

    expect(await oncComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
