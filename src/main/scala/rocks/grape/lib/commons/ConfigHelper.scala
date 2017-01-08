package rocks.grape.lib.commons

import play.api.Configuration

trait ConfigHelper {
  val config: Configuration

  /**
   * Get config string
   * @param path config path
   */
  def configString(path: String): String = config
    .getString(path)
    .getOrElse(throw new IllegalStateException(s"Config path missing: $path"))

  /**
   * Get config int
   * @param path config path
   */
  def configInt(path: String): Int = config
    .getInt(path)
    .getOrElse(throw new IllegalStateException(s"Config path missing: $path"))
}
