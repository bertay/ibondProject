import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DetailCalendrierComponentsPage, DetailCalendrierDeleteDialog, DetailCalendrierUpdatePage } from './detail-calendrier.page-object';

const expect = chai.expect;

describe('DetailCalendrier e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let detailCalendrierComponentsPage: DetailCalendrierComponentsPage;
  let detailCalendrierUpdatePage: DetailCalendrierUpdatePage;
  let detailCalendrierDeleteDialog: DetailCalendrierDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DetailCalendriers', async () => {
    await navBarPage.goToEntity('detail-calendrier');
    detailCalendrierComponentsPage = new DetailCalendrierComponentsPage();
    await browser.wait(ec.visibilityOf(detailCalendrierComponentsPage.title), 5000);
    expect(await detailCalendrierComponentsPage.getTitle()).to.eq('ibondgeneApp.detailCalendrier.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(detailCalendrierComponentsPage.entities), ec.visibilityOf(detailCalendrierComponentsPage.noResult)),
      1000
    );
  });

  it('should load create DetailCalendrier page', async () => {
    await detailCalendrierComponentsPage.clickOnCreateButton();
    detailCalendrierUpdatePage = new DetailCalendrierUpdatePage();
    expect(await detailCalendrierUpdatePage.getPageTitle()).to.eq('ibondgeneApp.detailCalendrier.home.createOrEditLabel');
    await detailCalendrierUpdatePage.cancel();
  });

  it('should create and save DetailCalendriers', async () => {
    const nbButtonsBeforeCreate = await detailCalendrierComponentsPage.countDeleteButtons();

    await detailCalendrierComponentsPage.clickOnCreateButton();

    await promise.all([
      detailCalendrierUpdatePage.setPeriodeInput('periode'),
      detailCalendrierUpdatePage.setAnneeInput('5'),
      detailCalendrierUpdatePage.setDateAnnonceInput('2000-12-31'),
      detailCalendrierUpdatePage.setDateAdjudicationInput('2000-12-31'),
      detailCalendrierUpdatePage.setDateEcheanceInput('2000-12-31'),
      detailCalendrierUpdatePage.setDureeInput('duree'),
      detailCalendrierUpdatePage.setVolumeEmissionInput('5'),
      detailCalendrierUpdatePage.uniteVolumeSelectLastOption(),
      detailCalendrierUpdatePage.deviseSelectLastOption(),
      detailCalendrierUpdatePage.setDateValeurInput('2000-12-31'),
      detailCalendrierUpdatePage.natureSelectLastOption(),
      detailCalendrierUpdatePage.setOperateurInput('operateur'),
      detailCalendrierUpdatePage.setDateOperationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      detailCalendrierUpdatePage.calendrierSelectLastOption(),
    ]);

    expect(await detailCalendrierUpdatePage.getPeriodeInput()).to.eq('periode', 'Expected Periode value to be equals to periode');
    expect(await detailCalendrierUpdatePage.getAnneeInput()).to.eq('5', 'Expected annee value to be equals to 5');
    expect(await detailCalendrierUpdatePage.getDateAnnonceInput()).to.eq(
      '2000-12-31',
      'Expected dateAnnonce value to be equals to 2000-12-31'
    );
    expect(await detailCalendrierUpdatePage.getDateAdjudicationInput()).to.eq(
      '2000-12-31',
      'Expected dateAdjudication value to be equals to 2000-12-31'
    );
    expect(await detailCalendrierUpdatePage.getDateEcheanceInput()).to.eq(
      '2000-12-31',
      'Expected dateEcheance value to be equals to 2000-12-31'
    );
    expect(await detailCalendrierUpdatePage.getDureeInput()).to.eq('duree', 'Expected Duree value to be equals to duree');
    expect(await detailCalendrierUpdatePage.getVolumeEmissionInput()).to.eq('5', 'Expected volumeEmission value to be equals to 5');
    expect(await detailCalendrierUpdatePage.getDateValeurInput()).to.eq(
      '2000-12-31',
      'Expected dateValeur value to be equals to 2000-12-31'
    );
    expect(await detailCalendrierUpdatePage.getOperateurInput()).to.eq('operateur', 'Expected Operateur value to be equals to operateur');
    expect(await detailCalendrierUpdatePage.getDateOperationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateOperation value to be equals to 2000-12-31'
    );

    await detailCalendrierUpdatePage.save();
    expect(await detailCalendrierUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await detailCalendrierComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last DetailCalendrier', async () => {
    const nbButtonsBeforeDelete = await detailCalendrierComponentsPage.countDeleteButtons();
    await detailCalendrierComponentsPage.clickOnLastDeleteButton();

    detailCalendrierDeleteDialog = new DetailCalendrierDeleteDialog();
    expect(await detailCalendrierDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.detailCalendrier.delete.question');
    await detailCalendrierDeleteDialog.clickOnConfirmButton();

    expect(await detailCalendrierComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
