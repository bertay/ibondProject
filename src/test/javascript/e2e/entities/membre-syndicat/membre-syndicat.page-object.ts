import { element, by, ElementFinder } from 'protractor';

export class MembreSyndicatComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-membre-syndicat div table .btn-danger'));
  title = element.all(by.css('jhi-membre-syndicat div h2#page-heading span')).first();
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

export class MembreSyndicatUpdatePage {
  pageTitle = element(by.id('jhi-membre-syndicat-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  rangSelect = element(by.id('field_rang'));
  commissionInput = element(by.id('field_commission'));
  operateurInput = element(by.id('field_operateur'));
  dateOperationInput = element(by.id('field_dateOperation'));

  emissionSelect = element(by.id('field_emission'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setRangSelect(rang: string): Promise<void> {
    await this.rangSelect.sendKeys(rang);
  }

  async getRangSelect(): Promise<string> {
    return await this.rangSelect.element(by.css('option:checked')).getText();
  }

  async rangSelectLastOption(): Promise<void> {
    await this.rangSelect.all(by.tagName('option')).last().click();
  }

  async setCommissionInput(commission: string): Promise<void> {
    await this.commissionInput.sendKeys(commission);
  }

  async getCommissionInput(): Promise<string> {
    return await this.commissionInput.getAttribute('value');
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

  async emissionSelectLastOption(): Promise<void> {
    await this.emissionSelect.all(by.tagName('option')).last().click();
  }

  async emissionSelectOption(option: string): Promise<void> {
    await this.emissionSelect.sendKeys(option);
  }

  getEmissionSelect(): ElementFinder {
    return this.emissionSelect;
  }

  async getEmissionSelectedOption(): Promise<string> {
    return await this.emissionSelect.element(by.css('option:checked')).getText();
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

export class MembreSyndicatDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-membreSyndicat-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-membreSyndicat'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
