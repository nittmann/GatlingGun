package customerportal

object FilterTypes {


  val filters = Map(
    "provider_id" -> "LONG_VALUE",
    "fraud_profile_id" -> "LONG_VALUE",
    "data_profile_id" -> "LONG_VALUE",
    "limit_profile_id" -> "LONG_VALUE",



  )
val LIMIT_STATE = Seq("limit_state", "LONG_VALUE", true, (0, 99, 99, 99))
val FRAUD_STATE = Seq("fraud_state", "LONG_VALUE", true, (0, 99, 99, 99))
val SIM_STATE = Seq("sim_state", "LONG_VALUE", true, (5))

val SIM_TYPE = Seq("sim_type", "LONG_VALUE", false)
val SIM_TYPE_NAME = Seq("sim_type_name", "STRING_VALUE", true)
val SUBUNIT_ID = Seq("subunit_id", "LONG_VALUE", false)
val SUBUNIT = Seq("subunit", "STRING_VALUE", true)
val IP_ADDRESS = Seq("ip_address", "STRING_VALUE", false)
val VIRTUAL = Seq("virtual", "BOOLEAN_VALUE", false, (true, false))
val SCHEDULED_CHANGE = Seq("scheduled_change", "STRING_VALUE", false)
val SCHEDULED_CHANGE_DATE = Seq("scheduled_change_date", "DATE_VALUE", false)
val LAST_UPDATE = Seq("last_update", "DATE_VALUE", true)
val OFFERING_ID = Seq("offering_id", "LONG_VALUE", false)
val OFFERING_NAME = Seq("offering_name", "STRING_VALUE", true)
val DATA_PROFILE_ID = Seq("data_profile_id", "LONG_VALUE", false)
val DATA_PROFILE_NAME = Seq("data_profile_name", "STRING_VALUE", true)
val FRAUD_PROFILE_ID = Seq("fraud_profile_id", "LONG_VALUE", false)
val FRAUD_PROFILE_NAME = Seq("fraud_profile_name", "STRING_VALUE", true)
val BARRING_PROFILE_ID = Seq("network_profile_id", "LONG_VALUE", false)
val BARRING_PROFILE_NAME = Seq("network_profile_name", "STRING_VALUE", true)
val COSTLIMIT_PROFILE_ID = Seq("cost_limit_profile_id", "LONG_VALUE", false)
val COSTLIMIT_PROFILE_NAME = Seq("cost_limit_profile_name", "STRING_VALUE", true)
val LIMIT_PROFILE_ID = Seq("limit_profile_id", "LONG_VALUE", false)
val LIMIT_PROFILE_NAME = Seq("limit_profile_name", "STRING_VALUE", true)
val OPTION_IDS = Seq("option_ids", "STRING_VALUE", false)
val REMARK01 = Seq("remark01", "STRING_VALUE", true)
val REMARK02 = Seq("remark02", "STRING_VALUE", true)
val REMARK03 = Seq("remark03", "STRING_VALUE", true)
val REMARK04 = Seq("remark04", "STRING_VALUE", true)
val REMARK05 = Seq("remark05", "STRING_VALUE", true)
val REMARK06 = Seq("remark06", "STRING_VALUE", true)
val REMARK07 = Seq("remark07", "STRING_VALUE", true)
val REMARK08 = Seq("remark08", "STRING_VALUE", true)
val REMARK09 = Seq("remark09", "STRING_VALUE", true)
val REMARK10 = Seq("remark10", "STRING_VALUE", true)
}
