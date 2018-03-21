organization := "ru.ars-co"
version := "0.0.3-SNAPSHOT"
name := "scala-preconditions"

crossScalaVersions := Seq("2.11.8", "2.12.2")

scalaVersion := "2.11.8"

// Logging
libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "org.slf4j" % "jcl-over-slf4j" % "1.7.21",
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.7",
  "org.apache.logging.log4j" % "log4j-api" % "2.7",
  "org.apache.logging.log4j" % "log4j-core" % "2.7"
)

// Testing
libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.0.0" % "test",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

resolvers += DefaultMavenRepository

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))

pomExtra :=
  <url>https://github.com/ArsCo</url>
    <licenses>
      <license>
        <name>Apache-2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:ArsCo/scala-preconditions.git</url>
      <connection>scm:git:git@github.com:ArsCo/scala-preconditions.git</connection>
    </scm>
    <developers>
      <developer>
        <id>ars</id>
        <name>Arsen Ibragimov</name>
        <url>https://github.com/ars-java</url>
      </developer>
    </developers>

publishMavenStyle := true
publishArtifact in Test := false
