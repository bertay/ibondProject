import { element, by, ElementFinder } from 'protractor';

export class NatureTitreComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-nature-titre div table .btn-danger'));
  title = element.all(by.css('jhi-nature-titre div h2#page-heading span')).first();
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

export class NatureTitreUpdatePage {
  pageTitle = element(by.id('jhi-nature-titre-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  codeNatureInput = element(by.id('field_codeNature'));
  designationFrInput = element(by.id('field_designationFr'));
  designationEnInput = element(by.id('field_designationEn'));
  designationPtInput = element(by.id('field_designationPt'));
  nominalUnitaireInput = element(by.id('field_nominalUnitaire'));
  uniteValeurSelect = element(by.id('field_uniteValeur'));
  natureEcheanceSelect = element(by.id('field_natureEcheance'));
  operateurInput = element(by.id('field_operateur'));
  dateOperationInput = element(by.id('field_dateOperation'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCodeNatureInput(codeNature: string): Promise<void> {
    await this.codeNatureInput.sendKeys(codeNature);
  }

  async getCodeNatureInput(): Promise<string> {
    return await this.codeNatureInput.getAttribute('value');
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

  async setNominalUnitaireInput(nominalUnitaire: string): Promise<void> {
    await this.nominalUnitaireInput.sendKeys(nominalUnitaire);
  }

  async getNominalUnitaireInput(): Promise<string> {
    return await this.nominalUnitaireInput.getAttribute('value');
  }

  async setUniteValeurSelect(uniteValeur: string): Promise<void> {
    await this.uniteValeurSelect.sendKeys(uniteValeur);
  }

  async getUniteValeurSelect(): Promise<string> {
    return await this.uniteValeurSelect.element(by.css('option:checked')).getText();
  }

  async uniteValeurSelectLastOption(): Promise<void> {
    await this.uniteValeurSelect.all(by.tagName('option')).last().click();
  }

  async setNatureEcheanceSelect(natureEcheance: string): Promise<void> {
    await this.natureEcheanceSelect.sendKeys(natureEcheance);
  }

  async getNatureEcheanceSelect(): Promise<string> {
    return await this.natureEcheanceSelect.element(by.css('option:checked')).getText();
  }

  async natureEcheanceSelectLastOption(): Promise<void> {
    await this.natureEcheanceSelect.all(by.tagName('option')).last().click();
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

export class NatureTitreDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-natureTitre-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-natureTitre'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
