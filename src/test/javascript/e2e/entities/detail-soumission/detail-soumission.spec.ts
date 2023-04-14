import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DetailSoumissionComponentsPage, DetailSoumissionDeleteDialog, DetailSoumissionUpdatePage } from './detail-soumission.page-object';

const expect = chai.expect;

describe('DetailSoumission e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let detailSoumissionComponentsPage: DetailSoumissionComponentsPage;
  let detailSoumissionUpdatePage: DetailSoumissionUpdatePage;
  let detailSoumissionDeleteDialog: DetailSoumissionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DetailSoumissions', async () => {
    await navBarPage.goToEntity('detail-soumission');
    detailSoumissionComponentsPage = new DetailSoumissionComponentsPage();
    await browser.wait(ec.visibilityOf(detailSoumissionComponentsPage.title), 5000);
    expect(await detailSoumissionComponentsPage.getTitle()).to.eq('ibondgeneApp.detailSoumission.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(detailSoumissionComponentsPage.entities), ec.visibilityOf(detailSoumissionComponentsPage.noResult)),
      1000
    );
  });

  it('should load create DetailSoumission page', async () => {
    await detailSoumissionComponentsPage.clickOnCreateButton();
    detailSoumissionUpdatePage = new DetailSoumissionUpdatePage();
    expect(await detailSoumissionUpdatePage.getPageTitle()).to.eq('ibondgeneApp.detailSoumission.home.createOrEditLabel');
    await detailSoumissionUpdatePage.cancel();
  });

  it('should create and save DetailSoumissions', async () => {
    const nbButtonsBeforeCreate = await detailSoumissionComponentsPage.countDeleteButtons();

    await detailSoumissionComponentsPage.clickOnCreateButton();

    await promise.all([
      detailSoumissionUpdatePage.setMontantSoumissionInput('5'),
      detailSoumissionUpdatePage.setTauxProposeInput('5'),
      detailSoumissionUpdatePage.setOperateurInput('operateur'),
      detailSoumissionUpdatePage.setDateOperationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      detailSoumissionUpdatePage.soumissionSelectLastOption(),
    ]);

    expect(await detailSoumissionUpdatePage.getMontantSoumissionInput()).to.eq('5', 'Expected montantSoumission value to be equals to 5');
    expect(await detailSoumissionUpdatePage.getTauxProposeInput()).to.eq('5', 'Expected tauxPropose value to be equals to 5');
    expect(await detailSoumissionUpdatePage.getOperateurInput()).to.eq('operateur', 'Expected Operateur value to be equals to operateur');
    expect(await detailSoumissionUpdatePage.getDateOperationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateOperation value to be equals to 2000-12-31'
    );

    await detailSoumissionUpdatePage.save();
    expect(await detailSoumissionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await detailSoumissionComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last DetailSoumission', async () => {
    const nbButtonsBeforeDelete = await detailSoumissionComponentsPage.countDeleteButtons();
    await detailSoumissionComponentsPage.clickOnLastDeleteButton();

    detailSoumissionDeleteDialog = new DetailSoumissionDeleteDialog();
    expect(await detailSoumissionDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.detailSoumission.delete.question');
    await detailSoumissionDeleteDialog.clickOnConfirmButton();

    expect(await detailSoumissionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
