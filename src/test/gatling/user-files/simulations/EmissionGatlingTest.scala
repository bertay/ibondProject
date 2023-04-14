import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the Emission entity.
 */
class EmissionGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:8080"""

    val httpConf = http
        .baseUrl(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")
        .silentResources // Silence all resources like css or css so they don't clutter the results

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authentication = Map(
        "Content-Type" -> """application/json""",
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "${access_token}"
    )

    val scn = scenario("Test the Emission entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))
        ).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authenticate")
        .headers(headers_http_authentication)
        .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJson
        .check(header("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(2)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all emissions")
            .get("/api/emissions")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new emission")
            .post("/api/emissions")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":null
                , "codeEmission":"SAMPLE_TEXT"
                , "designationFr":"SAMPLE_TEXT"
                , "designationEn":"SAMPLE_TEXT"
                , "designationPt":"SAMPLE_TEXT"
                , "dateEmission":"2020-01-01T00:00:00.000Z"
                , "echeance":"2020-01-01T00:00:00.000Z"
                , "duree":"SAMPLE_TEXT"
                , "remboursement":"SAMPLE_TEXT"
                , "formeTitre":"SAMPLE_TEXT"
                , "tauxInteret":null
                , "volumeEmission":null
                , "uniteVolume":"Milles"
                , "valeurNominale":null
                , "devise":"FCFA"
                , "quantiteTitre":"0"
                , "rendement":"SAMPLE_TEXT"
                , "dateLimite":"2020-01-01T00:00:00.000Z"
                , "lieuSouscription":"SAMPLE_TEXT"
                , "dateResultat":"2020-01-01T00:00:00.000Z"
                , "dateReglement":"2020-01-01T00:00:00.000Z"
                , "dateValeur":"2020-01-01T00:00:00.000Z"
                , "operateur":"SAMPLE_TEXT"
                , "dateOperation":"2020-01-01T00:00:00.000Z"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_emission_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created emission")
                .get("${new_emission_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created emission")
            .delete("${new_emission_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
