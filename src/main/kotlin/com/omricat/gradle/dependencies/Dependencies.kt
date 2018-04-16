@file:Suppress("unused", "ClassName")

import com.omricat.gradle.dependencies.DependencyGrouping
import com.omricat.gradle.dependencies.dependency
import com.omricat.gradle.dependencies.versions
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven

object deps : DependencyGrouping() {
  object kotlin : DependencyGrouping(
    group = "org.jetbrains.kotlin",
    version = versions.kotlin,
    prefix = "kotlin-"
  ) {
    val stdlib = dependency("stdlib")
    val stdlibJdk8 = dependency("stdlib-jdk8")
    val gradlePlugin = dependency("gradle-plugin")
    val test = dependency("test")
    val reflect = dependency("reflect")
  }

  object di {
    val kapsule =
      dependency(
        group = "space.traversal.kapsule",
        name = "kapsule-core",
        version = versions.di.kapsule
      )

    object dagger : DependencyGrouping(
      group = "com.google.dagger",
      version = versions.di.dagger
    ) {
      val runtime = dependency("dagger")
      val compiler = dependency("dagger-compiler")
    }
  }

  object omricat : DependencyGrouping(group = "com.github.omricat") {
    val kommons = dependency("kommons", version = versions.omricat.kommons)

    val agpExtensions =
      dependency("agp-extensions", version = versions.omricat.agpExtensions)

    val gradleKotlinDslExtensions =
      dependency(
        "gradle-kotlin-dsl-extensions",
        version = versions.omricat.gradleKotlinDslExtensions
      )

    object mvi : DependencyGrouping(
      group = "${omricat.group}.mvi",
      version = versions.omricat.mvi
    ) {
      val core = dependency("mvi")
      val dagger = dependency("mvi-dagger")
    }
  }

  object support : DependencyGrouping(
    group = "com.android.support",
    version = versions.support.library
  ) {
    val appcompat = dependency("appcompat-v7")
    val design = dependency("design")
    val supportv4 = dependency("design")
    val recyclerView = dependency("recyclerview-v7")
    val cardView = dependency("cardview-v7")
    val constraintLayout = dependency(
      group = "$group.constraint",
      name = "constraint-layout",
      version = versions.support.constraintLayout
    )
  }

  object rx : DependencyGrouping(group = "io.reactivex.rxjava2") {
    val java2 = dependency("rxjava", version = versions.rx.java2)
    val android = dependency("rxandroid", version = versions.rx.android)
    val kotlin = dependency("rxkotlin", version = versions.rx.kotlin)
    val test = dependency(
      group = "com.rubylichtenstein",
      name = "rxtest",
      version = versions.rx.test
    )
    val preferences = dependency(
      group = "com.f2prateek.rx.preferences2",
      name = "rx-preferences",
      version = versions.rx.preferences
    )
    val relay = dependency(
      group = "com.jakewharton.rxrelay2",
      name = "rxrelay",
      version = versions.rx.relay
    )
    val replayingShare = dependency(
      group = "com.jakewharton.rx2",
      name = "replaying-share-kotlin",
      version = versions.rx.replayingShare
    )

    object binding : DependencyGrouping(
      group = "com.jakewharton.rxbinding2",
      version = versions.rx.binding,
      prefix = "rxbinding-"
    ) {

      val core = dependency("rxbinding", prefix = "")
      val support = dependency("support-v4")
      val appcompat = dependency("appcompat-v7")
      val design = dependency("design")
      val recyclerview = dependency("recyclerview-v7")

      object kotlin : DependencyGrouping(suffix = "-kotlin") {
        val core = dependency("kotlin", suffix = "")
        val support = dependency("support-v4")
        val appcompat = dependency("appcompat-v7")
        val design = dependency("design-kotlin")
        val recyclerview = dependency("recyclerview-v7")
      }
    }
  }

  object net {
    val okhttp = dependency(
      group = "com.squareup.okhttp3",
      name = "okhttp",
      version = versions.net.okhttp
    )

    object retrofit : DependencyGrouping(
      group = "com.squareup.retrofit2",
      version = versions.net.retrofit
    ) {
      val core = dependency("retrofit")

      object adapters : DependencyGrouping(prefix = "adapter-") {
        val rxJava2 = dependency("rxjava2")
      }

      object converters : DependencyGrouping(prefix = "converter-") {
        val moshi = dependency("moshi")
      }
    }
  }

  object webapp {

    object ktor :
      DependencyGrouping(group = "io.ktor", version = versions.webapp.ktor) {

      object server : DependencyGrouping(prefix = "ktor-server-") {
        val core = dependency("core")
        val jetty = dependency("jetty")
        val netty = dependency("netty")
        val testHost = dependency("test-host")
      }

      object feature : DependencyGrouping(prefix = "ktor-") {
        val auth = dependency("auth")
        val gson = dependency("gson")
        val jackson = dependency("jackson")
      }
    }
  }

  object logging {
    val logback = dependency(
      group = "ch.qos.logback",
      name = "logback-classic",
      version = versions.logging.logback
    )
  }

  object json {
    object moshi : DependencyGrouping(
      group = "com.squareup.moshi",
      version = versions.json.moshi
    ) {
      val core = dependency("moshi")
      val kotlin = dependency("moshi-kotlin")
    }
  }

  object db {
    val exposed = dependency(
      group = "org.jetbrains.exposed",
      name = "exposed",
      version = versions.db.exposed
    )
    val pgEmbedded = dependency(
      group = "com.opentable.components",
      name = "otj-pg-embedded",
      version = versions.db.pgEmbedded
    )
  }

  object misc {
    val okio = dependency(
      group = "com.squareup.okio",
      name = "okio",
      version = versions.misc.okio
    )
    val timber = dependency(
      group = "com.jakewharton.timber",
      name = "timber",
      version = versions.misc.timber
    )
    val timberkt = dependency(
      group = "com.github.ajalt",
      name = "timberkt",
      version = versions.misc.timberkt
    )
    val picasso = dependency(
      group = "com.squareup.picasso",
      name = "picasso",
      version = versions.misc.picasso
    )
    val ktx = dependency(
      group = "androidx.core",
      name = "core-ktx",
      version = versions.misc.ktx
    )
  }

  object test {
    val junit =
      dependency(group = "junit", name = "junit", version = versions.test.junit)
    val assertj = dependency(
      group = "org.assertj",
      name = "assertj-core",
      version = versions.test.assertj
    )
    val mockito = dependency(
      group = "org.mockito",
      name = "mockito-core",
      version = versions.test.mockito
    )
  }

  object javax {
    val inject = dependency(
      group = "javax.inject",
      name = "javax.inject",
      version = versions.javax.inject
    )
  }

  object build {

    object android {
      object gradlePlugin {
        val classpath = dependency(
          group = "com.android.tools.build",
          name = "gradle",
          version = versions.agp
        )

        object id {
          const val application = "com.android.application"
          const val library = "com.android.library"
        }
      }
    }

    object nebula {
      const val resolutionRules = "nebula.resolution-rules"
    }

    const val taskTree = "com.dorongold.task-tree"
  }
}

object repositories {
  fun RepositoryHandler.jitpack() = maven("https://jitpack.io")
  fun RepositoryHandler.exposed() =
    maven("https://dl.bintray.com/kotlin/exposed")

  fun RepositoryHandler.ktor() = maven("https://dl.bintray.com/kotlin/ktor")
}
