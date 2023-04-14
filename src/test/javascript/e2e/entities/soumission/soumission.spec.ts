import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SoumissionComponentsPage, SoumissionDeleteDialog, SoumissionUpdatePage } from './soumission.page-object';

const expect = chai.expect;

describe('Soumission e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let soumissionComponentsPage: SoumissionComponentsPage;
  let soumissionUpdatePage: SoumissionUpdatePage;
  let soumissionDeleteDialog: SoumissionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Soumissions', async () => {
    await navBarPage.goToEntity('soumission');
    soumissionComponentsPage = new SoumissionComponentsPage();
    await browser.wait(ec.visibilityOf(soumissionComponentsPage.title), 5000);
    expect(await soumissionComponentsPage.getTitle()).to.eq('ibondgeneApp.soumission.home.title');
    await browser.wait(ec.or(ec.visibilityOf(soumissionComponentsPage.entities), ec.visibilityOf(soumissionComponentsPage.noResult)), 1000);
  });

  it('should load create Soumission page', async () => {
    await soumissionComponentsPage.clickOnCreateButton();
    soumissionUpdatePage = new SoumissionUpdatePage();
    expect(await soumissionUpdatePage.getPageTitle()).to.eq('ibondgeneApp.soumission.home.createOrEditLabel');
    await soumissionUpdatePage.cancel();
  });

  it('should create and save Soumissions', async () => {
    const nbButtonsBeforeCreate = await soumissionComponentsPage.countDeleteButtons();

    await soumissionComponentsPage.clickOnCreateButton();

    await promise.all([
      soumissionUpdatePage.setNumAnonymatInput('numAnonymat'),
      soumissionUpdatePage.setDateSoumissionInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      soumissionUpdatePage.setNbreSoumissionInput('5'),
      soumissionUpdatePage.setOperateurInput('operateur'),
      soumissionUpdatePage.setDateOperationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      soumissionUpdatePage.emissionSelectLastOption(),
      soumissionUpdatePage.reouvertureSelectLastOption(),
      soumissionUpdatePage.rachatSelectLastOption(),
      soumissionUpdatePage.oncSelectLastOption(),
    ]);

    expect(await soumissionUpdatePage.getNumAnonymatInput()).to.eq('numAnonymat', 'Expected NumAnonymat value to be equals to numAnonymat');
    expect(await soumissionUpdatePage.getDateSoumissionInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateSoumission value to be equals to 2000-12-31'
    );
    expect(await soumissionUpdatePage.getNbreSoumissionInput()).to.eq('5', 'Expected nbreSoumission value to be equals to 5');
    expect(await soumissionUpdatePage.getOperateurInput()).to.eq('operateur', 'Expected Operateur value to be equals to operateur');
    expect(await soumissionUpdatePage.getDateOperationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateOperation value to be equals to 2000-12-31'
    );

    await soumissionUpdatePage.save();
    expect(await soumissionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await soumissionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Soumission', async () => {
    const nbButtonsBeforeDelete = await soumissionComponentsPage.countDeleteButtons();
    await soumissionComponentsPage.clickOnLastDeleteButton();

    soumissionDeleteDialog = new SoumissionDeleteDialog();
    expect(await soumissionDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.soumission.delete.question');
    await soumissionDeleteDialog.clickOnConfirmButton();

    expect(await soumissionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
