@file:Suppress("unused", "ClassName")

import com.omricat.gradle.dependencies.DependencyContainer
import com.omricat.gradle.dependencies.dep
import com.omricat.gradle.dependencies.versions
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven

object deps {
  object kotlin : DependencyContainer(
    group = "org.jetbrains.kotlin",
    version = versions.kotlin,
    prefix = "kotlin-"
  ) {
    val stdlib = dep("stdlib")
    val stdlibJdk8 = dep("stdlib-jdk8")
    val gradlePlugin = dep("gradle-plugin")
    val test = dep("test")
    val reflect = dep("reflect")
  }

  object di {
    const val kapsule =
      "space.traversal.kapsule:kapsule-core:${versions.di.kapsule}"

    object dagger : DependencyContainer(
      group = "com.google.dagger",
      version = versions.di.dagger
    ) {
      val runtime = dep("dagger")
      val compiler = dep("dagger-compiler")
    }
  }

  object omricat : DependencyContainer(group = "com.github.omricat") {
    val kommons = dep("kommons", version = versions.omricat.kommons)

    val agpExtensions =
      dep("agp-extensions", version = versions.omricat.agpExtensions)

    val gradleKotlinDslExtensions =
      dep(
        "gradle-kotlin-dsl-extensions",
        version = versions.omricat.gradleKotlinDslExtensions
      )

    object mvi : DependencyContainer(
      group = "${omricat.group}.mvi",
      version = versions.omricat.mvi
    ) {
      val core = dep("mvi")
      val dagger = dep("mvi-dagger")
    }
  }

  object support : DependencyContainer(
    group = "com.android.support",
    version = versions.support.library
  ) {
    val appcompat = dep("appcompat-v7")
    val design = dep("design")
    val supportv4 = dep("design")
    val recyclerView = dep("recyclerview-v7")
    val cardView = dep("cardview-v7")
    val constraintLayout = dep(
      group = "$group.constraint",
      name = "constraint-layout",
      version = versions.support.constraintLayout
    )
  }

  object rx : DependencyContainer(group = "io.reactivex.rxjava2") {
    val java2 = dep("rxjava", version = versions.rx.java2)
    val android = dep("rxandroid", version = versions.rx.android)
    val kotlin = dep("rxkotlin", version = versions.rx.kotlin)
    const val test = "com.rubylichtenstein:rxtest:${versions.rx.test}"
    const val preferences =
      "com.f2prateek.rx.preferences2:rx-preferences:${versions.rx.preferences}"
    const val relay = "com.jakewharton.rxrelay2:rxrelay:${versions.rx.relay}"
    const val replayingShare =
      "com.jakewharton.rx2:replaying-share-kotlin:${versions.rx.replayingShare}"

    object binding : DependencyContainer(
      group = "com.jakewharton.rxbinding2",
      version = versions.rx.binding,
      prefix = "rxbinding-"
    ) {

      val core = dep("rxbinding", prefix = "")
      val support = dep("support-v4")
      val appcompat = dep("appcompat-v7")
      val design = dep("design")
      val recyclerview = dep("recyclerview-v7")

      object kotlin : DependencyContainer(suffix = "-kotlin") {
        val core = dep("kotlin", suffix = "")
        val support = dep("support-v4")
        val appcompat = dep("appcompat-v7")
        val design = dep("design-kotlin")
        val recyclerview = dep("recyclerview-v7")
      }
    }
  }

  object net {
    const val okhttp = "com.squareup.okhttp3:okhttp:${versions.net.okhttp}"

    object retrofit : DependencyContainer(
      group = "com.squareup.retrofit2",
      version = versions.net.retrofit
    ) {
      val core = dep("retrofit")

      object adapters : DependencyContainer(prefix = "adapter-") {
        val rxJava2 = dep("rxjava2")
      }

      object converters : DependencyContainer(prefix = "converter-") {
        val moshi = dep("moshi")
      }
    }
  }

  object webapp {

    object ktor :
      DependencyContainer(group = "io.ktor", version = versions.webapp.ktor) {

      object server : DependencyContainer(prefix = "ktor-server-") {
        val core = dep("core")
        val jetty = dep("jetty")
        val netty = dep("netty")
        val testHost = dep("test-host")
      }

      object feature : DependencyContainer(prefix = "ktor-") {
        val auth = dep("auth")
        val gson = dep("gson")
        val jackson = dep("jackson")
      }
    }
  }

  object logging {
    const val logback =
      "ch.qos.logback:logback-classic:${versions.logging.logback}"
  }

  object json {
    object moshi : DependencyContainer(
      group = "com.squareup.moshi",
      version = versions.json.moshi
    ) {
      val core = dep("moshi")
      val kotlin = dep("moshi-kotlin")
    }
  }

  object db {
    const val exposed = "org.jetbrains.exposed:exposed:${versions.db.exposed}"
    const val pgEmbedded =
      "com.opentable.components:otj-pg-embedded:${versions.db.pgEmbedded}"
  }

  object misc {
    const val okio = "com.squareup.okio:okio:${versions.misc.okio}"
    const val timber = "com.jakewharton.timber:timber:${versions.misc.timber}"
    const val timberkt = "com.github.ajalt:timberkt:${versions.misc.timberkt}"
    const val picasso =
      "com.squareup.picasso:picasso:${versions.misc.picasso}"
    const val ktx = "androidx.core:core-ktx:${versions.misc.ktx}"
  }

  object test {
    val junit = "junit:junit:${versions.test.junit}"
    const val assertj = "org.assertj:assertj-core:${versions.test.assertj}"
    const val mockito = "org.mockito:mockito-core:${versions.test.mockito}"
  }

  object javax {
    const val inject = "javax.inject:javax.inject:${versions.javax.inject}"
  }

  object build {

    object android {
      object gradlePlugin {
        const val classpath = "com.android.tools.build:gradle:${versions.agp}"

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
