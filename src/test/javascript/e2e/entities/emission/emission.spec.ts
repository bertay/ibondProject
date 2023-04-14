import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EmissionComponentsPage, EmissionDeleteDialog, EmissionUpdatePage } from './emission.page-object';

const expect = chai.expect;

describe('Emission e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let emissionComponentsPage: EmissionComponentsPage;
  let emissionUpdatePage: EmissionUpdatePage;
  let emissionDeleteDialog: EmissionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Emissions', async () => {
    await navBarPage.goToEntity('emission');
    emissionComponentsPage = new EmissionComponentsPage();
    await browser.wait(ec.visibilityOf(emissionComponentsPage.title), 5000);
    expect(await emissionComponentsPage.getTitle()).to.eq('ibondgeneApp.emission.home.title');
    await browser.wait(ec.or(ec.visibilityOf(emissionComponentsPage.entities), ec.visibilityOf(emissionComponentsPage.noResult)), 1000);
  });

  it('should load create Emission page', async () => {
    await emissionComponentsPage.clickOnCreateButton();
    emissionUpdatePage = new EmissionUpdatePage();
    expect(await emissionUpdatePage.getPageTitle()).to.eq('ibondgeneApp.emission.home.createOrEditLabel');
    await emissionUpdatePage.cancel();
  });

  it('should create and save Emissions', async () => {
    const nbButtonsBeforeCreate = await emissionComponentsPage.countDeleteButtons();

    await emissionComponentsPage.clickOnCreateButton();

    await promise.all([
      emissionUpdatePage.setCodeEmissionInput('codeEmission'),
      emissionUpdatePage.setDesignationFrInput('designationFr'),
      emissionUpdatePage.setDesignationEnInput('designationEn'),
      emissionUpdatePage.setDesignationPtInput('designationPt'),
      emissionUpdatePage.setDateEmissionInput('2000-12-31'),
      emissionUpdatePage.setEcheanceInput('2000-12-31'),
      emissionUpdatePage.setDureeInput('duree'),
      emissionUpdatePage.setRemboursementInput('remboursement'),
      emissionUpdatePage.setFormeTitreInput('formeTitre'),
      emissionUpdatePage.setTauxInteretInput('5'),
      emissionUpdatePage.setVolumeEmissionInput('5'),
      emissionUpdatePage.uniteVolumeSelectLastOption(),
      emissionUpdatePage.setValeurNominaleInput('5'),
      emissionUpdatePage.deviseSelectLastOption(),
      emissionUpdatePage.setQuantiteTitreInput('5'),
      emissionUpdatePage.setRendementInput('rendement'),
      emissionUpdatePage.setDateLimiteInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      emissionUpdatePage.setLieuSouscriptionInput('lieuSouscription'),
      emissionUpdatePage.setDateResultatInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      emissionUpdatePage.setDateReglementInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      emissionUpdatePage.setDateValeurInput('2000-12-31'),
      emissionUpdatePage.setOperateurInput('operateur'),
      emissionUpdatePage.setDateOperationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      emissionUpdatePage.avisEmissionSelectLastOption(),
      emissionUpdatePage.natureTitreSelectLastOption(),
    ]);

    expect(await emissionUpdatePage.getCodeEmissionInput()).to.eq(
      'codeEmission',
      'Expected CodeEmission value to be equals to codeEmission'
    );
    expect(await emissionUpdatePage.getDesignationFrInput()).to.eq(
      'designationFr',
      'Expected DesignationFr value to be equals to designationFr'
    );
    expect(await emissionUpdatePage.getDesignationEnInput()).to.eq(
      'designationEn',
      'Expected DesignationEn value to be equals to designationEn'
    );
    expect(await emissionUpdatePage.getDesignationPtInput()).to.eq(
      'designationPt',
      'Expected DesignationPt value to be equals to designationPt'
    );
    expect(await emissionUpdatePage.getDateEmissionInput()).to.eq('2000-12-31', 'Expected dateEmission value to be equals to 2000-12-31');
    expect(await emissionUpdatePage.getEcheanceInput()).to.eq('2000-12-31', 'Expected echeance value to be equals to 2000-12-31');
    expect(await emissionUpdatePage.getDureeInput()).to.eq('duree', 'Expected Duree value to be equals to duree');
    expect(await emissionUpdatePage.getRemboursementInput()).to.eq(
      'remboursement',
      'Expected Remboursement value to be equals to remboursement'
    );
    expect(await emissionUpdatePage.getFormeTitreInput()).to.eq('formeTitre', 'Expected FormeTitre value to be equals to formeTitre');
    expect(await emissionUpdatePage.getTauxInteretInput()).to.eq('5', 'Expected tauxInteret value to be equals to 5');
    expect(await emissionUpdatePage.getVolumeEmissionInput()).to.eq('5', 'Expected volumeEmission value to be equals to 5');
    expect(await emissionUpdatePage.getValeurNominaleInput()).to.eq('5', 'Expected valeurNominale value to be equals to 5');
    expect(await emissionUpdatePage.getQuantiteTitreInput()).to.eq('5', 'Expected quantiteTitre value to be equals to 5');
    expect(await emissionUpdatePage.getRendementInput()).to.eq('rendement', 'Expected Rendement value to be equals to rendement');
    expect(await emissionUpdatePage.getDateLimiteInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateLimite value to be equals to 2000-12-31'
    );
    expect(await emissionUpdatePage.getLieuSouscriptionInput()).to.eq(
      'lieuSouscription',
      'Expected LieuSouscription value to be equals to lieuSouscription'
    );
    expect(await emissionUpdatePage.getDateResultatInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateResultat value to be equals to 2000-12-31'
    );
    expect(await emissionUpdatePage.getDateReglementInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateReglement value to be equals to 2000-12-31'
    );
    expect(await emissionUpdatePage.getDateValeurInput()).to.eq('2000-12-31', 'Expected dateValeur value to be equals to 2000-12-31');
    expect(await emissionUpdatePage.getOperateurInput()).to.eq('operateur', 'Expected Operateur value to be equals to operateur');
    expect(await emissionUpdatePage.getDateOperationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateOperation value to be equals to 2000-12-31'
    );

    await emissionUpdatePage.save();
    expect(await emissionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await emissionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Emission', async () => {
    const nbButtonsBeforeDelete = await emissionComponentsPage.countDeleteButtons();
    await emissionComponentsPage.clickOnLastDeleteButton();

    emissionDeleteDialog = new EmissionDeleteDialog();
    expect(await emissionDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.emission.delete.question');
    await emissionDeleteDialog.clickOnConfirmButton();

    expect(await emissionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
