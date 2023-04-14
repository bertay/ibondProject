import { element, by, ElementFinder } from 'protractor';

export class AvisEmissionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-avis-emission div table .btn-danger'));
  title = element.all(by.css('jhi-avis-emission div h2#page-heading span')).first();
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

export class AvisEmissionUpdatePage {
  pageTitle = element(by.id('jhi-avis-emission-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  natureSelect = element(by.id('field_nature'));
  numeroInput = element(by.id('field_numero'));
  referenceInput = element(by.id('field_reference'));
  signataireInput = element(by.id('field_signataire'));
  objetAvisFrInput = element(by.id('field_objetAvisFr'));
  objetAvisEnInput = element(by.id('field_objetAvisEn'));
  objetAvisPtInput = element(by.id('field_objetAvisPt'));
  etatAvisSelect = element(by.id('field_etatAvis'));
  operateurInput = element(by.id('field_operateur'));
  dateOperationInput = element(by.id('field_dateOperation'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNatureSelect(nature: string): Promise<void> {
    await this.natureSelect.sendKeys(nature);
  }

  async getNatureSelect(): Promise<string> {
    return await this.natureSelect.element(by.css('option:checked')).getText();
  }

  async natureSelectLastOption(): Promise<void> {
    await this.natureSelect.all(by.tagName('option')).last().click();
  }

  async setNumeroInput(numero: string): Promise<void> {
    await this.numeroInput.sendKeys(numero);
  }

  async getNumeroInput(): Promise<string> {
    return await this.numeroInput.getAttribute('value');
  }

  async setReferenceInput(reference: string): Promise<void> {
    await this.referenceInput.sendKeys(reference);
  }

  async getReferenceInput(): Promise<string> {
    return await this.referenceInput.getAttribute('value');
  }

  async setSignataireInput(signataire: string): Promise<void> {
    await this.signataireInput.sendKeys(signataire);
  }

  async getSignataireInput(): Promise<string> {
    return await this.signataireInput.getAttribute('value');
  }

  async setObjetAvisFrInput(objetAvisFr: string): Promise<void> {
    await this.objetAvisFrInput.sendKeys(objetAvisFr);
  }

  async getObjetAvisFrInput(): Promise<string> {
    return await this.objetAvisFrInput.getAttribute('value');
  }

  async setObjetAvisEnInput(objetAvisEn: string): Promise<void> {
    await this.objetAvisEnInput.sendKeys(objetAvisEn);
  }

  async getObjetAvisEnInput(): Promise<string> {
    return await this.objetAvisEnInput.getAttribute('value');
  }

  async setObjetAvisPtInput(objetAvisPt: string): Promise<void> {
    await this.objetAvisPtInput.sendKeys(objetAvisPt);
  }

  async getObjetAvisPtInput(): Promise<string> {
    return await this.objetAvisPtInput.getAttribute('value');
  }

  async setEtatAvisSelect(etatAvis: string): Promise<void> {
    await this.etatAvisSelect.sendKeys(etatAvis);
  }

  async getEtatAvisSelect(): Promise<string> {
    return await this.etatAvisSelect.element(by.css('option:checked')).getText();
  }

  async etatAvisSelectLastOption(): Promise<void> {
    await this.etatAvisSelect.all(by.tagName('option')).last().click();
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

export class AvisEmissionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-avisEmission-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-avisEmission'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
