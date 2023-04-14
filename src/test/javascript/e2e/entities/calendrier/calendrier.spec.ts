import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CalendrierComponentsPage, CalendrierDeleteDialog, CalendrierUpdatePage } from './calendrier.page-object';

const expect = chai.expect;

describe('Calendrier e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let calendrierComponentsPage: CalendrierComponentsPage;
  let calendrierUpdatePage: CalendrierUpdatePage;
  let calendrierDeleteDialog: CalendrierDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Calendriers', async () => {
    await navBarPage.goToEntity('calendrier');
    calendrierComponentsPage = new CalendrierComponentsPage();
    await browser.wait(ec.visibilityOf(calendrierComponentsPage.title), 5000);
    expect(await calendrierComponentsPage.getTitle()).to.eq('ibondgeneApp.calendrier.home.title');
    await browser.wait(ec.or(ec.visibilityOf(calendrierComponentsPage.entities), ec.visibilityOf(calendrierComponentsPage.noResult)), 1000);
  });

  it('should load create Calendrier page', async () => {
    await calendrierComponentsPage.clickOnCreateButton();
    calendrierUpdatePage = new CalendrierUpdatePage();
    expect(await calendrierUpdatePage.getPageTitle()).to.eq('ibondgeneApp.calendrier.home.createOrEditLabel');
    await calendrierUpdatePage.cancel();
  });

  it('should create and save Calendriers', async () => {
    const nbButtonsBeforeCreate = await calendrierComponentsPage.countDeleteButtons();

    await calendrierComponentsPage.clickOnCreateButton();

    await promise.all([
      calendrierUpdatePage.natureSelectLastOption(),
      calendrierUpdatePage.setNumeroInput('numero'),
      calendrierUpdatePage.setReferenceInput('reference'),
      calendrierUpdatePage.setSignataireInput('signataire'),
      calendrierUpdatePage.setTitreCalendrierFrInput('titreCalendrierFr'),
      calendrierUpdatePage.setTitreCalendrierEnInput('titreCalendrierEn'),
      calendrierUpdatePage.setTitreCalendrierPtInput('titreCalendrierPt'),
      calendrierUpdatePage.etatCalendrierSelectLastOption(),
      calendrierUpdatePage.setOperateurInput('operateur'),
      calendrierUpdatePage.setDateOperationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
    ]);

    expect(await calendrierUpdatePage.getNumeroInput()).to.eq('numero', 'Expected Numero value to be equals to numero');
    expect(await calendrierUpdatePage.getReferenceInput()).to.eq('reference', 'Expected Reference value to be equals to reference');
    expect(await calendrierUpdatePage.getSignataireInput()).to.eq('signataire', 'Expected Signataire value to be equals to signataire');
    expect(await calendrierUpdatePage.getTitreCalendrierFrInput()).to.eq(
      'titreCalendrierFr',
      'Expected TitreCalendrierFr value to be equals to titreCalendrierFr'
    );
    expect(await calendrierUpdatePage.getTitreCalendrierEnInput()).to.eq(
      'titreCalendrierEn',
      'Expected TitreCalendrierEn value to be equals to titreCalendrierEn'
    );
    expect(await calendrierUpdatePage.getTitreCalendrierPtInput()).to.eq(
      'titreCalendrierPt',
      'Expected TitreCalendrierPt value to be equals to titreCalendrierPt'
    );
    expect(await calendrierUpdatePage.getOperateurInput()).to.eq('operateur', 'Expected Operateur value to be equals to operateur');
    expect(await calendrierUpdatePage.getDateOperationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateOperation value to be equals to 2000-12-31'
    );

    await calendrierUpdatePage.save();
    expect(await calendrierUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await calendrierComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Calendrier', async () => {
    const nbButtonsBeforeDelete = await calendrierComponentsPage.countDeleteButtons();
    await calendrierComponentsPage.clickOnLastDeleteButton();

    calendrierDeleteDialog = new CalendrierDeleteDialog();
    expect(await calendrierDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.calendrier.delete.question');
    await calendrierDeleteDialog.clickOnConfirmButton();

    expect(await calendrierComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
