require('chromedriver');
const { Given, When, Then, AfterAll } = require('cucumber');
const { Builder, By, Key, until } = require('selenium-webdriver');
const chai = require('chai');
const expect = chai.expect;

const driver = new Builder().forBrowser('chrome').build();

Given('I am on the Dashboard page', { timeout: 10000 }, async function () {
  await driver.get("http://localhost:3000/dashboard");
});

// ============
// TEMPERATURA 
// ============

When('the temperature readings area is present', { timeout: 10000 }, async function () {
  driver.wait(until.elementIsVisible(By.id('temperaturaEsquerda_div')), 10000);
  driver.wait(until.elementIsVisible(By.id('temperaturaDireita_div')), 10000);
});

Then('the values of temperature sensors must be visible', async function () {

  driver.wait(until.elementIsVisible(By.id('temperaturaEsquerda_valor'), 10000));
  driver.wait(until.elementIsVisible(By.id('temperaturaDireita_valor'), 10000));

  const tempEsquerdaValor = driver.findElement(By.id('temperaturaEsquerda_valor'));
  const tempDireitaValor = driver.findElement(By.id('temperaturaDireita_valor'));

  expect(tempEsquerdaValor.getText()).to.not.equal("");
  expect(tempDireitaValor.getText()).to.not.equal("");
});



// ============
// CO2 
// ============

When('the CO2 readings area is present', { timeout: 10000 }, async function () {
  driver.wait(until.elementIsVisible(By.id('co2Esquerda_div'), 10000));
  driver.wait(until.elementIsVisible(By.id('co2Direita_div'), 10000));

});

Then('the values of CO2 sensors must be visible', async function () {
  driver.wait(until.elementIsVisible(By.id('co2Esquerda_valor'), 10000));
  driver.wait(until.elementIsVisible(By.id('co2Direita_valor'), 10000));

  const co2EsquerdaValor = driver.findElement(By.id('co2Esquerda_valor'));
  const co2DireitaValor = driver.findElement(By.id('co2Direita_valor'));

  expect(co2EsquerdaValor.getText()).to.not.equal("");
  expect(co2DireitaValor.getText()).to.not.equal("");

});

// ============
// HUMIDADE 
// ============


When('the plant tray are present', { timeout: 10000 }, async function () {
  driver.wait(until.elementIsVisible(By.css('classe_teste'), 10000));
  //const colunaTabuleiro = driver.findElement(By.css("classe_teste"));

  //expect(colunaTabuleiro.isDisplayed()).to.be.true;
});

When('its humidity reading area is present', async function () {
  driver.wait(until.elementIsVisible(By.css('rodape_humidade'), 10000));
  //const colunaTabuleiro = driver.findElement(By.css("rodape_humidade"));

  //expect(colunaTabuleiro.isDisplayed()).to.be.true;
});

Then('the value of that humidity sensor must be visible', async function () {
  driver.wait(until.elementIsVisible(By.name('humidade_valor'), 10000));
  //const leituraHumidade = driver.findElement(By.name("humidade_valor"));

  //expect(leituraHumidade.getText()).to.not.equal("");
});


AfterAll('end', async function () {
  await driver.quit();
});