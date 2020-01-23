/*
 * Copyright 2018 Arsen Ibragimov (ars)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

val ArsCo = "ru.ars-co"
val ProjectName = "scala-preconditions"

val `Scala 2.11 version` = "2.11.12"
val `Scala 2.12 version` = "2.12.10"

lazy val commonSettings = Seq(
  organization := ArsCo,
  version := "0.1.3",
  name := ProjectName,
  isSnapshot := false
)

lazy val versionSettings = Seq(
  scalaVersion := `Scala 2.11 version`,
  crossScalaVersions := Seq(
    `Scala 2.11 version`,
    `Scala 2.12 version`
  )
)

lazy val nexusPublishSettings = Seq(
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  pomIncludeRepository := { _ => false },

  publishMavenStyle := true,
  publishArtifact in Test := false
)

lazy val scmSettings = Seq(
  scmInfo := Some(
    ScmInfo(
      url(s"https://github.com/ArsCo/$ProjectName"),
      s"scm:git@github.com:ArsCo/$ProjectName.git"
    )
  )
)

lazy val developerSettings = Seq(
  developers := List(
    Developer(
      id    = "ars",
      name  = "Arsen Ibragimov",
      email = "ars@ars-co.ru",
      url   = url("https://github.com/ars-java")
    )
  )
)

lazy val projectUrls = Seq(
  homepage := Some(url(s"https://github.com/ArsCo/${ProjectName}")),
  licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))
)

lazy val coverageSettings = {
  import org.scoverage.coveralls.Imports.CoverallsKeys._
  Seq(
    coverallsTokenFile := sys.env.get("COVERALLS_TOKEN_DIRS").map(dir => s"$dir/$ProjectName.token")
  )
}

lazy val `scala-preconditions` = (project in file("."))
  .settings(
    commonSettings,
    versionSettings,

    nexusPublishSettings,
    scmSettings,
    developerSettings,

    projectUrls,
    coverageSettings,

    libraryDependencies ++= Dependencies.logging ++ Dependencies.testing
  )
