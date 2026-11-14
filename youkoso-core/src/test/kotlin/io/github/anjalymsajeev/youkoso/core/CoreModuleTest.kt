package io.github.anjalymsajeev.youkoso.core

import kotlin.test.Test
import kotlin.test.assertEquals

class CoreModuleTest {
    @Test
    fun artifactIdMatchesModuleName() {
        assertEquals("youkoso-core", CoreModule.ARTIFACT_ID)
    }
}
