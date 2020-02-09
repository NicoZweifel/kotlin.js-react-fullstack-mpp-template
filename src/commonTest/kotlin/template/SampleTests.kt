package template

import kotlin.test.Test
import kotlin.test.assertTrue

class SampleTests {
    @Test
    fun testMe() {
        assertTrue("JS" in hello() || "JVM" in hello())
    }
}