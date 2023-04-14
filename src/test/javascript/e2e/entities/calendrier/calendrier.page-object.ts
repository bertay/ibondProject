import { element, by, ElementFinder } from 'protractor';

export class CalendrierComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-calendrier div table .btn-danger'));
  title = element.all(by.css('jhi-calendrier div h2#page-heading span')).first();
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

export class CalendrierUpdatePage {
  pageTitle = element(by.id('jhi-calendrier-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  natureSelect = element(by.id('field_nature'));
  numeroInput = element(by.id('field_numero'));
  referenceInput = element(by.id('field_reference'));
  signataireInput = element(by.id('field_signataire'));
  titreCalendrierFrInput = element(by.id('field_titreCalendrierFr'));
  titreCalendrierEnInput = element(by.id('field_titreCalendrierEn'));
  titreCalendrierPtInput = element(by.id('field_titreCalendrierPt'));
  etatCalendrierSelect = element(by.id('field_etatCalendrier'));
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

  async setTitreCalendrierFrInput(titreCalendrierFr: string): Promise<void> {
    await this.titreCalendrierFrInput.sendKeys(titreCalendrierFr);
  }

  async getTitreCalendrierFrInput(): Promise<string> {
    return await this.titreCalendrierFrInput.getAttribute('value');
  }

  async setTitreCalendrierEnInput(titreCalendrierEn: string): Promise<void> {
    await this.titreCalendrierEnInput.sendKeys(titreCalendrierEn);
  }

  async getTitreCalendrierEnInput(): Promise<string> {
    return await this.titreCalendrierEnInput.getAttribute('value');
  }

  async setTitreCalendrierPtInput(titreCalendrierPt: string): Promise<void> {
    await this.titreCalendrierPtInput.sendKeys(titreCalendrierPt);
  }

  async getTitreCalendrierPtInput(): Promise<string> {
    return await this.titreCalendrierPtInput.getAttribute('value');
  }

  async setEtatCalendrierSelect(etatCalendrier: string): Promise<void> {
    await this.etatCalendrierSelect.sendKeys(etatCalendrier);
  }

  async getEtatCalendrierSelect(): Promise<string> {
    return await this.etatCalendrierSelect.element(by.css('option:checked')).getText();
  }

  async etatCalendrierSelectLastOption(): Promise<void> {
    await this.etatCalendrierSelect.all(by.tagName('option')).last().click();
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

export class CalendrierDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-calendrier-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-calendrier'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
