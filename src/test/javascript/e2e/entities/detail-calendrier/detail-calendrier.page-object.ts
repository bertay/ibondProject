import { element, by, ElementFinder } from 'protractor';

export class DetailCalendrierComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-detail-calendrier div table .btn-danger'));
  title = element.all(by.css('jhi-detail-calendrier div h2#page-heading span')).first();
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

export class DetailCalendrierUpdatePage {
  pageTitle = element(by.id('jhi-detail-calendrier-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  periodeInput = element(by.id('field_periode'));
  anneeInput = element(by.id('field_annee'));
  dateAnnonceInput = element(by.id('field_dateAnnonce'));
  dateAdjudicationInput = element(by.id('field_dateAdjudication'));
  dateEcheanceInput = element(by.id('field_dateEcheance'));
  dureeInput = element(by.id('field_duree'));
  volumeEmissionInput = element(by.id('field_volumeEmission'));
  uniteVolumeSelect = element(by.id('field_uniteVolume'));
  deviseSelect = element(by.id('field_devise'));
  dateValeurInput = element(by.id('field_dateValeur'));
  natureSelect = element(by.id('field_nature'));
  operateurInput = element(by.id('field_operateur'));
  dateOperationInput = element(by.id('field_dateOperation'));

  calendrierSelect = element(by.id('field_calendrier'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setPeriodeInput(periode: string): Promise<void> {
    await this.periodeInput.sendKeys(periode);
  }

  async getPeriodeInput(): Promise<string> {
    return await this.periodeInput.getAttribute('value');
  }

  async setAnneeInput(annee: string): Promise<void> {
    await this.anneeInput.sendKeys(annee);
  }

  async getAnneeInput(): Promise<string> {
    return await this.anneeInput.getAttribute('value');
  }

  async setDateAnnonceInput(dateAnnonce: string): Promise<void> {
    await this.dateAnnonceInput.sendKeys(dateAnnonce);
  }

  async getDateAnnonceInput(): Promise<string> {
    return await this.dateAnnonceInput.getAttribute('value');
  }

  async setDateAdjudicationInput(dateAdjudication: string): Promise<void> {
    await this.dateAdjudicationInput.sendKeys(dateAdjudication);
  }

  async getDateAdjudicationInput(): Promise<string> {
    return await this.dateAdjudicationInput.getAttribute('value');
  }

  async setDateEcheanceInput(dateEcheance: string): Promise<void> {
    await this.dateEcheanceInput.sendKeys(dateEcheance);
  }

  async getDateEcheanceInput(): Promise<string> {
    return await this.dateEcheanceInput.getAttribute('value');
  }

  async setDureeInput(duree: string): Promise<void> {
    await this.dureeInput.sendKeys(duree);
  }

  async getDureeInput(): Promise<string> {
    return await this.dureeInput.getAttribute('value');
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

  async setDeviseSelect(devise: string): Promise<void> {
    await this.deviseSelect.sendKeys(devise);
  }

  async getDeviseSelect(): Promise<string> {
    return await this.deviseSelect.element(by.css('option:checked')).getText();
  }

  async deviseSelectLastOption(): Promise<void> {
    await this.deviseSelect.all(by.tagName('option')).last().click();
  }

  async setDateValeurInput(dateValeur: string): Promise<void> {
    await this.dateValeurInput.sendKeys(dateValeur);
  }

  async getDateValeurInput(): Promise<string> {
    return await this.dateValeurInput.getAttribute('value');
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

  async calendrierSelectLastOption(): Promise<void> {
    await this.calendrierSelect.all(by.tagName('option')).last().click();
  }

  async calendrierSelectOption(option: string): Promise<void> {
    await this.calendrierSelect.sendKeys(option);
  }

  getCalendrierSelect(): ElementFinder {
    return this.calendrierSelect;
  }

  async getCalendrierSelectedOption(): Promise<string> {
    return await this.calendrierSelect.element(by.css('option:checked')).getText();
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

export class DetailCalendrierDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-detailCalendrier-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-detailCalendrier'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
