package umc.hackathon.data.network.model.response

import com.google.gson.annotations.SerializedName
import umc.hackathon.model.JobPosting

data class JobPostingResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("contract_type")
    val contractType: String,
    @SerializedName("work_hours")
    val workHours: String,
    @SerializedName("salary")
    val salary: String,
    @SerializedName("job_type")
    val jobType: String,
    @SerializedName("duties")
    val duties: String,
    @SerializedName("recruit_count")
    val recruitCount: String,
    @SerializedName("experience")
    val experience: String,
    @SerializedName("education")
    val education: String,
    @SerializedName("disability_type")
    val disabilityType: String
)

fun JobPostingResponse.toDomainModel(): JobPosting {
    return JobPosting(
        id = this.id,
        title = this.title,
        company = this.company,
        category = this.category,
        location = this.location,
        contractType = this.contractType,
        workHours = this.workHours,
        salary = this.salary,
        jobType = this.jobType,
        duties = this.duties,
        recruitCount = this.recruitCount,
        experience = this.experience,
        education = this.education,
        disabilityType = this.disabilityType
    )
}