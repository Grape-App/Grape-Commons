package rocks.grape.lib.commons

import io.swagger.annotations.ApiModelProperty
import play.api.libs.json._

import scala.annotation.meta.field

/**
 * ServiceError is the base class for errors returned as left value in a ServiceResult. It contains an error message,
 * an optional internal error message, and an optional error code.
 */
trait ServiceError {

  /**
   * The error message presented to the caller.
   */
  val error: String

  /**
   * Message containing internal information to be written to the log. Is not displayed in swagger and json results
   * currently.
   */
  @(ApiModelProperty @field)(required = false, dataType = "String", hidden = true)
  val internalMessage: Option[String] = None

  /**
   * An optional error code as int.
   */
  @(ApiModelProperty @field)(required = false, dataType = "Int", hidden = true)
  val code: Option[Int] = None
}

object ServiceError {

  /**
   * JSON serializer.
   */
  implicit val writes: Writes[ServiceError] = new Writes[ServiceError] {
    override def writes(error: ServiceError): JsValue = JsObject(Seq(
      "error" -> Json.toJson(error.error),
      "code"  -> Json.toJson(error.code)
    ).filterNot(_._2 == JsNull))
  }

  /**
   * Alternative factory to create a new ServiceError.
   */
  def apply(e: String, i: Option[String], c: Int): ServiceError = ServiceError(e, i, Some(c))

  /**
   * Alternative factory to create a new ServiceError.
   */
  def apply(e: String, i: Option[String], c: Option[Int]): ServiceError = new ServiceError {
    override val error: String = e
    override val internalMessage: Option[String] = i
    override val code: Option[Int] = c
  }

  /**
   * Alternative factory to create a new ServiceError.
   */
  def apply(e: String): ServiceError = ServiceError(e, None, None)
}
