package rocks.grape.lib.commons

import cats.data.EitherT

import scala.concurrent.Future

object ServiceResult {

  type ServiceResult[T] = EitherT[Future, ServiceError, T]

  /**
   * Create ServiceResult from Future containing Either.
   */
  def apply[T](result: Future[Either[ServiceError, T]]): ServiceResult[T] = EitherT[Future, ServiceError, T](result)

  /**
   * Create ServiceResult from ServiceError.
   */
  def apply[T](error: ServiceError): ServiceResult[T] = EitherT[Future, ServiceError, T](Future.successful(Left(error)))

  /**
   * Create ServiceResult from T.
   */
  def apply[T](result: T): ServiceResult[T] = EitherT[Future, ServiceError, T](Future.successful(Right(result)))

  /**
   * Create ServiceResult from Either.
   */
  def apply[T](result: Either[ServiceError, T]): ServiceResult[T] =
    EitherT[Future, ServiceError, T](Future.successful(result))
}
