package pl.toboche.spacexlauchpadsbrowser

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class LaunchPadListTest {

    private var mockWebServer = MockWebServer().apply {
        start(8080)
        dispatcher = MockServerDispatcher()
    }

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @get:Rule(order = 2)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun showLaunchPadsList() {
        composeTestRule.onNodeWithText("Test Launch Pad")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("Test Launch Pad")
            .performClick()

        composeTestRule.onNodeWithText("ACTIVE")
            .assertIsDisplayed()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}