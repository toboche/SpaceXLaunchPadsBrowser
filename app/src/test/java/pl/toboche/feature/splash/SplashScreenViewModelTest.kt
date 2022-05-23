package pl.toboche.feature.splash

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.toboche.feature.TestDispatcherRule
import pl.toboche.spacexlauchpadsbrowser.core.result.Result

@OptIn(ExperimentalCoroutinesApi::class)
class SplashScreenViewModelTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private lateinit var systemUnderTest: SplashScreenViewModel
    private val testLaunchPadsRepository = TestLaunchPadsRepository()

    @Before
    fun setUp() {
        systemUnderTest = SplashScreenViewModel(
            testLaunchPadsRepository
        )
    }

    @Test
    fun `start with loading`() = runTest {
        systemUnderTest.uiState.test {
            assertEquals(SplashScreenViewModel.SplashScreenUiState.Loading, awaitItem())
            cancel()
        }
        systemUnderTest.uiState
    }

    @Test
    fun `when download error show error`() = runTest {
        systemUnderTest.uiState.test {
            testLaunchPadsRepository.launchPadsFlow.emit(Result.Error())
            skipItems(1)
            assertEquals(
                SplashScreenViewModel.SplashScreenUiState.Error(
                    "Error loading",
                    "Retry"
                ), awaitItem()
            )
            cancel()
        }
        systemUnderTest.uiState
    }

    @Test
    fun `when download in progress show loading`() = runTest {
        systemUnderTest.uiState.test {
            testLaunchPadsRepository.launchPadsFlow.emit(Result.Loading)
            assertEquals(SplashScreenViewModel.SplashScreenUiState.Loading, awaitItem())
            cancel()
        }
        systemUnderTest.uiState
    }

    @Test
    fun `when download finished close screen`() = runTest {
        systemUnderTest.uiState.test {
            testLaunchPadsRepository.launchPadsFlow.emit(Result.Success(emptyList()))
            skipItems(1)
            assertEquals(SplashScreenViewModel.SplashScreenUiState.Finish, awaitItem())
            cancel()
        }
        systemUnderTest.uiState
    }
}