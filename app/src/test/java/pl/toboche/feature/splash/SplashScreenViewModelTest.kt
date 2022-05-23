package pl.toboche.feature.splash

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.toboche.feature.TestDispatcherRule

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
            Assert.assertEquals(SplashScreenViewModel.SplashScreenUiState.Loading, awaitItem())
            cancel()
        }
        systemUnderTest.uiState
    }
}