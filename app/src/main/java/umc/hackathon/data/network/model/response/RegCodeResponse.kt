package umc.hackathon.data.network.model.response

data class RegCodeResponse(
    val regcodes: List<RegCode>
)

data class RegCode(
    val code: String,
    val name: String
)