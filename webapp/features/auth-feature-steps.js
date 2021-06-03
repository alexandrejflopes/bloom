//const assert = require('assert');
const { Given, When, Then, AfterAll} = require('cucumber');
const { Builder, By, Key } = require('selenium-webdriver');
const { expect } = require('chai')

const driver = new Builder().forBrowser('chrome').build();

Given('I am on the Login page', { timeout: 10000 }, async function () {
  await driver.get("http://localhost:3000/");
});

When('I insert my e-mail {string}', async function (string) {
  const element = await driver.findElement(By.id('email_field'));
  element.sendKeys(string, Key.RETURN);
  await driver.sleep(1000);
});

When('I hit entrar', async function () {
    await driver.findElement(By.id("entrar_button")).click();
    await driver.get("http://localhost:3000/dashboard");
});

Then('the page title is {string}', async function (string) {
  expect(await (await driver.findElement(By.id('titulo_estufa'))).getText()).to.eql(string);
});

AfterAll('end', async function () {
    await driver.quit();
});

