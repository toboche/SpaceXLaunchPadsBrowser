package pl.toboche.core_testing.mockwebserver

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.InputStreamReader

class MockServerDispatcher : Dispatcher() {

    var alwaysReturn400: Boolean = false

    override fun dispatch(request: RecordedRequest): MockResponse {
        if (alwaysReturn400) {
            return build400Error()
        }
        return when (request.path) {
            "/v3/launchpads" -> MockResponse().setResponseCode(200)
                .setBody(getJsonContent("launch_pads_list.json"))
            else -> build400Error()
        }
    }

    private fun build400Error() = MockResponse().setResponseCode(400)

    private fun getJsonContent(fileName: String): String {
        return InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(fileName)).use { it.readText() }
    }
}