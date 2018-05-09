val ProjectName = "scala-preconditions"

lazy val commonSettings = Seq(
  organization := "ru.ars-co",
  version := "0.1.0",
  name := ProjectName,
  isSnapshot := false
)

lazy val coverageSettings = {
  import org.scoverage.coveralls.Imports.CoverallsKeys._
  Seq(
    coverallsTokenFile := sys.env.get("COVERALLS_TOKEN_DIRS").map(dir => s"$dir/$ProjectName.token")
  )
}

lazy val loggingDependencies = Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "org.slf4j" % "jcl-over-slf4j" % "1.7.21"
)

lazy val testingDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.0" % Test
)


lazy val `scala-preconditions` = (project in file("."))
  .settings(
    commonSettings,
    coverageSettings,

    scalaVersion := "2.11.8",
    crossScalaVersions := Seq("2.11.8", "2.12.4"),

    libraryDependencies ++= loggingDependencies ++ testingDependencies,

    resolvers += DefaultMavenRepository,

    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value || version.value.contains("-SNAPSHOT"))
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases"  at nexus + "service/local/staging/deploy/maven2")
    },

    homepage := Some(url(s"https://github.com/ArsCo/${ProjectName}")),

    scmInfo := Some(
      ScmInfo(
        url(s"https://github.com/ArsCo/${ProjectName}"),
        s"scm:git@github.com:ArsCo/${ProjectName}.git"
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
