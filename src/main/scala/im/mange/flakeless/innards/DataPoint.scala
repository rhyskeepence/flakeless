package im.mange.flakeless.innards

case class DataPoint(flightNumber: Int, when: Long, description: Option[String],
                     command: Option[ReportCommand], context: Option[Context])
