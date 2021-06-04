const { Given, When, Then, AfterAll } = require('cucumber');
const { Builder, By, Key } = require('selenium-webdriver');
const { expect } = require('chai')


const driver = new Builder().forBrowser('chrome').build();

Given('I am on the Dashboard page', { timeout: 10000 }, async function () {
  await driver.get("http://localhost:3000/dashboard");
});

// ============
// TEMPERATURA 
// ============

When('the temperature readings area is present', async function (string) {
  await driver.sleep(3000); // wait for data to load
  const tempEsquerda = await driver.findElement(By.id('temperaturaEsquerda_div'));
  const tempDireita = await driver.findElement(By.id('temperaturaDireita_div'));

  return expect(tempEsquerda.isDisplayed()).to.be.true && expect(tempDireita.isDisplayed()).to.be.true;
  //expect(element.isPresent().to.be.false);
});

Then('the values of temperature sensors must be visible', async function () {
  const tempEsquerdaValor = await driver.findElement(By.id('temperaturaEsquerda_valor'));
  const tempDireitaValor = await driver.findElement(By.id('temperaturaDireita_valor'));

  return expect(tempEsquerdaValor.isDisplayed()).to.be.true && expect(tempDireitaValor.isDisplayed()).to.be.true;
});



// ============
// CO2 
// ============

When('the CO2 readings area is present', async function (string) {
  await driver.sleep(3000); // wait for data to load
  const co2Esquerda = await driver.findElement(By.id('co2Esquerda_div'));
  const co2Direita = await driver.findElement(By.id('co2Direita_div'));

  return expect(co2Esquerda.isDisplayed()).to.be.true && expect(co2Direita.isDisplayed()).to.be.true;
  //expect(element.isPresent().to.be.false);
});

Then('the values of CO2 sensors must be visible', async function () {
  const co2EsquerdaValor = await driver.findElement(By.id('co2Esquerda_valor'));
  const co2DireitaValor = await driver.findElement(By.id('co2Direita_valor'));

  return expect(co2EsquerdaValor.isDisplayed()).to.be.true && expect(co2DireitaValor.isDisplayed()).to.be.true;
});

// ============
// HUMIDADE 
// ============


When('the plant tray are present', async function () {
  await driver.sleep(3000); // wait for data to load
  const colunaTabuleiro = await driver.findElement(By.css(".classe_teste"));

  return expect(colunaTabuleiro.isDisplayed()).to.be.true;
});

When('its humidity reading area is present', async function (string) {
  await driver.sleep(3000); // wait for data to load
  const colunaTabuleiro = await driver.findElement(By.css(".rodape_humidade"));

  return expect(colunaTabuleiro.isDisplayed()).to.be.true;
  //expect(element.isPresent().to.be.false);
});

Then('the value of that humidity sensor must be visible', async function () {
  const leituraHumidade = await driver.findElement(By.name("humidade_valor"));

  return expect(leituraHumidade.isDisplayed()).to.be.true;
});