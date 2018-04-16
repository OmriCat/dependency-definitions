package com.omricat.gradle.dependencies

import kotlin.reflect.KClass
import org.gradle.api.artifacts.Dependency as GradleDependency

abstract class DependencyGrouping(
  val group: String? = null,
  val prefix: String? = null,
  val suffix: String? = null,
  val version: String? = null
)

data class Dependency(
  internal val name: String = "",
  internal val group: String? = null,
  internal val version: String? = null,
  internal val prefix: String = "",
  internal val suffix: String = "",
  private val reason: String? = null
) : GradleDependency {
  operator fun invoke(version: String): Dependency = overrideVersion(version)

  operator fun invoke(configure: OverrideContext.(Dependency) -> Unit): Dependency =
      override(configure)

  fun overrideVersion(version: String): Dependency = override { this.version = version }

  fun override(configure: (OverrideContext).(Dependency) -> Unit) =
    OverrideContext()
      .apply{ configure(this@Dependency) }
      .let { copy(
        group = it.group ?: this.group,
        name = it.name ?: this.name,
        version = it.version ?: this.version
      ) }

  override fun getGroup(): String? = group

  override fun getName(): String = """$prefix$name$suffix"""

  override fun getVersion(): String? = version

  override fun contentEquals(dependency: GradleDependency): Boolean =
    getName() == dependency.name
        && group == dependency.group
        && version == dependency.version

  override fun copy(): GradleDependency = copy()

  override fun because(reason: String?) {
    TODO("not implemented")
  }

  override fun getReason(): String? = reason

  class OverrideContext(
    var group: String? = null,
    var name: String? = null,
    var version: String? = null
  )
}

fun DependencyGrouping.dependency(
  name: String,
  version: String? = null,
  group: String? = null,
  prefix: String? = null,
  suffix: String? = null
): Dependency =
  defineDep(name).let {
    Dependency(
      group = group ?: it.group,
      name = "${prefix ?: it.prefix}${it.name}${suffix ?: it.suffix}",
      version = version ?: it.version
    )
  }

private fun DependencyGrouping.defineDep(name: String): Dependency =
  containingObjects().plus(this)
    .filterIsInstance<DependencyGrouping>()
    .let { objs: List<DependencyGrouping> ->
      objs.fold(Dependency()) { dep, container ->
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

internal fun Any.containingObjects(): List<Any> {
  val containingObjects = mutableListOf<Any>()
  var parent = this::class.enclosingObjectInstance
  while (parent != null) {
    containingObjects.add(parent)
    parent = parent::class.enclosingObjectInstance
  }
  return containingObjects.toList().reversed()
}

private val KClass<*>.enclosingObjectInstance get() = java.enclosingClass.kotlin.objectInstance
