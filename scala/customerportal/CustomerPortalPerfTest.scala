package customerportal

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.concurrent.duration.DurationInt

class CustomerPortalPerfTest extends Simulation {

  def domain: String = Utils.getProperty("DOMAIN", "127.0.0.1:8092")

  def users: Int = Utils.getProperty("USERS", "30").toInt

  def duration: Int = Utils.getProperty("DURATION", "5").toInt

  def scn: String = Utils.getProperty("SCENARIO", "ES")

  val httpProtocol: HttpProtocolBuilder = http.baseUrl("http://" + domain)

  val allowedScenarios = List("ES", "RANGE-ES", "ORA", "RANGE-ORA", "ES-ORA")

  before {
    println("Test scenario: " + scn)
  }

  after {
    println("Stress testing customer portal REST API completed")
  }

  object Scenarios {

    def Elastic: ScenarioBuilder = scenario("Calling REST API using Elasticsearch")
      .exec(RestApiCalls.searchSimList(true))

    def RangeEs: ScenarioBuilder = scenario("Calling REST API using Elasticsearch (Range-Query)")
      .exec(RestApiCalls.searchRange(true))

    def Oracle: ScenarioBuilder = scenario("Calling REST API using Oracle")
      .exec(RestApiCalls.searchSimList(false))

    def RangeOracle: ScenarioBuilder = scenario("Calling REST API using Oracle (Range-Query)")
      .exec(RestApiCalls.searchRange(false))

    def EsOra: ScenarioBuilder = scenario("Calling REST API using Oracle and Elasticsearch")
      .randomSwitch(
        50d -> exec(RestApiCalls.searchSimList(true)),
        50d -> exec(RestApiCalls.searchSimList(false))
      )
  }

  scn match {
    case "ES" => setUp(Scenarios.Elastic.inject(constantConcurrentUsers(users).during(duration.minutes)).protocols(httpProtocol))
    case "RANGE-ES" => setUp(Scenarios.RangeEs.inject(constantConcurrentUsers(users).during(duration.minutes)).protocols(httpProtocol))
    case "ORA" => setUp(Scenarios.Oracle.inject(constantConcurrentUsers(users).during(duration.minutes)).protocols(httpProtocol))
    case "RANGE-ORA" => setUp(Scenarios.RangeOracle.inject(constantConcurrentUsers(users).during(duration.minutes)).protocols(httpProtocol))
    case "ES-ORA" => setUp(Scenarios.EsOra.inject(constantConcurrentUsers(users).during(duration.minutes)).protocols(httpProtocol))
    case _ => {
      println("Only the following test scenarios are allowed: ES, RANGE-ES, ORA, RANGE-ORA, ES-ORA")
      System.exit(1)
    }
  }

}
