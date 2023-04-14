import { element, by, ElementFinder } from 'protractor';

export class SoumissionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-soumission div table .btn-danger'));
  title = element.all(by.css('jhi-soumission div h2#page-heading span')).first();
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

export class SoumissionUpdatePage {
  pageTitle = element(by.id('jhi-soumission-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  numAnonymatInput = element(by.id('field_numAnonymat'));
  dateSoumissionInput = element(by.id('field_dateSoumission'));
  nbreSoumissionInput = element(by.id('field_nbreSoumission'));
  operateurInput = element(by.id('field_operateur'));
  dateOperationInput = element(by.id('field_dateOperation'));

  emissionSelect = element(by.id('field_emission'));
  reouvertureSelect = element(by.id('field_reouverture'));
  rachatSelect = element(by.id('field_rachat'));
  oncSelect = element(by.id('field_onc'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNumAnonymatInput(numAnonymat: string): Promise<void> {
    await this.numAnonymatInput.sendKeys(numAnonymat);
  }

  async getNumAnonymatInput(): Promise<string> {
    return await this.numAnonymatInput.getAttribute('value');
  }

  async setDateSoumissionInput(dateSoumission: string): Promise<void> {
    await this.dateSoumissionInput.sendKeys(dateSoumission);
  }

  async getDateSoumissionInput(): Promise<string> {
    return await this.dateSoumissionInput.getAttribute('value');
  }

  async setNbreSoumissionInput(nbreSoumission: string): Promise<void> {
    await this.nbreSoumissionInput.sendKeys(nbreSoumission);
  }

  async getNbreSoumissionInput(): Promise<string> {
    return await this.nbreSoumissionInput.getAttribute('value');
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

  async reouvertureSelectLastOption(): Promise<void> {
    await this.reouvertureSelect.all(by.tagName('option')).last().click();
  }

  async reouvertureSelectOption(option: string): Promise<void> {
    await this.reouvertureSelect.sendKeys(option);
  }

  getReouvertureSelect(): ElementFinder {
    return this.reouvertureSelect;
  }

  async getReouvertureSelectedOption(): Promise<string> {
    return await this.reouvertureSelect.element(by.css('option:checked')).getText();
  }

  async rachatSelectLastOption(): Promise<void> {
    await this.rachatSelect.all(by.tagName('option')).last().click();
  }

  async rachatSelectOption(option: string): Promise<void> {
    await this.rachatSelect.sendKeys(option);
  }

  getRachatSelect(): ElementFinder {
    return this.rachatSelect;
  }

  async getRachatSelectedOption(): Promise<string> {
    return await this.rachatSelect.element(by.css('option:checked')).getText();
  }

  async oncSelectLastOption(): Promise<void> {
    await this.oncSelect.all(by.tagName('option')).last().click();
  }

  async oncSelectOption(option: string): Promise<void> {
    await this.oncSelect.sendKeys(option);
  }

  getOncSelect(): ElementFinder {
    return this.oncSelect;
  }

  async getOncSelectedOption(): Promise<string> {
    return await this.oncSelect.element(by.css('option:checked')).getText();
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

export class SoumissionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-soumission-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-soumission'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
