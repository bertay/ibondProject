import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ReouvertureComponentsPage, ReouvertureDeleteDialog, ReouvertureUpdatePage } from './reouverture.page-object';

const expect = chai.expect;

describe('Reouverture e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let reouvertureComponentsPage: ReouvertureComponentsPage;
  let reouvertureUpdatePage: ReouvertureUpdatePage;
  let reouvertureDeleteDialog: ReouvertureDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Reouvertures', async () => {
    await navBarPage.goToEntity('reouverture');
    reouvertureComponentsPage = new ReouvertureComponentsPage();
    await browser.wait(ec.visibilityOf(reouvertureComponentsPage.title), 5000);
    expect(await reouvertureComponentsPage.getTitle()).to.eq('ibondgeneApp.reouverture.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(reouvertureComponentsPage.entities), ec.visibilityOf(reouvertureComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Reouverture page', async () => {
    await reouvertureComponentsPage.clickOnCreateButton();
    reouvertureUpdatePage = new ReouvertureUpdatePage();
    expect(await reouvertureUpdatePage.getPageTitle()).to.eq('ibondgeneApp.reouverture.home.createOrEditLabel');
    await reouvertureUpdatePage.cancel();
  });

  it('should create and save Reouvertures', async () => {
    const nbButtonsBeforeCreate = await reouvertureComponentsPage.countDeleteButtons();

    await reouvertureComponentsPage.clickOnCreateButton();

    await promise.all([
      reouvertureUpdatePage.setCodeValeurInput('codeValeur'),
      reouvertureUpdatePage.setDesignationFrInput('designationFr'),
      reouvertureUpdatePage.setDesignationEnInput('designationEn'),
      reouvertureUpdatePage.setDesignationPtInput('designationPt'),
      reouvertureUpdatePage.setDateEmissionInput('2000-12-31'),
      reouvertureUpdatePage.setTauxInteretInput('5'),
      reouvertureUpdatePage.setEncoursEmissionInput('5'),
      reouvertureUpdatePage.uniteVolumeSelectLastOption(),
      reouvertureUpdatePage.setMontantSolliciteInput('5'),
      reouvertureUpdatePage.uniteMontantSelectLastOption(),
      reouvertureUpdatePage.deviseSelectLastOption(),
      reouvertureUpdatePage.setDateEcheanceInput('2000-12-31'),
      reouvertureUpdatePage.setDateValeurInput('2000-12-31'),
      reouvertureUpdatePage.setOperateurInput('operateur'),
      reouvertureUpdatePage.setDateOperationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      reouvertureUpdatePage.emissionSelectLastOption(),
    ]);

    expect(await reouvertureUpdatePage.getCodeValeurInput()).to.eq('codeValeur', 'Expected CodeValeur value to be equals to codeValeur');
    expect(await reouvertureUpdatePage.getDesignationFrInput()).to.eq(
      'designationFr',
      'Expected DesignationFr value to be equals to designationFr'
    );
    expect(await reouvertureUpdatePage.getDesignationEnInput()).to.eq(
      'designationEn',
      'Expected DesignationEn value to be equals to designationEn'
    );
    expect(await reouvertureUpdatePage.getDesignationPtInput()).to.eq(
      'designationPt',
      'Expected DesignationPt value to be equals to designationPt'
    );
    expect(await reouvertureUpdatePage.getDateEmissionInput()).to.eq(
      '2000-12-31',
      'Expected dateEmission value to be equals to 2000-12-31'
    );
    expect(await reouvertureUpdatePage.getTauxInteretInput()).to.eq('5', 'Expected tauxInteret value to be equals to 5');
    expect(await reouvertureUpdatePage.getEncoursEmissionInput()).to.eq('5', 'Expected encoursEmission value to be equals to 5');
    expect(await reouvertureUpdatePage.getMontantSolliciteInput()).to.eq('5', 'Expected montantSollicite value to be equals to 5');
    expect(await reouvertureUpdatePage.getDateEcheanceInput()).to.eq(
      '2000-12-31',
      'Expected dateEcheance value to be equals to 2000-12-31'
    );
    expect(await reouvertureUpdatePage.getDateValeurInput()).to.eq('2000-12-31', 'Expected dateValeur value to be equals to 2000-12-31');
    expect(await reouvertureUpdatePage.getOperateurInput()).to.eq('operateur', 'Expected Operateur value to be equals to operateur');
    expect(await reouvertureUpdatePage.getDateOperationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateOperation value to be equals to 2000-12-31'
    );

    await reouvertureUpdatePage.save();
    expect(await reouvertureUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await reouvertureComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Reouverture', async () => {
    const nbButtonsBeforeDelete = await reouvertureComponentsPage.countDeleteButtons();
    await reouvertureComponentsPage.clickOnLastDeleteButton();

    reouvertureDeleteDialog = new ReouvertureDeleteDialog();
    expect(await reouvertureDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.reouverture.delete.question');
    await reouvertureDeleteDialog.clickOnConfirmButton();

    expect(await reouvertureComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
