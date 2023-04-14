import { element, by, ElementFinder } from 'protractor';

export class DetailSoumissionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-detail-soumission div table .btn-danger'));
  title = element.all(by.css('jhi-detail-soumission div h2#page-heading span')).first();
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

export class DetailSoumissionUpdatePage {
  pageTitle = element(by.id('jhi-detail-soumission-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  montantSoumissionInput = element(by.id('field_montantSoumission'));
  tauxProposeInput = element(by.id('field_tauxPropose'));
  operateurInput = element(by.id('field_operateur'));
  dateOperationInput = element(by.id('field_dateOperation'));

  soumissionSelect = element(by.id('field_soumission'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setMontantSoumissionInput(montantSoumission: string): Promise<void> {
    await this.montantSoumissionInput.sendKeys(montantSoumission);
  }

  async getMontantSoumissionInput(): Promise<string> {
    return await this.montantSoumissionInput.getAttribute('value');
  }

  async setTauxProposeInput(tauxPropose: string): Promise<void> {
    await this.tauxProposeInput.sendKeys(tauxPropose);
  }

  async getTauxProposeInput(): Promise<string> {
    return await this.tauxProposeInput.getAttribute('value');
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

  async soumissionSelectLastOption(): Promise<void> {
    await this.soumissionSelect.all(by.tagName('option')).last().click();
  }

  async soumissionSelectOption(option: string): Promise<void> {
    await this.soumissionSelect.sendKeys(option);
  }

  getSoumissionSelect(): ElementFinder {
    return this.soumissionSelect;
  }

  async getSoumissionSelectedOption(): Promise<string> {
    return await this.soumissionSelect.element(by.css('option:checked')).getText();
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

export class DetailSoumissionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-detailSoumission-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-detailSoumission'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
