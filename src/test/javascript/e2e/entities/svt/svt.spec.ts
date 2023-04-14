import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SvtComponentsPage, SvtDeleteDialog, SvtUpdatePage } from './svt.page-object';

const expect = chai.expect;

describe('Svt e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let svtComponentsPage: SvtComponentsPage;
  let svtUpdatePage: SvtUpdatePage;
  let svtDeleteDialog: SvtDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Svts', async () => {
    await navBarPage.goToEntity('svt');
    svtComponentsPage = new SvtComponentsPage();
    await browser.wait(ec.visibilityOf(svtComponentsPage.title), 5000);
    expect(await svtComponentsPage.getTitle()).to.eq('ibondgeneApp.svt.home.title');
    await browser.wait(ec.or(ec.visibilityOf(svtComponentsPage.entities), ec.visibilityOf(svtComponentsPage.noResult)), 1000);
  });

  it('should load create Svt page', async () => {
    await svtComponentsPage.clickOnCreateButton();
    svtUpdatePage = new SvtUpdatePage();
    expect(await svtUpdatePage.getPageTitle()).to.eq('ibondgeneApp.svt.home.createOrEditLabel');
    await svtUpdatePage.cancel();
  });

  it('should create and save Svts', async () => {
    const nbButtonsBeforeCreate = await svtComponentsPage.countDeleteButtons();

    await svtComponentsPage.clickOnCreateButton();

    await promise.all([
      svtUpdatePage.setAbreviationFrInput('abreviationFr'),
      svtUpdatePage.setAbreviationEnInput('abreviationEn'),
      svtUpdatePage.setAbreviationPtInput('abreviationPt'),
      svtUpdatePage.setDesignationFrInput('designationFr'),
      svtUpdatePage.setDesignationEnInput('designationEn'),
      svtUpdatePage.etatSelectLastOption(),
      svtUpdatePage.setOperateurInput('operateur'),
      svtUpdatePage.setDateOperationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      svtUpdatePage.paysSelectLastOption(),
    ]);

    expect(await svtUpdatePage.getAbreviationFrInput()).to.eq(
      'abreviationFr',
      'Expected AbreviationFr value to be equals to abreviationFr'
    );
    expect(await svtUpdatePage.getAbreviationEnInput()).to.eq(
      'abreviationEn',
      'Expected AbreviationEn value to be equals to abreviationEn'
    );
    expect(await svtUpdatePage.getAbreviationPtInput()).to.eq(
      'abreviationPt',
      'Expected AbreviationPt value to be equals to abreviationPt'
    );
    expect(await svtUpdatePage.getDesignationFrInput()).to.eq(
      'designationFr',
      'Expected DesignationFr value to be equals to designationFr'
    );
    expect(await svtUpdatePage.getDesignationEnInput()).to.eq(
      'designationEn',
      'Expected DesignationEn value to be equals to designationEn'
    );
    expect(await svtUpdatePage.getOperateurInput()).to.eq('operateur', 'Expected Operateur value to be equals to operateur');
    expect(await svtUpdatePage.getDateOperationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateOperation value to be equals to 2000-12-31'
    );

    await svtUpdatePage.save();
    expect(await svtUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await svtComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Svt', async () => {
    const nbButtonsBeforeDelete = await svtComponentsPage.countDeleteButtons();
    await svtComponentsPage.clickOnLastDeleteButton();

    svtDeleteDialog = new SvtDeleteDialog();
    expect(await svtDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.svt.delete.question');
    await svtDeleteDialog.clickOnConfirmButton();

    expect(await svtComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
