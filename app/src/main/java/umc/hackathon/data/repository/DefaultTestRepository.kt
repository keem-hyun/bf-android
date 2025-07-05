package umc.hackathon.data.repository

import umc.hackathon.data.datasource.TestDataSource
import umc.hackathon.domain.TestRepository
import javax.inject.Inject

internal class DefaultTestRepository @Inject constructor(val testDataSource: TestDataSource) :
    TestRepository {
    override fun foo() {
        testDataSource.bar()
    }
}