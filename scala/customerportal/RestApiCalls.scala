package customerportal

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

object RestApiCalls {
  // Select user randomly from a CSV file
  val userFeeder = csv("./src/test/resources/data/users.csv").random

  val sortDirections = Array("DESC","ASC")
  val rangeDirections = Array("GREATER_OR_EQUAL","LESS_OR_EQUAL")
  val iccidRange = Array("8999092255470335275","8999989999999999999")

  val sortFields = Array("iccid","limit_state","fraud_state","imsi","imei","eid","msisdn","sim_state","order_id","provider_name","sim_type_name","subunit","last_update","offering_name","data_profile_name","fraud_profile_name","network_profile_name","cost_limit_profile_name","limit_profile_name","remark01","remark02","remark03","remark04","remark05","remark06","remark07","remark08","remark09","remark10")

  val simListUrl = "xxx"


  // The selected user is then used in the request header (see ${userid} variable)
  val sentHeaders = Map("Content-Type" -> "application/json",
    "Accept" -> "application/json",
    "userid" -> "${user_id}")

  def generateSearch(session: Session): String = {
    var searchString = ""
    var totalCount = session("total_count").as[Long]
    var filters = Seq("DATA_PROFILE_ID", "FRAUD_PROFILE_ID", "PROVIDER_ID", "OFFERING_ID", "LIMIT_PROFILE_ID");
    var rand = new Random()
    //add Filters with x% propability
    searchString = addSimStateFilter(searchString, 75)
    searchString = addProfileStateFilter(searchString, "FRAUD_STATE", 75)
    searchString = addProfileStateFilter(searchString, "LIMIT_STATE", 75)

    for (filter <- filters) {
      if (!session(filter).asOption[Int].equals(None)) {
        var count = session(filter+"_COUNT").as[Long]
        var value = session(filter).as[String]
        var probability = count.toFloat/totalCount*100
        if (rand.nextInt(100) < probability) {
          var filterString = Requests.searchFilter.replace("${filter}",filter).replace("${value}", value)
          searchString = searchString + filterString + ","
        }
      }
    }
    return searchString.patch(searchString.lastIndexOf(','), "", 1)
  }

  def addSimStateFilter(string: String, propability: Int): String = {
    var returnString = string
    var rand = new Random()
    if (rand.nextInt(100) < propability) {
      var filterString = Requests.searchFilter.replace("${filter}","SIM_STATE").replace("${value}", "5")
      returnString = returnString + filterString + ","
    }
    return returnString
  }

  def addProfileStateFilter(string: String, profile: String, propability: Int): String = {
    var returnString = string
    var rand = new Random()
    if (rand.nextInt(100) < propability) {
      if (rand.nextInt(100) < 75) {
        var filterString = Requests.searchFilter.replace("${filter}",profile).replace("${value}", "99")
        returnString = returnString + filterString + ","
      }
      else {
        var filterString = Requests.searchFilter.replace("${filter}",profile).replace("${value}", "0")
        returnString = returnString + filterString + ","
      }
    }
    return returnString
  }

  def searchSimList(useES: Boolean) = {
    var requestName = "Browsing SIM list"
    var useEsParam = "true"
    if (useES) {
      requestName += "ES"
    } else {
      requestName += "Oracle"
      useEsParam = "false"
    }
    feed(userFeeder)
    .exec { session => session.set("searchString", generateSearch(session)) }
    .exec { session => session.set("sortDirection", sortDirections(Random.nextInt(sortDirections.size))) }
    .exec { session => session.set("sortField", sortFields(Random.nextInt(sortFields.size))) }
    .exec(http(requestName)
                    .post(simListUrl + "?useEs=" + useEsParam)
                    .headers(sentHeaders)
                    .body(StringBody(Requests.search)).asJson
                    .check(status.is(200))
                    .check(jsonPath("$['errorCode']").isNull)
                  ).exitHereIfFailed
  }


  def searchDefault(useES: Boolean) = {
    var requestName = "Browsing SIM list"
    var useEsParam = "true"
    if (useES) {
      requestName += "ES"
    } else {
      requestName += "Oracle"
      useEsParam = "false"
    }
    feed(userFeeder)
      .exec { session => session.set("searchString", generateSearch(session)) }
      .exec { session => session.set("sortDirection", sortDirections(Random.nextInt(sortDirections.size))) }
      .exec { session => session.set("sortField", sortFields(Random.nextInt(sortFields.size))) }
      .exec(http(requestName)
        .post(simListUrl + "?useEs=" + useEsParam)
        .headers(sentHeaders)
        .body(StringBody(Requests.search1)).asJson
        .check(status.is(200))
        .check(jsonPath("$['errorCode']").isNull)
      ).exitHereIfFailed
  }

def searchRange(useES: Boolean) = {
  var requestName = "Browsing SIM list"
  var useEsParam = "true"
  if (useES) {
  requestName += "ES"
} else {
  requestName += "Oracle"
  useEsParam = "false"
}
  feed(userFeeder)
    .exec { session => session.set("searchString", generateSearch(session)) }
  .exec { session => session.set("sortDirection", sortDirections(Random.nextInt(sortDirections.size))) }
  .exec { session => session.set("sortField", sortFields(Random.nextInt(sortFields.size))) }
  .exec { session => session.set("divider", Random.between(iccidRange(0).toLong, iccidRange(1).toLong)) }
  .exec { session => session.set("rangeDirection", rangeDirections(Random.nextInt(rangeDirections.size))) }
  .exec(http(requestName)
  .post(simListUrl + "?useEs=" + useEsParam)
  .headers(sentHeaders)
  .body(StringBody(Requests.search2)).asJson
  .check(status.is(200))
  .check(jsonPath("$['errorCode']").isNull)
  ).exitHereIfFailed
}
}