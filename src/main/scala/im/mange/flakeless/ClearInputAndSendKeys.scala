package im.mange.flakeless

import im.mange.flakeless.innards.{Body, Description, WaitForInteractableElement}
import org.openqa.selenium.{By, WebDriver, WebElement}

object ClearInputAndSendKeys {
  def apply(webDriver: WebDriver, by: By, keysToSend: CharSequence*): Unit = {
    apply(Body(webDriver), by, keysToSend: _*)
  }

  def apply(in: WebElement, by: By, keysToSend: CharSequence*): Unit = {
    WaitForInteractableElement(in, by,

      description = e => Description("ClearInputAndSendKeys", in, by, args = Map("keysToSend" -> keysToSend.toString())).describe(e),

      action = e => {
        e.clear()
        e.sendKeys(keysToSend: _*)
      }

    )
  }
}
