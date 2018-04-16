package com.omricat.gradle.dependencies

import com.omricat.gradle.dependencies.DependencyGroupingTest.deps.test.nested.nested2.dep
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.shouldBe

@Suppress("DEPRECATION")
class DependencyGroupingTest {

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
    val containingObjects: List<Any> =
      TestObject.Nested1.Nested2.Nested3.containingObjects()

    kotlin.test.assert(containingObjects) {
      shouldBe(
        listOf(
          TestObject,
          TestObject.Nested1,
          TestObject.Nested1.Nested2
        )
      )

    }

    kotlin.test.assert(containingObjects.filterIsInstance<Marker>()) {
      shouldBe(
        listOf(
          TestObject.Nested1,
          TestObject.Nested1.Nested2
        )
      )
    }
  }

  object deps {

    object test : DependencyGrouping(group = "group") {

      object nested :
        DependencyGrouping(prefix = "prefix-", suffix = "-suffix") {
        object nested2 :
          DependencyGrouping(prefix = "prefix2-", suffix = "-suffix2") {
          val dep = dependency("name", "1.0.0")
        }
      }
    }
  }

  @Test
  fun `dependency() works`() {

    val expected = Dependency(
      group = "group",
      name = "prefix-prefix2-name-suffix2-suffix",
      version = "1.0.0"
    )
    assertEquals(expected, dep)
  }

}
