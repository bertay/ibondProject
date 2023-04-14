import { element, by, ElementFinder } from 'protractor';

export class RachatComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-rachat div table .btn-danger'));
  title = element.all(by.css('jhi-rachat div h2#page-heading span')).first();
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

export class RachatUpdatePage {
  pageTitle = element(by.id('jhi-rachat-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  codeValeurInput = element(by.id('field_codeValeur'));
  designationFrInput = element(by.id('field_designationFr'));
  designationEnInput = element(by.id('field_designationEn'));
  designationPtInput = element(by.id('field_designationPt'));
  dateEmissionInput = element(by.id('field_dateEmission'));
  tauxInteretInput = element(by.id('field_tauxInteret'));
  montantNominalInput = element(by.id('field_montantNominal'));
  uniteMontantSelect = element(by.id('field_uniteMontant'));
  deviseSelect = element(by.id('field_devise'));
  dateEcheanceInput = element(by.id('field_dateEcheance'));
  dateValeurInput = element(by.id('field_dateValeur'));
  operateurInput = element(by.id('field_operateur'));
  dateOperationInput = element(by.id('field_dateOperation'));

  emissionSelect = element(by.id('field_emission'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCodeValeurInput(codeValeur: string): Promise<void> {
    await this.codeValeurInput.sendKeys(codeValeur);
  }

  async getCodeValeurInput(): Promise<string> {
    return await this.codeValeurInput.getAttribute('value');
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

  async setDateEmissionInput(dateEmission: string): Promise<void> {
    await this.dateEmissionInput.sendKeys(dateEmission);
  }

  async getDateEmissionInput(): Promise<string> {
    return await this.dateEmissionInput.getAttribute('value');
  }

  async setTauxInteretInput(tauxInteret: string): Promise<void> {
    await this.tauxInteretInput.sendKeys(tauxInteret);
  }

  async getTauxInteretInput(): Promise<string> {
    return await this.tauxInteretInput.getAttribute('value');
  }

  async setMontantNominalInput(montantNominal: string): Promise<void> {
    await this.montantNominalInput.sendKeys(montantNominal);
  }

  async getMontantNominalInput(): Promise<string> {
    return await this.montantNominalInput.getAttribute('value');
  }

  async setUniteMontantSelect(uniteMontant: string): Promise<void> {
    await this.uniteMontantSelect.sendKeys(uniteMontant);
  }

  async getUniteMontantSelect(): Promise<string> {
    return await this.uniteMontantSelect.element(by.css('option:checked')).getText();
  }

  async uniteMontantSelectLastOption(): Promise<void> {
    await this.uniteMontantSelect.all(by.tagName('option')).last().click();
  }

  async setDeviseSelect(devise: string): Promise<void> {
    await this.deviseSelect.sendKeys(devise);
  }

  async getDeviseSelect(): Promise<string> {
    return await this.deviseSelect.element(by.css('option:checked')).getText();
  }

  async deviseSelectLastOption(): Promise<void> {
    await this.deviseSelect.all(by.tagName('option')).last().click();
  }

  async setDateEcheanceInput(dateEcheance: string): Promise<void> {
    await this.dateEcheanceInput.sendKeys(dateEcheance);
  }

  async getDateEcheanceInput(): Promise<string> {
    return await this.dateEcheanceInput.getAttribute('value');
  }

  async setDateValeurInput(dateValeur: string): Promise<void> {
    await this.dateValeurInput.sendKeys(dateValeur);
  }

  async getDateValeurInput(): Promise<string> {
    return await this.dateValeurInput.getAttribute('value');
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

export class RachatDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-rachat-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-rachat'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
