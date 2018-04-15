@file:Suppress("ClassName", "unused")

package com.omricat.gradle.dependencies

object versions {
  const val gradle = "4.6"
  const val agp = "3.1.0"
  const val kotlin = "1.2.31"
  const val buildTools = "27.0.3"
  const val compileSdk = 27
  const val minSdk = 19
  const val targetSdk = 27

  object omricat {

    const val kommons = "0.3.2"
    const val mvi = "0.1.1"
    const val agpExtensions = "0.1"
    const val gradleKotlinDslExtensions = "0.2"
  }

  object support {

    const val library = "27.0.2"
    const val constraintLayout = "1.0.2"
  }

  object di {

    const val kapsule = "0.3"
    const val dagger = "2.15"
  }

  object rx {

    const val java2 = "2.1.10"
    const val android = "2.0.1"
    const val kotlin = "2.2.0"
    const val test = "1.0.5"
    const val preferences = "2.0.0-RC3"
    const val relay = "2.0.0"
    const val binding = "2.0.0"
    const val replayingShare = "2.0.1"
  }

  object net {

    const val okhttp = "3.8.0"
    const val retrofit = "2.3.0"
  }

  object webapp {
    const val ktor = "0.9.1"
  }

  object db {
    const val exposed = "0.10.2"
    const val pgEmbedded = "0.11.4"
  }

  object logging {
    const val logback = "1.2.3"
  }

  object test {
    const val junit = "4.12"
    const val assertj = "3.7.0"
    const val mockito = "2.7.22"
  }

  object json {
    const val moshi = "1.5.0"
  }

  object misc {
    const val timber = "4.6.0"
    const val timberkt = "1.3.0"
    const val okio = "1.13.0"
    const val picasso = "2.5.2"
    const val ktx = "0.2"
  }

  object javax {
    const val inject = "1"
  }

  object build {
    object nebula {
      const val resolutionRules = "5.1.1"
    }

    const val taskTree = "1.3"
  }


}
