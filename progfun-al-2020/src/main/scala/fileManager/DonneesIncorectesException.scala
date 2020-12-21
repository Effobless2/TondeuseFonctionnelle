package fileManager

final case class DonneesIncorectesException(private val cause: Throwable = None.orNull) extends Exception("", cause)
