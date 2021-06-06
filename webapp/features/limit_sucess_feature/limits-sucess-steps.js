const assert = require('assert');
const { Given, When, Then, AfterAll} = require('cucumber');
const { Builder, By, Key } = require('selenium-webdriver');
const { expect } = require('chai');

const driver = new Builder().forBrowser('chrome').build();

Given('I am on the Settings page', { timeout: 10000 }, async function () {
  await driver.get("http://localhost:3000/settings");
});

When('I insert humidity minimum limit {string}', async function (string) {
    const element = await driver.findElement(By.id('min_hum'));
    element.clear();
    element.sendKeys(string, Key.RETURN);
    await driver.sleep(1000);
});

When('I insert humidity maximum limit {string}', async function (string) {
    const element = await driver.findElement(By.id('max_hum'));
    element.clear();
    element.sendKeys(string, Key.RETURN);
    await driver.sleep(1000);
});

When('I change to temperature tab', async function () {
    await driver.findElement(By.id("temperature_tab")).click();
});

When('I insert temperature minimum limit {string}', async function (string) {
    const element = await driver.findElement(By.id('min_temp'));
    element.clear();
    element.sendKeys(string, Key.RETURN);
    await driver.sleep(1000);
});

When('I insert temperature maximum limit {string}', async function (string) {
    const element = await driver.findElement(By.id('max_temp'));
    element.clear();
    element.sendKeys(string, Key.RETURN);
    await driver.sleep(1000);
});

When('I hit Guardar', async function () {
    await driver.findElement(By.id("guardar_bt")).click();
    await driver.sleep(1000);
});

Then('I see a success dialog box saying {string}', async function (string) {
    expect(await (await driver.findElement(By.id('sucesso_alert'))).getText()).to.eql(string);
});


AfterAll('end', async function () {
    await driver.quit();
});

