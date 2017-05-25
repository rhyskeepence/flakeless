package im.mange.flakeless

import im.mange.flakeless.innards.{Body, Description, WaitForElement}
import org.openqa.selenium.{By, WebDriver, WebElement}

object AssertElementEmpty {
  def apply(flakeless: Flakeless, by: By): Unit = {
    apply(Body(flakeless.rawWebDriver), by, Some(flakeless))
  }

  def apply(in: WebElement, by: By, flakeless: Option[Flakeless] = None): Unit = {
    WaitForElement(flakeless, in, by,

      description = e =>
        Description("AssertElementEmpty", in, by, expected = Some(""),
          actual = Some((e) =>
            e.getText ++ " and " ++ e.findElements(By.xpath(".//*")).size.toString ++ " children "
          )
        ).describe(e),

      condition = e => e.findElements(By.xpath(".//*")).size == 0 && e.getText.isEmpty)
  }
}
