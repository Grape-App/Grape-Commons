package rocks.grape.lib.commons

import ch.qos.logback.classic.LoggerContext
import org.scalatest.BeforeAndAfterEach
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mock.MockitoSugar
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatestplus.play.PlaySpec
import org.slf4j.LoggerFactory
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.ExecutionContext
import scala.util.Try

class TestSpec extends PlaySpec with MockitoSugar with ScalaFutures with BeforeAndAfterEach {

  /**
   * The timeout to wait for the future before declaring it as failed.
   * Note: Seconds
   */
  protected val futurePatienceTimeout: Int = 5

  /**
   * The interval for the checking whether the future has completed
   * Note: Milliseconds
   */
  protected val futurePatienceInterval: Int = 20

  /**
   * The execution context used in unit tests.
   */
  implicit val defaultExecutionContext: ExecutionContext = defaultContext

  /**
   * To prevent tests to fail because of future timeout issues, we override the default behavior.
   * Note: it is an implicit Option for the ScalaFutures method 'whenReady'
   */
  implicit val defaultPatience: PatienceConfig = PatienceConfig(
    timeout = Span(futurePatienceTimeout, Seconds),
    interval = Span(futurePatienceInterval, Millis)
  )

  /**
   * @inheritdoc
   */
  override def beforeEach(): Unit = {
    try super.beforeEach()
    finally {
      // shutdown logger
      Try(LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]).map { ctx =>
        ctx.stop()
        org.slf4j.bridge.SLF4JBridgeHandler.uninstall()
      }
    }
  }
}
