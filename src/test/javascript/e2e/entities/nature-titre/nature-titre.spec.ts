import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { NatureTitreComponentsPage, NatureTitreDeleteDialog, NatureTitreUpdatePage } from './nature-titre.page-object';

const expect = chai.expect;

describe('NatureTitre e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let natureTitreComponentsPage: NatureTitreComponentsPage;
  let natureTitreUpdatePage: NatureTitreUpdatePage;
  let natureTitreDeleteDialog: NatureTitreDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load NatureTitres', async () => {
    await navBarPage.goToEntity('nature-titre');
    natureTitreComponentsPage = new NatureTitreComponentsPage();
    await browser.wait(ec.visibilityOf(natureTitreComponentsPage.title), 5000);
    expect(await natureTitreComponentsPage.getTitle()).to.eq('ibondgeneApp.natureTitre.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(natureTitreComponentsPage.entities), ec.visibilityOf(natureTitreComponentsPage.noResult)),
      1000
    );
  });

  it('should load create NatureTitre page', async () => {
    await natureTitreComponentsPage.clickOnCreateButton();
    natureTitreUpdatePage = new NatureTitreUpdatePage();
    expect(await natureTitreUpdatePage.getPageTitle()).to.eq('ibondgeneApp.natureTitre.home.createOrEditLabel');
    await natureTitreUpdatePage.cancel();
  });

  it('should create and save NatureTitres', async () => {
    const nbButtonsBeforeCreate = await natureTitreComponentsPage.countDeleteButtons();

    await natureTitreComponentsPage.clickOnCreateButton();

    await promise.all([
      natureTitreUpdatePage.setCodeNatureInput('codeNature'),
      natureTitreUpdatePage.setDesignationFrInput('designationFr'),
      natureTitreUpdatePage.setDesignationEnInput('designationEn'),
      natureTitreUpdatePage.setDesignationPtInput('designationPt'),
      natureTitreUpdatePage.setNominalUnitaireInput('5'),
      natureTitreUpdatePage.uniteValeurSelectLastOption(),
      natureTitreUpdatePage.natureEcheanceSelectLastOption(),
      natureTitreUpdatePage.setOperateurInput('operateur'),
      natureTitreUpdatePage.setDateOperationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
    ]);

    expect(await natureTitreUpdatePage.getCodeNatureInput()).to.eq('codeNature', 'Expected CodeNature value to be equals to codeNature');
    expect(await natureTitreUpdatePage.getDesignationFrInput()).to.eq(
      'designationFr',
      'Expected DesignationFr value to be equals to designationFr'
    );
    expect(await natureTitreUpdatePage.getDesignationEnInput()).to.eq(
      'designationEn',
      'Expected DesignationEn value to be equals to designationEn'
    );
    expect(await natureTitreUpdatePage.getDesignationPtInput()).to.eq(
      'designationPt',
      'Expected DesignationPt value to be equals to designationPt'
    );
    expect(await natureTitreUpdatePage.getNominalUnitaireInput()).to.eq('5', 'Expected nominalUnitaire value to be equals to 5');
    expect(await natureTitreUpdatePage.getOperateurInput()).to.eq('operateur', 'Expected Operateur value to be equals to operateur');
    expect(await natureTitreUpdatePage.getDateOperationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateOperation value to be equals to 2000-12-31'
    );

    await natureTitreUpdatePage.save();
    expect(await natureTitreUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await natureTitreComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last NatureTitre', async () => {
    const nbButtonsBeforeDelete = await natureTitreComponentsPage.countDeleteButtons();
    await natureTitreComponentsPage.clickOnLastDeleteButton();

    natureTitreDeleteDialog = new NatureTitreDeleteDialog();
    expect(await natureTitreDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.natureTitre.delete.question');
    await natureTitreDeleteDialog.clickOnConfirmButton();

    expect(await natureTitreComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
