package im.mange.flakeless.innards

import im.mange.flakeless.Flakeless
import org.openqa.selenium.{By, WebDriver, WebElement}

private [flakeless] object AssertElementDisplayedness {
  def apply(in: WebElement, by: By, expected: Boolean, flakeless: Option[Flakeless]): Unit = {
    WaitForElement(flakeless,
      Command(s"AssertElement${if (expected) "Displayed" else "Hidden"}", Some(in), Some(by)),
      description = e => Description(actual = Some((e) => if (e.isDisplayed) "displayed" else "hidden")).describeActual(e),
      condition = e => e.isDisplayed == expected)
  }
}
