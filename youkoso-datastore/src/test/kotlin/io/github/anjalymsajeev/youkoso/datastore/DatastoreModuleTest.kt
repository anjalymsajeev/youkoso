package io.github.anjalymsajeev.youkoso.datastore

import kotlin.test.Test
import kotlin.test.assertEquals

class DatastoreModuleTest {
    @Test
    fun artifactIdMatchesModuleName() {
        assertEquals("youkoso-datastore", DatastoreModule.ARTIFACT_ID)
    }
}
