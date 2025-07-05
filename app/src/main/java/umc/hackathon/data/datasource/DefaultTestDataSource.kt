package umc.hackathon.data.datasource

import android.util.Log
import javax.inject.Inject

internal class DefaultTestDataSource @Inject constructor() : TestDataSource {
    override fun bar() {
        Log.d("DefaultTestDataSource", "bar called")
    }
}