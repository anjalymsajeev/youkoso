package io.github.anjalymsajeev.youkoso.compose

import kotlin.test.Test
import kotlin.test.assertEquals

class ComposeModuleTest {
    @Test
    fun artifactIdMatchesModuleName() {
        assertEquals("youkoso-compose", ComposeModule.ARTIFACT_ID)
    }
}
