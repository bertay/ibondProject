import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { RachatComponentsPage, RachatDeleteDialog, RachatUpdatePage } from './rachat.page-object';

const expect = chai.expect;

describe('Rachat e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let rachatComponentsPage: RachatComponentsPage;
  let rachatUpdatePage: RachatUpdatePage;
  let rachatDeleteDialog: RachatDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Rachats', async () => {
    await navBarPage.goToEntity('rachat');
    rachatComponentsPage = new RachatComponentsPage();
    await browser.wait(ec.visibilityOf(rachatComponentsPage.title), 5000);
    expect(await rachatComponentsPage.getTitle()).to.eq('ibondgeneApp.rachat.home.title');
    await browser.wait(ec.or(ec.visibilityOf(rachatComponentsPage.entities), ec.visibilityOf(rachatComponentsPage.noResult)), 1000);
  });

  it('should load create Rachat page', async () => {
    await rachatComponentsPage.clickOnCreateButton();
    rachatUpdatePage = new RachatUpdatePage();
    expect(await rachatUpdatePage.getPageTitle()).to.eq('ibondgeneApp.rachat.home.createOrEditLabel');
    await rachatUpdatePage.cancel();
  });

  it('should create and save Rachats', async () => {
    const nbButtonsBeforeCreate = await rachatComponentsPage.countDeleteButtons();

    await rachatComponentsPage.clickOnCreateButton();

    await promise.all([
      rachatUpdatePage.setCodeValeurInput('codeValeur'),
      rachatUpdatePage.setDesignationFrInput('designationFr'),
      rachatUpdatePage.setDesignationEnInput('designationEn'),
      rachatUpdatePage.setDesignationPtInput('designationPt'),
      rachatUpdatePage.setDateEmissionInput('2000-12-31'),
      rachatUpdatePage.setTauxInteretInput('5'),
      rachatUpdatePage.setMontantNominalInput('5'),
      rachatUpdatePage.uniteMontantSelectLastOption(),
      rachatUpdatePage.deviseSelectLastOption(),
      rachatUpdatePage.setDateEcheanceInput('2000-12-31'),
      rachatUpdatePage.setDateValeurInput('2000-12-31'),
      rachatUpdatePage.setOperateurInput('operateur'),
      rachatUpdatePage.setDateOperationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      rachatUpdatePage.emissionSelectLastOption(),
    ]);

    expect(await rachatUpdatePage.getCodeValeurInput()).to.eq('codeValeur', 'Expected CodeValeur value to be equals to codeValeur');
    expect(await rachatUpdatePage.getDesignationFrInput()).to.eq(
      'designationFr',
      'Expected DesignationFr value to be equals to designationFr'
    );
    expect(await rachatUpdatePage.getDesignationEnInput()).to.eq(
      'designationEn',
      'Expected DesignationEn value to be equals to designationEn'
    );
    expect(await rachatUpdatePage.getDesignationPtInput()).to.eq(
      'designationPt',
      'Expected DesignationPt value to be equals to designationPt'
    );
    expect(await rachatUpdatePage.getDateEmissionInput()).to.eq('2000-12-31', 'Expected dateEmission value to be equals to 2000-12-31');
    expect(await rachatUpdatePage.getTauxInteretInput()).to.eq('5', 'Expected tauxInteret value to be equals to 5');
    expect(await rachatUpdatePage.getMontantNominalInput()).to.eq('5', 'Expected montantNominal value to be equals to 5');
    expect(await rachatUpdatePage.getDateEcheanceInput()).to.eq('2000-12-31', 'Expected dateEcheance value to be equals to 2000-12-31');
    expect(await rachatUpdatePage.getDateValeurInput()).to.eq('2000-12-31', 'Expected dateValeur value to be equals to 2000-12-31');
    expect(await rachatUpdatePage.getOperateurInput()).to.eq('operateur', 'Expected Operateur value to be equals to operateur');
    expect(await rachatUpdatePage.getDateOperationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateOperation value to be equals to 2000-12-31'
    );

    await rachatUpdatePage.save();
    expect(await rachatUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await rachatComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Rachat', async () => {
    const nbButtonsBeforeDelete = await rachatComponentsPage.countDeleteButtons();
    await rachatComponentsPage.clickOnLastDeleteButton();

    rachatDeleteDialog = new RachatDeleteDialog();
    expect(await rachatDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.rachat.delete.question');
    await rachatDeleteDialog.clickOnConfirmButton();

    expect(await rachatComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
