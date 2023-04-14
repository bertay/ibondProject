import { element, by, ElementFinder } from 'protractor';

export class SvtComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-svt div table .btn-danger'));
  title = element.all(by.css('jhi-svt div h2#page-heading span')).first();
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

export class SvtUpdatePage {
  pageTitle = element(by.id('jhi-svt-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  abreviationFrInput = element(by.id('field_abreviationFr'));
  abreviationEnInput = element(by.id('field_abreviationEn'));
  abreviationPtInput = element(by.id('field_abreviationPt'));
  designationFrInput = element(by.id('field_designationFr'));
  designationEnInput = element(by.id('field_designationEn'));
  etatSelect = element(by.id('field_etat'));
  operateurInput = element(by.id('field_operateur'));
  dateOperationInput = element(by.id('field_dateOperation'));

  paysSelect = element(by.id('field_pays'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setAbreviationFrInput(abreviationFr: string): Promise<void> {
    await this.abreviationFrInput.sendKeys(abreviationFr);
  }

  async getAbreviationFrInput(): Promise<string> {
    return await this.abreviationFrInput.getAttribute('value');
  }

  async setAbreviationEnInput(abreviationEn: string): Promise<void> {
    await this.abreviationEnInput.sendKeys(abreviationEn);
  }

  async getAbreviationEnInput(): Promise<string> {
    return await this.abreviationEnInput.getAttribute('value');
  }

  async setAbreviationPtInput(abreviationPt: string): Promise<void> {
    await this.abreviationPtInput.sendKeys(abreviationPt);
  }

  async getAbreviationPtInput(): Promise<string> {
    return await this.abreviationPtInput.getAttribute('value');
  }

  async setDesignationFrInput(designationFr: string): Promise<void> {
    await this.designationFrInput.sendKeys(designationFr);
  }

  async getDesignationFrInput(): Promise<string> {
    return await this.designationFrInput.getAttribute('value');
  }

  async setDesignationEnInput(designationEn: string): Promise<void> {
    await this.designationEnInput.sendKeys(designationEn);
  }

  async getDesignationEnInput(): Promise<string> {
    return await this.designationEnInput.getAttribute('value');
  }

  async setEtatSelect(etat: string): Promise<void> {
    await this.etatSelect.sendKeys(etat);
  }

  async getEtatSelect(): Promise<string> {
    return await this.etatSelect.element(by.css('option:checked')).getText();
  }

  async etatSelectLastOption(): Promise<void> {
    await this.etatSelect.all(by.tagName('option')).last().click();
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

  async paysSelectLastOption(): Promise<void> {
    await this.paysSelect.all(by.tagName('option')).last().click();
  }

  async paysSelectOption(option: string): Promise<void> {
    await this.paysSelect.sendKeys(option);
  }

  getPaysSelect(): ElementFinder {
    return this.paysSelect;
  }

  async getPaysSelectedOption(): Promise<string> {
    return await this.paysSelect.element(by.css('option:checked')).getText();
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

export class SvtDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-svt-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-svt'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
