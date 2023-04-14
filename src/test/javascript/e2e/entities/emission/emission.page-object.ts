import { element, by, ElementFinder } from 'protractor';

export class EmissionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-emission div table .btn-danger'));
  title = element.all(by.css('jhi-emission div h2#page-heading span')).first();
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

export class EmissionUpdatePage {
  pageTitle = element(by.id('jhi-emission-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  codeEmissionInput = element(by.id('field_codeEmission'));
  designationFrInput = element(by.id('field_designationFr'));
  designationEnInput = element(by.id('field_designationEn'));
  designationPtInput = element(by.id('field_designationPt'));
  dateEmissionInput = element(by.id('field_dateEmission'));
  echeanceInput = element(by.id('field_echeance'));
  dureeInput = element(by.id('field_duree'));
  remboursementInput = element(by.id('field_remboursement'));
  formeTitreInput = element(by.id('field_formeTitre'));
  tauxInteretInput = element(by.id('field_tauxInteret'));
  volumeEmissionInput = element(by.id('field_volumeEmission'));
  uniteVolumeSelect = element(by.id('field_uniteVolume'));
  valeurNominaleInput = element(by.id('field_valeurNominale'));
  deviseSelect = element(by.id('field_devise'));
  quantiteTitreInput = element(by.id('field_quantiteTitre'));
  rendementInput = element(by.id('field_rendement'));
  dateLimiteInput = element(by.id('field_dateLimite'));
  lieuSouscriptionInput = element(by.id('field_lieuSouscription'));
  dateResultatInput = element(by.id('field_dateResultat'));
  dateReglementInput = element(by.id('field_dateReglement'));
  dateValeurInput = element(by.id('field_dateValeur'));
  operateurInput = element(by.id('field_operateur'));
  dateOperationInput = element(by.id('field_dateOperation'));

  avisEmissionSelect = element(by.id('field_avisEmission'));
  natureTitreSelect = element(by.id('field_natureTitre'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCodeEmissionInput(codeEmission: string): Promise<void> {
    await this.codeEmissionInput.sendKeys(codeEmission);
  }

  async getCodeEmissionInput(): Promise<string> {
    return await this.codeEmissionInput.getAttribute('value');
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

  async setEcheanceInput(echeance: string): Promise<void> {
    await this.echeanceInput.sendKeys(echeance);
  }

  async getEcheanceInput(): Promise<string> {
    return await this.echeanceInput.getAttribute('value');
  }

  async setDureeInput(duree: string): Promise<void> {
    await this.dureeInput.sendKeys(duree);
  }

  async getDureeInput(): Promise<string> {
    return await this.dureeInput.getAttribute('value');
  }

  async setRemboursementInput(remboursement: string): Promise<void> {
    await this.remboursementInput.sendKeys(remboursement);
  }

  async getRemboursementInput(): Promise<string> {
    return await this.remboursementInput.getAttribute('value');
  }

  async setFormeTitreInput(formeTitre: string): Promise<void> {
    await this.formeTitreInput.sendKeys(formeTitre);
  }

  async getFormeTitreInput(): Promise<string> {
    return await this.formeTitreInput.getAttribute('value');
  }

  async setTauxInteretInput(tauxInteret: string): Promise<void> {
    await this.tauxInteretInput.sendKeys(tauxInteret);
  }

  async getTauxInteretInput(): Promise<string> {
    return await this.tauxInteretInput.getAttribute('value');
  }

  async setVolumeEmissionInput(volumeEmission: string): Promise<void> {
    await this.volumeEmissionInput.sendKeys(volumeEmission);
  }

  async getVolumeEmissionInput(): Promise<string> {
    return await this.volumeEmissionInput.getAttribute('value');
  }

  async setUniteVolumeSelect(uniteVolume: string): Promise<void> {
    await this.uniteVolumeSelect.sendKeys(uniteVolume);
  }

  async getUniteVolumeSelect(): Promise<string> {
    return await this.uniteVolumeSelect.element(by.css('option:checked')).getText();
  }

  async uniteVolumeSelectLastOption(): Promise<void> {
    await this.uniteVolumeSelect.all(by.tagName('option')).last().click();
  }

  async setValeurNominaleInput(valeurNominale: string): Promise<void> {
    await this.valeurNominaleInput.sendKeys(valeurNominale);
  }

  async getValeurNominaleInput(): Promise<string> {
    return await this.valeurNominaleInput.getAttribute('value');
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

  async setQuantiteTitreInput(quantiteTitre: string): Promise<void> {
    await this.quantiteTitreInput.sendKeys(quantiteTitre);
  }

  async getQuantiteTitreInput(): Promise<string> {
    return await this.quantiteTitreInput.getAttribute('value');
  }

  async setRendementInput(rendement: string): Promise<void> {
    await this.rendementInput.sendKeys(rendement);
  }

  async getRendementInput(): Promise<string> {
    return await this.rendementInput.getAttribute('value');
  }

  async setDateLimiteInput(dateLimite: string): Promise<void> {
    await this.dateLimiteInput.sendKeys(dateLimite);
  }

  async getDateLimiteInput(): Promise<string> {
    return await this.dateLimiteInput.getAttribute('value');
  }

  async setLieuSouscriptionInput(lieuSouscription: string): Promise<void> {
    await this.lieuSouscriptionInput.sendKeys(lieuSouscription);
  }

  async getLieuSouscriptionInput(): Promise<string> {
    return await this.lieuSouscriptionInput.getAttribute('value');
  }

  async setDateResultatInput(dateResultat: string): Promise<void> {
    await this.dateResultatInput.sendKeys(dateResultat);
  }

  async getDateResultatInput(): Promise<string> {
    return await this.dateResultatInput.getAttribute('value');
  }

  async setDateReglementInput(dateReglement: string): Promise<void> {
    await this.dateReglementInput.sendKeys(dateReglement);
  }

  async getDateReglementInput(): Promise<string> {
    return await this.dateReglementInput.getAttribute('value');
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

  async avisEmissionSelectLastOption(): Promise<void> {
    await this.avisEmissionSelect.all(by.tagName('option')).last().click();
  }

  async avisEmissionSelectOption(option: string): Promise<void> {
    await this.avisEmissionSelect.sendKeys(option);
  }

  getAvisEmissionSelect(): ElementFinder {
    return this.avisEmissionSelect;
  }

  async getAvisEmissionSelectedOption(): Promise<string> {
    return await this.avisEmissionSelect.element(by.css('option:checked')).getText();
  }

  async natureTitreSelectLastOption(): Promise<void> {
    await this.natureTitreSelect.all(by.tagName('option')).last().click();
  }

  async natureTitreSelectOption(option: string): Promise<void> {
    await this.natureTitreSelect.sendKeys(option);
  }

  getNatureTitreSelect(): ElementFinder {
    return this.natureTitreSelect;
  }

  async getNatureTitreSelectedOption(): Promise<string> {
    return await this.natureTitreSelect.element(by.css('option:checked')).getText();
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

export class EmissionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-emission-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-emission'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
