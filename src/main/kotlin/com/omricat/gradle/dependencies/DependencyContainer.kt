package com.omricat.gradle.dependencies

import kotlin.reflect.KClass

abstract class DependencyContainer(
  val group: String? = null,
  val prefix: String? = null,
  val suffix: String? = null,
  val version: String? = null
)

data class Dep(
  val group: String? = null,
  val name: String = "",
  val version: String? = null,
  val prefix: String = "",
  val suffix: String = ""

)

fun DependencyContainer.dep(
  name: String,
  version: String? = null,
  group: String? = null,
  prefix: String? = null,
  suffix: String? = null
) =
  defineDep(name).let {
    "${group ?: it.group}" +
        ":${prefix ?: it.prefix}${it.name}${suffix ?: it.suffix}" +
        ":${version ?: it.version}"
  }

private fun DependencyContainer.defineDep(name: String): Dep =
  (containingObjects().plus(this)).let { objs: List<DependencyContainer> ->
    objs.fold(Dep()) { dep, container ->
      val group: String? = container.group ?: dep.group
      val version: String? = container.version ?: dep.version
      dep.copy(group = group, version = version)
    }.let {
      val prefix =
        objs.joinToString(separator = "") { it.prefix.orEmpty() }
      val suffix =
        objs.reversed().joinToString(separator = "") { it.suffix.orEmpty() }
      it.copy(name = name, prefix = prefix, suffix = suffix)
    }
  }

private fun <T> T?.override(overrideValue: T?): T? =
  overrideValue ?: this


inline fun <reified T : Any> T.containingObjects(): List<T> {
  val containingObjects = mutableListOf<T>()
  var parent: T? = this::class.enclosingObjectInstance as? T
  while (parent != null) {
    containingObjects.add(parent)
    parent = parent::class.enclosingObjectInstance as? T
  }
  return containingObjects.toList().reversed()
}

val KClass<*>.enclosingObjectInstance get() = java.enclosingClass.kotlin.objectInstance
