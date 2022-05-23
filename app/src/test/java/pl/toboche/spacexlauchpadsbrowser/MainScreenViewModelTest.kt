package pl.toboche.spacexlauchpadsbrowser

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.toboche.feature.TestDispatcherRule
import pl.toboche.feature.splash.TestLaunchPadsRepository

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
            Assert.assertEquals(MainScreenViewModel.MainScreenUiState.Initial, awaitItem())
            cancel()
        }
        systemUnderTest.uiState
    }
}