package pl.toboche.spacexlauchpadsbrowser.feature.lauch_pad_details

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import pl.toboche.spacexlauchpadsbrowser.core.data.repository.LaunchPadsRepository
import pl.toboche.spacexlauchpadsbrowser.core.model.LaunchPad
import pl.toboche.spacexlauchpadsbrowser.core.result.Result
import pl.toboche.spacexlauchpadsbrowser.feature.lauch_pad_details.ui.LaunchPadDetailsScreenState

class LaunchPadDetailsViewModelTest {

    private lateinit var systemUnderTest: LaunchPadDetailsViewModel

    private val testId = 4L
    private val testName = "test name"
    private val testStatus = LaunchPad.Status.RETIRED
    private val testFullName = "test full name"
    private val testLocationName = "location name"
    private val testRegion = "test region"

    private val launchPads =
        MutableStateFlow<Result<List<LaunchPad>>>(
            Result.Success(
                listOf(
                    LaunchPad(
                        id = testId,
                        name = testName,
                        status = testStatus,
                        location = LaunchPad.Location(
                            name = testLocationName,
                            region = testRegion,
                            0.0,
                            0.0
                        ),
                        fullName = testFullName
                    )
                )
            )
        )
    private val testLaunchPadsRepository = mock<LaunchPadsRepository> {
        on { launchPads }.doReturn(launchPads)
    }

    @Before
    fun setUp() {
        systemUnderTest = LaunchPadDetailsViewModel(
            testLaunchPadsRepository,
            SavedStateHandle(mapOf("launchPadId" to testId))
        )
    }

    @Test
    fun `show launch pad details`() = runTest {
        val expectedLaunchPadDetails = LaunchPadDetailsScreenState.LaunchPadDetails(
            name = testName,
            status = LaunchPad.Status.RETIRED.toString().toUpperCase(),
            location = "$testLocationName, $testRegion",
            fullName = testFullName
        )

        systemUnderTest.uiState.test {
            assertEquals(
                expectedLaunchPadDetails, awaitItem()
            )
            cancel()
        }
    }
}