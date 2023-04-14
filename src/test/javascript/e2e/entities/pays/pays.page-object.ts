import { element, by, ElementFinder } from 'protractor';

export class PaysComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pays div table .btn-danger'));
  title = element.all(by.css('jhi-pays div h2#page-heading span')).first();
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

export class PaysUpdatePage {
  pageTitle = element(by.id('jhi-pays-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  codePaysInput = element(by.id('field_codePays'));
  designationFrInput = element(by.id('field_designationFr'));
  designationEnInput = element(by.id('field_designationEn'));
  designationPtInput = element(by.id('field_designationPt'));
  operateurInput = element(by.id('field_operateur'));
  dateOperationInput = element(by.id('field_dateOperation'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCodePaysInput(codePays: string): Promise<void> {
    await this.codePaysInput.sendKeys(codePays);
  }

  async getCodePaysInput(): Promise<string> {
    return await this.codePaysInput.getAttribute('value');
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

  async setDesignationPtInput(designationPt: string): Promise<void> {
    await this.designationPtInput.sendKeys(designationPt);
  }

  async getDesignationPtInput(): Promise<string> {
    return await this.designationPtInput.getAttribute('value');
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

export class PaysDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-pays-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-pays'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
