package com.cristian.apprecetas.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class RecipeCoroutineRule(val dispacher: TestCoroutineDispatcher = TestCoroutineDispatcher()) :
    TestWatcher(),
    TestCoroutineScope by TestCoroutineScope(dispacher) {
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispacher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        dispacher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}
