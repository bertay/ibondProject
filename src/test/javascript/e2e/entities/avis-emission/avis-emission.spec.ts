import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AvisEmissionComponentsPage, AvisEmissionDeleteDialog, AvisEmissionUpdatePage } from './avis-emission.page-object';

const expect = chai.expect;

describe('AvisEmission e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let avisEmissionComponentsPage: AvisEmissionComponentsPage;
  let avisEmissionUpdatePage: AvisEmissionUpdatePage;
  let avisEmissionDeleteDialog: AvisEmissionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load AvisEmissions', async () => {
    await navBarPage.goToEntity('avis-emission');
    avisEmissionComponentsPage = new AvisEmissionComponentsPage();
    await browser.wait(ec.visibilityOf(avisEmissionComponentsPage.title), 5000);
    expect(await avisEmissionComponentsPage.getTitle()).to.eq('ibondgeneApp.avisEmission.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(avisEmissionComponentsPage.entities), ec.visibilityOf(avisEmissionComponentsPage.noResult)),
      1000
    );
  });

  it('should load create AvisEmission page', async () => {
    await avisEmissionComponentsPage.clickOnCreateButton();
    avisEmissionUpdatePage = new AvisEmissionUpdatePage();
    expect(await avisEmissionUpdatePage.getPageTitle()).to.eq('ibondgeneApp.avisEmission.home.createOrEditLabel');
    await avisEmissionUpdatePage.cancel();
  });

  it('should create and save AvisEmissions', async () => {
    const nbButtonsBeforeCreate = await avisEmissionComponentsPage.countDeleteButtons();

    await avisEmissionComponentsPage.clickOnCreateButton();

    await promise.all([
      avisEmissionUpdatePage.natureSelectLastOption(),
      avisEmissionUpdatePage.setNumeroInput('numero'),
      avisEmissionUpdatePage.setReferenceInput('reference'),
      avisEmissionUpdatePage.setSignataireInput('signataire'),
      avisEmissionUpdatePage.setObjetAvisFrInput('objetAvisFr'),
      avisEmissionUpdatePage.setObjetAvisEnInput('objetAvisEn'),
      avisEmissionUpdatePage.setObjetAvisPtInput('objetAvisPt'),
      avisEmissionUpdatePage.etatAvisSelectLastOption(),
      avisEmissionUpdatePage.setOperateurInput('operateur'),
      avisEmissionUpdatePage.setDateOperationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
    ]);

    expect(await avisEmissionUpdatePage.getNumeroInput()).to.eq('numero', 'Expected Numero value to be equals to numero');
    expect(await avisEmissionUpdatePage.getReferenceInput()).to.eq('reference', 'Expected Reference value to be equals to reference');
    expect(await avisEmissionUpdatePage.getSignataireInput()).to.eq('signataire', 'Expected Signataire value to be equals to signataire');
    expect(await avisEmissionUpdatePage.getObjetAvisFrInput()).to.eq(
      'objetAvisFr',
      'Expected ObjetAvisFr value to be equals to objetAvisFr'
    );
    expect(await avisEmissionUpdatePage.getObjetAvisEnInput()).to.eq(
      'objetAvisEn',
      'Expected ObjetAvisEn value to be equals to objetAvisEn'
    );
    expect(await avisEmissionUpdatePage.getObjetAvisPtInput()).to.eq(
      'objetAvisPt',
      'Expected ObjetAvisPt value to be equals to objetAvisPt'
    );
    expect(await avisEmissionUpdatePage.getOperateurInput()).to.eq('operateur', 'Expected Operateur value to be equals to operateur');
    expect(await avisEmissionUpdatePage.getDateOperationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateOperation value to be equals to 2000-12-31'
    );

    await avisEmissionUpdatePage.save();
    expect(await avisEmissionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await avisEmissionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last AvisEmission', async () => {
    const nbButtonsBeforeDelete = await avisEmissionComponentsPage.countDeleteButtons();
    await avisEmissionComponentsPage.clickOnLastDeleteButton();

    avisEmissionDeleteDialog = new AvisEmissionDeleteDialog();
    expect(await avisEmissionDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.avisEmission.delete.question');
    await avisEmissionDeleteDialog.clickOnConfirmButton();

    expect(await avisEmissionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
