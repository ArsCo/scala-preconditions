import sbt.Keys.{developers, libraryDependencies, scmInfo}
import sbt.url

lazy val commonSettings = Seq(
  organization := "ru.ars-co",
  version := "0.0.3", // + "-SNAPSHOT"
  name := "scala-preconditions"
)

lazy val loggingDependencies = Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "org.slf4j" % "jcl-over-slf4j" % "1.7.21",
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.7",
  "org.apache.logging.log4j" % "log4j-api" % "2.7",
  "org.apache.logging.log4j" % "log4j-core" % "2.7"
)

lazy val testingDependencies = Seq(
  "org.scalactic" %% "scalactic" % "3.0.0" % Test,
  "org.scalatest" %% "scalatest" % "3.0.0" % Test
)


lazy val root = (project in file("."))
  .settings(
    commonSettings,

    scalaVersion := "2.11.8",
    crossScalaVersions := Seq("2.11.8", "2.12.4"),
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),

    libraryDependencies ++= loggingDependencies ++ testingDependencies,

    resolvers += DefaultMavenRepository,

    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases"  at nexus + "service/local/staging/deploy/maven2")
    },

    homepage := Some(url("https://github.com/ArsCo/scala-preconditions")),

    scmInfo := Some(
      ScmInfo(
        url("https://github.com/your-account/your-project"),
        "scm:git@github.com:ArsCo/scala-preconditions.git"
      )
    ),

    developers := List(
      Developer(
        id    = "ars",
        name  = "Arsen Ibragimov",
        email = "ars@ars-co.ru",
        url   = url("https://github.com/ars-java")
      )
    ),

    licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html")),

    pomIncludeRepository := { _ => false },

    publishMavenStyle := true,
    publishArtifact in Test := false
  )

