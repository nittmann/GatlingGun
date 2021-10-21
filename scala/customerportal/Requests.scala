package customerportal

object Requests {

  var search = {
    """
      |{
      |    "count":false,
      |    "unrestricted":false,
      |    "sorting":{
      |        "sortField":"${sortField}",
      |        "sortDirection":"${sortDirection}"
      |    },
      |    "pagination":{
      |        "page":1,
      |        "pageSize":50
      |    },
      |    "columns":[${searchString}]
      |}
      |""".stripMargin
  }

  var searchFilter = {
    """
      |        {
      |        "field":"${filter}",
      |        "filterField":"${filter}",
      |        "anyFilter":false,
      |        "columnType":"LONG_VALUE",
      |        "comparisonType":"EQUAL",
      |        "value":"${value}",
      |        "relation":"AND"
      |         }
      |""".stripMargin
  }

  var search1 =
    """
      |{
      |    "count":false,
      |    "unrestricted":false,
      |    "sorting":{
      |        "sortField":"${sortField}",
      |        "sortDirection":"${sortDirection}"
      |    },
      |    "pagination":{
      |        "page":1,
      |        "pageSize":50
      |    },
      |    "columns":[]
      |}
      |""".stripMargin

  var search2 =
    """
      |{
      |    "count":false,
      |    "unrestricted":false,
      |    "pagination":{
      |        "page":1,
      |        "pageSize":50},
      |    "columns":[{
      |        "field":"ICCID",
      |        "filterField":"ICCID",
      |        "anyFilter":false,
      |        "columnType":"LONG_VALUE",
      |        "comparisonType":"${rangeDirection}",
      |        "value":"${divider}",
      |        "valueTo":null,
      |        "relation":"AND",
      |        "customFilter":null}]
      |}
      |""".stripMargin

  var search3 =
    """
      |{
      |    "count":false,
      |    "unrestricted":false,
      |    "sorting":{
      |        "sortField":"${sortField}",
      |        "sortDirection":"${sortDirection}"},
      |    "pagination":{
      |        "page":1,
      |        "pageSize":50},
      |    "columns":[{
      |        "field":"VIRTUAL",
      |        "filterField":"VIRTUAL",
      |        "anyFilter":false,
      |        "columnType":"BOOLEAN_VALUE",
      |        "comparisonType":"EQUAL",
      |        "value":"false",
      |        "valueTo":null,
      |        "relation":"AND",
      |        "customFilter":null}]
      |}
      |""".stripMargin

  var search4 =
    """
      |    "count":false,
      |    "unrestricted":false,
      |    "sorting":{
      |        "sortField":"${sortField}",
      |        "sortDirection":"${sortDirection}"},
      |    "pagination":{
      |        "page":1,
      |        "pageSize":50},
      |    "columns":[{
      |        "field":"iccid",
      |        "filterField":"ICCID",
      |        "anyFilter":false,
      |        "columnType":"STRING_VALUE",
      |        "comparisonType":"GREATER_OR_EQUAL",
      |        "value":"8900000000000000000",
      |        "valueTo":null,
      |        "relation":"AND",
      |        "customFilter":null}]
      |}
      |""".stripMargin
}