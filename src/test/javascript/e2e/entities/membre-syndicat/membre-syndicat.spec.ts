import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MembreSyndicatComponentsPage, MembreSyndicatDeleteDialog, MembreSyndicatUpdatePage } from './membre-syndicat.page-object';

const expect = chai.expect;

describe('MembreSyndicat e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let membreSyndicatComponentsPage: MembreSyndicatComponentsPage;
  let membreSyndicatUpdatePage: MembreSyndicatUpdatePage;
  let membreSyndicatDeleteDialog: MembreSyndicatDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MembreSyndicats', async () => {
    await navBarPage.goToEntity('membre-syndicat');
    membreSyndicatComponentsPage = new MembreSyndicatComponentsPage();
    await browser.wait(ec.visibilityOf(membreSyndicatComponentsPage.title), 5000);
    expect(await membreSyndicatComponentsPage.getTitle()).to.eq('ibondgeneApp.membreSyndicat.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(membreSyndicatComponentsPage.entities), ec.visibilityOf(membreSyndicatComponentsPage.noResult)),
      1000
    );
  });

  it('should load create MembreSyndicat page', async () => {
    await membreSyndicatComponentsPage.clickOnCreateButton();
    membreSyndicatUpdatePage = new MembreSyndicatUpdatePage();
    expect(await membreSyndicatUpdatePage.getPageTitle()).to.eq('ibondgeneApp.membreSyndicat.home.createOrEditLabel');
    await membreSyndicatUpdatePage.cancel();
  });

  it('should create and save MembreSyndicats', async () => {
    const nbButtonsBeforeCreate = await membreSyndicatComponentsPage.countDeleteButtons();

    await membreSyndicatComponentsPage.clickOnCreateButton();

    await promise.all([
      membreSyndicatUpdatePage.rangSelectLastOption(),
      membreSyndicatUpdatePage.setCommissionInput('5'),
      membreSyndicatUpdatePage.setOperateurInput('operateur'),
      membreSyndicatUpdatePage.setDateOperationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      membreSyndicatUpdatePage.emissionSelectLastOption(),
    ]);

    expect(await membreSyndicatUpdatePage.getCommissionInput()).to.eq('5', 'Expected commission value to be equals to 5');
    expect(await membreSyndicatUpdatePage.getOperateurInput()).to.eq('operateur', 'Expected Operateur value to be equals to operateur');
    expect(await membreSyndicatUpdatePage.getDateOperationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateOperation value to be equals to 2000-12-31'
    );

    await membreSyndicatUpdatePage.save();
    expect(await membreSyndicatUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await membreSyndicatComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MembreSyndicat', async () => {
    const nbButtonsBeforeDelete = await membreSyndicatComponentsPage.countDeleteButtons();
    await membreSyndicatComponentsPage.clickOnLastDeleteButton();

    membreSyndicatDeleteDialog = new MembreSyndicatDeleteDialog();
    expect(await membreSyndicatDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.membreSyndicat.delete.question');
    await membreSyndicatDeleteDialog.clickOnConfirmButton();

    expect(await membreSyndicatComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
