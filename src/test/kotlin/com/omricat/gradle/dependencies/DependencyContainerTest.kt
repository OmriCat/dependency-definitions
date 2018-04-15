package com.omricat.gradle.dependencies

import com.omricat.gradle.dependencies.DependencyContainerTest.deps.test.nested.nested2.dep
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.containsAll
import kotlin.test.sizeShouldBe

@Suppress("DEPRECATION")
class DependencyContainerTest {

  interface Marker

  object TestObject {
    object Nested1 : Marker {
      object Nested2 : Marker {
        object Nested3 : Marker
      }
    }
  }

  @Test
  fun `containingObjects works`() {
    val containingObjects: List<Marker> =
      TestObject.Nested1.Nested2.Nested3.containingObjects<Marker>()

    kotlin.test.assert(containingObjects) {
      containsAll(TestObject.Nested1, TestObject.Nested1.Nested2)
      sizeShouldBe(2)
    }
  }

  object deps {

    object test : DependencyContainer(group = "group") {

      object nested : DependencyContainer(prefix = "prefix-", suffix = "-suffix") {
        object nested2 :
          DependencyContainer(prefix = "prefix2-", suffix = "-suffix2") {
          val dep = dep("name", "1.0.0")
        }
      }
    }
  }

  @Test
  fun `dep works`() {

    assertEquals("group:prefix-prefix2-name-suffix2-suffix:1.0.0", dep)
  }

}
