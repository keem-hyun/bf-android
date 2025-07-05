package umc.hackathon.data.network.model.request

import com.google.gson.annotations.SerializedName

data class JobApplicationRequest(
    @SerializedName("jobPostQuestionId")
    val jobPostQuestionId: Int,
    @SerializedName("answer")
    val answer: String,
    @SerializedName("num")
    val num: Int
)