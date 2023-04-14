import { element, by, ElementFinder } from 'protractor';

export class ParametresComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-parametres div table .btn-danger'));
  title = element.all(by.css('jhi-parametres div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class ParametresUpdatePage {
  pageTitle = element(by.id('jhi-parametres-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  adresseServeurInput = element(by.id('field_adresseServeur'));
  timbreService1Input = element(by.id('field_timbreService1'));
  timbreService2Input = element(by.id('field_timbreService2'));
  timbreService3Input = element(by.id('field_timbreService3'));
  operateurInput = element(by.id('field_operateur'));
  dateOperationInput = element(by.id('field_dateOperation'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setAdresseServeurInput(adresseServeur: string): Promise<void> {
    await this.adresseServeurInput.sendKeys(adresseServeur);
  }

  async getAdresseServeurInput(): Promise<string> {
    return await this.adresseServeurInput.getAttribute('value');
  }

  async setTimbreService1Input(timbreService1: string): Promise<void> {
    await this.timbreService1Input.sendKeys(timbreService1);
  }

  async getTimbreService1Input(): Promise<string> {
    return await this.timbreService1Input.getAttribute('value');
  }

  async setTimbreService2Input(timbreService2: string): Promise<void> {
    await this.timbreService2Input.sendKeys(timbreService2);
  }

  async getTimbreService2Input(): Promise<string> {
    return await this.timbreService2Input.getAttribute('value');
  }

  async setTimbreService3Input(timbreService3: string): Promise<void> {
    await this.timbreService3Input.sendKeys(timbreService3);
  }

  async getTimbreService3Input(): Promise<string> {
    return await this.timbreService3Input.getAttribute('value');
  }

  async setOperateurInput(operateur: string): Promise<void> {
    await this.operateurInput.sendKeys(operateur);
  }

  async getOperateurInput(): Promise<string> {
    return await this.operateurInput.getAttribute('value');
  }

  async setDateOperationInput(dateOperation: string): Promise<void> {
    await this.dateOperationInput.sendKeys(dateOperation);
  }

  async getDateOperationInput(): Promise<string> {
    return await this.dateOperationInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class ParametresDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-parametres-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-parametres'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
