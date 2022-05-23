package pl.toboche.spacexlauchpadsbrowser

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.toboche.feature.TestDispatcherRule
import pl.toboche.feature.splash.TestLaunchPadsRepository
import pl.toboche.spacexlauchpadsbrowser.core.result.Result
import pl.toboche.spacexlauchpadsbrowser.core.result.Result.Loading

class MainScreenViewModelTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private lateinit var systemUnderTest: MainScreenViewModel
    private val testLaunchPadsRepository = TestLaunchPadsRepository()

    @Before
    fun setUp() {
        systemUnderTest = MainScreenViewModel(
            testLaunchPadsRepository
        )
    }

    @Test
    fun `initially show initialising screen`() = runTest {
        systemUnderTest.uiState.test {
            assertEquals(MainScreenViewModel.MainScreenUiState.Initial, awaitItem())
            cancel()
        }
        systemUnderTest.uiState
    }

    @Test
    fun `when loading show initialising screen`() = runTest {
        systemUnderTest.uiState.test {
            testLaunchPadsRepository.launchPadsFlow.value = Loading
            skipItems(1)
            assertEquals(MainScreenViewModel.MainScreenUiState.Missing, awaitItem())
            cancel()
        }
        systemUnderTest.uiState
    }

    @Test
    fun `when loaded show next screen`() = runTest {
        systemUnderTest.uiState.test {
            testLaunchPadsRepository.scheduledValue = Result.Success(emptyList())
            skipItems(1)
            assertEquals(MainScreenViewModel.MainScreenUiState.Cached, awaitItem())
            cancel()
        }
        systemUnderTest.uiState
    }

    @Test
    fun `when error loading show error`() = runTest {
        systemUnderTest.uiState.test {
            testLaunchPadsRepository.scheduledValue = Result.Error()
            skipItems(1)
            assertEquals(
                MainScreenViewModel.MainScreenUiState.Error(
                    "Error downloading",
                    "Tap retry to try one more time",
                    "Retry"
                ), awaitItem()
            )
            cancel()
        }
        systemUnderTest.uiState
    }
}