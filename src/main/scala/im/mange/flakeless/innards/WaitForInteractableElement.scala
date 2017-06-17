package im.mange.flakeless.innards

import im.mange.flakeless.Flakeless
import org.openqa.selenium.{By, WebDriver, WebElement}

object WaitForInteractableElement {
  def apply(flakeless: Option[Flakeless], command: Command,
            description: (WebElement) => String,
            condition: (WebElement) => Boolean = (e) => {true},
            action: (WebElement) => Unit,
            mustBeDisplayed: Boolean = true) {

    Execute(flakeless, new WaitForInteractableElement(command, description, condition, action, mustBeDisplayed))
  }
}

private class WaitForInteractableElement(val command: Command,
                                         description: (WebElement) => String,
                                         condition: (WebElement) => Boolean = (e) => {true},
                                         action: (WebElement) => Unit,
                                         mustBeDisplayed: Boolean = true) extends Executable {

  def execute(context: Context) {
    (command.in, command.by) match {
      case (Some(in), Some(by)) =>
        //TODO: we should ensure there is only one element - make configurable
        Wait.waitUpTo().forCondition(command, {
          val e = in.findElement(by)
          val result = (if (mustBeDisplayed) e.isDisplayed else true) && e.isEnabled && condition(e)
          val value = description(in.findElement(by))
          if (result) context.succeeded()
          else context.failed(value)
          result
        },
          description(in.findElement(by)),
          action(in.findElement(by))
        )
      case _ => throw new RuntimeException("cannot wait without in and by")
    }
  }
}




