import { element, by, ElementFinder } from 'protractor';

export class ClassementComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-classement div table .btn-danger'));
  title = element.all(by.css('jhi-classement div h2#page-heading span')).first();
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

export class ClassementUpdatePage {
  pageTitle = element(by.id('jhi-classement-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  rangInput = element(by.id('field_rang'));
  observationFrInput = element(by.id('field_observationFr'));
  observationEnInput = element(by.id('field_observationEn'));
  observationPtInput = element(by.id('field_observationPt'));

  detailSoumissionSelect = element(by.id('field_detailSoumission'));
  emissionSelect = element(by.id('field_emission'));
  reouvertureSelect = element(by.id('field_reouverture'));
  rachatSelect = element(by.id('field_rachat'));
  oncSelect = element(by.id('field_onc'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setRangInput(rang: string): Promise<void> {
    await this.rangInput.sendKeys(rang);
  }

  async getRangInput(): Promise<string> {
    return await this.rangInput.getAttribute('value');
  }

  async setObservationFrInput(observationFr: string): Promise<void> {
    await this.observationFrInput.sendKeys(observationFr);
  }

  async getObservationFrInput(): Promise<string> {
    return await this.observationFrInput.getAttribute('value');
  }

  async setObservationEnInput(observationEn: string): Promise<void> {
    await this.observationEnInput.sendKeys(observationEn);
  }

  async getObservationEnInput(): Promise<string> {
    return await this.observationEnInput.getAttribute('value');
  }

  async setObservationPtInput(observationPt: string): Promise<void> {
    await this.observationPtInput.sendKeys(observationPt);
  }

  async getObservationPtInput(): Promise<string> {
    return await this.observationPtInput.getAttribute('value');
  }

  async detailSoumissionSelectLastOption(): Promise<void> {
    await this.detailSoumissionSelect.all(by.tagName('option')).last().click();
  }

  async detailSoumissionSelectOption(option: string): Promise<void> {
    await this.detailSoumissionSelect.sendKeys(option);
  }

  getDetailSoumissionSelect(): ElementFinder {
    return this.detailSoumissionSelect;
  }

  async getDetailSoumissionSelectedOption(): Promise<string> {
    return await this.detailSoumissionSelect.element(by.css('option:checked')).getText();
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

export class ClassementDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-classement-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-classement'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
