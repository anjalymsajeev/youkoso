package io.github.anjalymsajeev.youkoso.material3

import kotlin.test.Test
import kotlin.test.assertEquals

class Material3ModuleTest {
    @Test
    fun artifactIdMatchesModuleName() {
        assertEquals("youkoso-material3", Material3Module.ARTIFACT_ID)
    }
}
