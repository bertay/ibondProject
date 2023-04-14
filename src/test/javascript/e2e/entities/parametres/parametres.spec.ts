import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ParametresComponentsPage, ParametresDeleteDialog, ParametresUpdatePage } from './parametres.page-object';

const expect = chai.expect;

describe('Parametres e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let parametresComponentsPage: ParametresComponentsPage;
  let parametresUpdatePage: ParametresUpdatePage;
  let parametresDeleteDialog: ParametresDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Parametres', async () => {
    await navBarPage.goToEntity('parametres');
    parametresComponentsPage = new ParametresComponentsPage();
    await browser.wait(ec.visibilityOf(parametresComponentsPage.title), 5000);
    expect(await parametresComponentsPage.getTitle()).to.eq('ibondgeneApp.parametres.home.title');
    await browser.wait(ec.or(ec.visibilityOf(parametresComponentsPage.entities), ec.visibilityOf(parametresComponentsPage.noResult)), 1000);
  });

  it('should load create Parametres page', async () => {
    await parametresComponentsPage.clickOnCreateButton();
    parametresUpdatePage = new ParametresUpdatePage();
    expect(await parametresUpdatePage.getPageTitle()).to.eq('ibondgeneApp.parametres.home.createOrEditLabel');
    await parametresUpdatePage.cancel();
  });

  it('should create and save Parametres', async () => {
    const nbButtonsBeforeCreate = await parametresComponentsPage.countDeleteButtons();

    await parametresComponentsPage.clickOnCreateButton();

    await promise.all([
      parametresUpdatePage.setAdresseServeurInput('adresseServeur'),
      parametresUpdatePage.setTimbreService1Input('timbreService1'),
      parametresUpdatePage.setTimbreService2Input('timbreService2'),
      parametresUpdatePage.setTimbreService3Input('timbreService3'),
      parametresUpdatePage.setOperateurInput('operateur'),
      parametresUpdatePage.setDateOperationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
    ]);

    expect(await parametresUpdatePage.getAdresseServeurInput()).to.eq(
      'adresseServeur',
      'Expected AdresseServeur value to be equals to adresseServeur'
    );
    expect(await parametresUpdatePage.getTimbreService1Input()).to.eq(
      'timbreService1',
      'Expected TimbreService1 value to be equals to timbreService1'
    );
    expect(await parametresUpdatePage.getTimbreService2Input()).to.eq(
      'timbreService2',
      'Expected TimbreService2 value to be equals to timbreService2'
    );
    expect(await parametresUpdatePage.getTimbreService3Input()).to.eq(
      'timbreService3',
      'Expected TimbreService3 value to be equals to timbreService3'
    );
    expect(await parametresUpdatePage.getOperateurInput()).to.eq('operateur', 'Expected Operateur value to be equals to operateur');
    expect(await parametresUpdatePage.getDateOperationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateOperation value to be equals to 2000-12-31'
    );

    await parametresUpdatePage.save();
    expect(await parametresUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await parametresComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Parametres', async () => {
    const nbButtonsBeforeDelete = await parametresComponentsPage.countDeleteButtons();
    await parametresComponentsPage.clickOnLastDeleteButton();

    parametresDeleteDialog = new ParametresDeleteDialog();
    expect(await parametresDeleteDialog.getDialogTitle()).to.eq('ibondgeneApp.parametres.delete.question');
    await parametresDeleteDialog.clickOnConfirmButton();

    expect(await parametresComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
