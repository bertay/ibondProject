import { element, by, ElementFinder } from 'protractor';

export class ResultatComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-resultat div table .btn-danger'));
  title = element.all(by.css('jhi-resultat div h2#page-heading span')).first();
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

export class ResultatUpdatePage {
  pageTitle = element(by.id('jhi-resultat-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nbreSvtTotalInput = element(by.id('field_nbreSvtTotal'));
  nbreSvtSoumisInput = element(by.id('field_nbreSvtSoumis'));
  montantTresorInput = element(by.id('field_montantTresor'));
  uniteTresorSelect = element(by.id('field_uniteTresor'));
  montantSoumisInput = element(by.id('field_montantSoumis'));
  uniteSoumisSelect = element(by.id('field_uniteSoumis'));
  montantServiInput = element(by.id('field_montantServi'));
  nbreTitreTotalInput = element(by.id('field_nbreTitreTotal'));
  nbreTitreSoumisInput = element(by.id('field_nbreTitreSoumis'));
  tauxMinProposeInput = element(by.id('field_tauxMinPropose'));
  tauxMaxProposeInput = element(by.id('field_tauxMaxPropose'));
  tauxLimiteInput = element(by.id('field_tauxLimite'));
  tauxInteretMoyenInput = element(by.id('field_tauxInteretMoyen'));
  tauxRendementMoyenInput = element(by.id('field_tauxRendementMoyen'));
  tauxCouvertureInput = element(by.id('field_tauxCouverture'));

  emissionSelect = element(by.id('field_emission'));
  resultatSelect = element(by.id('field_resultat'));
  rachatSelect = element(by.id('field_rachat'));
  oncSelect = element(by.id('field_onc'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNbreSvtTotalInput(nbreSvtTotal: string): Promise<void> {
    await this.nbreSvtTotalInput.sendKeys(nbreSvtTotal);
  }

  async getNbreSvtTotalInput(): Promise<string> {
    return await this.nbreSvtTotalInput.getAttribute('value');
  }

  async setNbreSvtSoumisInput(nbreSvtSoumis: string): Promise<void> {
    await this.nbreSvtSoumisInput.sendKeys(nbreSvtSoumis);
  }

  async getNbreSvtSoumisInput(): Promise<string> {
    return await this.nbreSvtSoumisInput.getAttribute('value');
  }

  async setMontantTresorInput(montantTresor: string): Promise<void> {
    await this.montantTresorInput.sendKeys(montantTresor);
  }

  async getMontantTresorInput(): Promise<string> {
    return await this.montantTresorInput.getAttribute('value');
  }

  async setUniteTresorSelect(uniteTresor: string): Promise<void> {
    await this.uniteTresorSelect.sendKeys(uniteTresor);
  }

  async getUniteTresorSelect(): Promise<string> {
    return await this.uniteTresorSelect.element(by.css('option:checked')).getText();
  }

  async uniteTresorSelectLastOption(): Promise<void> {
    await this.uniteTresorSelect.all(by.tagName('option')).last().click();
  }

  async setMontantSoumisInput(montantSoumis: string): Promise<void> {
    await this.montantSoumisInput.sendKeys(montantSoumis);
  }

  async getMontantSoumisInput(): Promise<string> {
    return await this.montantSoumisInput.getAttribute('value');
  }

  async setUniteSoumisSelect(uniteSoumis: string): Promise<void> {
    await this.uniteSoumisSelect.sendKeys(uniteSoumis);
  }

  async getUniteSoumisSelect(): Promise<string> {
    return await this.uniteSoumisSelect.element(by.css('option:checked')).getText();
  }

  async uniteSoumisSelectLastOption(): Promise<void> {
    await this.uniteSoumisSelect.all(by.tagName('option')).last().click();
  }

  async setMontantServiInput(montantServi: string): Promise<void> {
    await this.montantServiInput.sendKeys(montantServi);
  }

  async getMontantServiInput(): Promise<string> {
    return await this.montantServiInput.getAttribute('value');
  }

  async setNbreTitreTotalInput(nbreTitreTotal: string): Promise<void> {
    await this.nbreTitreTotalInput.sendKeys(nbreTitreTotal);
  }

  async getNbreTitreTotalInput(): Promise<string> {
    return await this.nbreTitreTotalInput.getAttribute('value');
  }

  async setNbreTitreSoumisInput(nbreTitreSoumis: string): Promise<void> {
    await this.nbreTitreSoumisInput.sendKeys(nbreTitreSoumis);
  }

  async getNbreTitreSoumisInput(): Promise<string> {
    return await this.nbreTitreSoumisInput.getAttribute('value');
  }

  async setTauxMinProposeInput(tauxMinPropose: string): Promise<void> {
    await this.tauxMinProposeInput.sendKeys(tauxMinPropose);
  }

  async getTauxMinProposeInput(): Promise<string> {
    return await this.tauxMinProposeInput.getAttribute('value');
  }

  async setTauxMaxProposeInput(tauxMaxPropose: string): Promise<void> {
    await this.tauxMaxProposeInput.sendKeys(tauxMaxPropose);
  }

  async getTauxMaxProposeInput(): Promise<string> {
    return await this.tauxMaxProposeInput.getAttribute('value');
  }

  async setTauxLimiteInput(tauxLimite: string): Promise<void> {
    await this.tauxLimiteInput.sendKeys(tauxLimite);
  }

  async getTauxLimiteInput(): Promise<string> {
    return await this.tauxLimiteInput.getAttribute('value');
  }

  async setTauxInteretMoyenInput(tauxInteretMoyen: string): Promise<void> {
    await this.tauxInteretMoyenInput.sendKeys(tauxInteretMoyen);
  }

  async getTauxInteretMoyenInput(): Promise<string> {
    return await this.tauxInteretMoyenInput.getAttribute('value');
  }

  async setTauxRendementMoyenInput(tauxRendementMoyen: string): Promise<void> {
    await this.tauxRendementMoyenInput.sendKeys(tauxRendementMoyen);
  }

  async getTauxRendementMoyenInput(): Promise<string> {
    return await this.tauxRendementMoyenInput.getAttribute('value');
  }

  async setTauxCouvertureInput(tauxCouverture: string): Promise<void> {
    await this.tauxCouvertureInput.sendKeys(tauxCouverture);
  }

  async getTauxCouvertureInput(): Promise<string> {
    return await this.tauxCouvertureInput.getAttribute('value');
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

  async resultatSelectLastOption(): Promise<void> {
    await this.resultatSelect.all(by.tagName('option')).last().click();
  }

  async resultatSelectOption(option: string): Promise<void> {
    await this.resultatSelect.sendKeys(option);
  }

  getResultatSelect(): ElementFinder {
    return this.resultatSelect;
  }

  async getResultatSelectedOption(): Promise<string> {
    return await this.resultatSelect.element(by.css('option:checked')).getText();
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

export class ResultatDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-resultat-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-resultat'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
