import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ResultatComponentsPage, ResultatDeleteDialog, ResultatUpdatePage } from './resultat.page-object';

const expect = chai.expect;

describe('Resultat e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let resultatComponentsPage: ResultatComponentsPage;
  let resultatUpdatePage: ResultatUpdatePage;
  let resultatDeleteDialog: ResultatDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Resultats', async () => {
    await navBarPage.goToEntity('resultat');
    resultatComponentsPage = new ResultatComponentsPage();
    await browser.wait(ec.visibilityOf(resultatComponentsPage.title), 5000);
    expect(await resultatComponentsPage.getTitle()).to.eq('ibondgeneApp.resultat.home.title');
    await browser.wait(ec.or(ec.visibilityOf(resultatComponentsPage.entities), ec.visibilityOf(resultatComponentsPage.noResult)), 1000);
  });

  it('should load create Resultat page', async () => {
    await resultatComponentsPage.clickOnCreateButton();
    resultatUpdatePage = new ResultatUpdatePage();
    expect(await resultatUpdatePage.getPageTitle()).to.eq('ibondgeneApp.resultat.home.createOrEditLabel');
    await resultatUpdatePage.cancel();
  });

  it('should create and save Resultats', async () => {
    const nbButtonsBeforeCreate = await resultatComponentsPage.countDeleteButtons();

    await resultatComponentsPage.clickOnCreateButton();

    await promise.all([
      resultatUpdatePage.setNbreSvtTotalInput('5'),
      resultatUpdatePage.setNbreSvtSoumisInput('5'),
      resultatUpdatePage.setMontantTresorInput('5'),
      resultatUpdatePage.uniteTresorSelectLastOption(),
      resultatUpdatePage.setMontantSoumisInput('5'),
      resultatUpdatePage.uniteSoumisSelectLastOption(),
      resultatUpdatePage.setMontantServiInput('5'),
      resultatUpdatePage.setNbreTitreTotalInput('5'),
      resultatUpdatePage.setNbreTitreSoumisInput('5'),
      resultatUpdatePage.setTauxMinProposeInput('5'),
      resultatUpdatePage.setTauxMaxProposeInput('5'),
      resultatUpdatePage.setTauxLimiteInput('5'),
      resultatUpdatePage.setTauxInteretMoyenInput('5'),
      resultatUpdatePage.setTauxRendementMoyenInput('5'),
      resultatUpdatePage.setTauxCouvertureInput('5'),
      resultatUpdatePage.emissionSelectLastOption(),
      resultatUpdatePage.resultatSelectLastOption(),
      resultatUpdatePage.rachatSelectLastOption(),
      resultatUpdatePage.oncSelectLastOption(),
    ]);

    expect(await resultatUpdatePage.getNbreSvtTotalInput()).to.eq('5', 'Expected nbreSvtTotal value to be equals to 5');
    expect(await resultatUpdatePage.getNbreSvtSoumisInput()).to.eq('5', 'Expected nbreSvtSoumis value to be equals to 5');
    expect(await resultatUpdatePage.getMontantTresorInput()).to.eq('5', 'Expected montantTresor value to be equals to 5');
    expect(await resultatUpdatePage.getMontantSoumisInput()).to.eq('5', 'Expected montantSoumis value to be equals to 5');
    expect(await resultatUpdatePage.getMontantServiInput()).to.eq('5', 'Expected montantServi value to be equals to 5');
    expect(await resultatUpdatePage.getNbreTitreTotalInput()).to.eq('5', 'Expected nbreTitreTotal value to be equals to 5');
    expect(await resultatUpdatePage.getNbreTitreSoumisInput()).to.eq('5', 'Expected nbreTitreSoumis value to be equals to 5');
    expect(await resultatUpdatePage.getTauxMinProposeInput()).to.eq('5', 'Expected tauxMinPropose value to be equals to 5');
    expect(await resultatUpdatePage.getTauxMaxProposeInput()).to.eq('5', 'Expected tauxMaxPropose value to be equals to 5');
    expect(await resultatUpdatePage.getTauxLimiteInput()).to.eq('5', 'Expected tauxLimite value to be equals to 5');
    expect(await resultatUpdatePage.getTauxInteretMoyenInput()).to.eq('5', 'Expected tauxInteretMoyen value to be equals to 5');
    expect(await resultatUpdatePage.getTauxRendementMoyenInput()).to.eq('5', 'Expected tauxRendementMoyen value to be equals to 5');
    expect(await resultatUpdatePage.getTauxCouvertureInput()).to.eq('5', 'Expected tauxCouverture value to be equals to 5');

    await resultatUpdatePage.save();
    expect(await resultatUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await resultatComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Resultat', async () => {
    const nbButtonsBeforeDelete = await resultatComponentsPage.countDeleteButtons();
    await resultatComponentsPage.clickOnLastDeleteButton();

    resultatDeleteDialog = new ResultatDeleteDialog();
    expect(await resultatDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.resultat.delete.question');
    await resultatDeleteDialog.clickOnConfirmButton();

    expect(await resultatComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
