package pl.toboche.spacexlauchpadsbrowser.feature.launch_pads

import app.cash.turbine.test
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import pl.toboche.spacexlauchpadsbrowser.core.data.repository.LaunchPadsRepository
import pl.toboche.spacexlauchpadsbrowser.core.model.LaunchPad
import pl.toboche.spacexlauchpadsbrowser.core.result.Result
import pl.toboche.spacexlauchpadsbrowser.core.result.Result.Error

@RunWith(Parameterized::class)
class LaunchPadsListScreenViewModelTest(
    status: LaunchPad.Status,
    private val expectedStatus: String
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(LaunchPad.Status.ACTIVE, "Active"),
                arrayOf(LaunchPad.Status.UNDER_CONSTRUCTION, "Under construction"),
                arrayOf(LaunchPad.Status.RETIRED, "Retired"),
                arrayOf(LaunchPad.Status.UNKNOWN, "Unknown"),
            )
        }
    }

    private lateinit var systemUnderTest: LaunchPadsListScreenViewModel
    private val launchPads =
        MutableStateFlow<Result<List<LaunchPad>>>(
            Error()
        )
    private val testLaunchPadsRepository = mock<LaunchPadsRepository> {
        on { launchPads }.doReturn(launchPads)
    }

    val testName = "launch pad name"
    val testLaunchPad = LaunchPad(
        id = 1,
        name = testName,
        status,
        location = mock()
    )

    @Before
    fun setUp() {
        systemUnderTest = LaunchPadsListScreenViewModel(
            testLaunchPadsRepository
        )
    }

    @Test
    fun `show empty list if missing`() = runTest {
        systemUnderTest.uiState.test {
            assertEquals(emptyList<LaunchPadListItem>(), awaitItem())
            cancel()
        }
    }

    @Test
    fun `show populated list when available`() = runTest {
        systemUnderTest.uiState.test {
            launchPads.emit(Result.Success(listOf(testLaunchPad)))
            skipItems(1)

            val awaitItems = awaitItem()
            assertEquals(1, awaitItems.size)
            val (name, status) = awaitItems[0]
            assertEquals(name, testName)
            assertEquals(status, expectedStatus)
            cancel()
        }
    }
}
