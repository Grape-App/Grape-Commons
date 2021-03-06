// -------------------------------------------------------------------------------------------------
// CONSTANTS
// -------------------------------------------------------------------------------------------------
val ORGANIZATION_NAME = "Grape"
val ORGANIZATION_PACKAGE = "rocks.grape"
val ORGANIZATION_URI = url("http://grape-app.com")
val PROJECT_NAME = "Grape-Commons"
val PROJECT_START_YEAR = 2017

git.useGitDescribe := true
enablePlugins(GitVersioning)

// -------------------------------------------------------------------------------------------------
// PACKAGE CONFIGURATION
// -------------------------------------------------------------------------------------------------
name := PROJECT_NAME
organization := ORGANIZATION_PACKAGE
organizationHomepage := Some(ORGANIZATION_URI)
organizationName := ORGANIZATION_NAME
startYear := Some(PROJECT_START_YEAR)
licenses := Seq("MIT" -> url("https://opensource.org/licenses/MIT"))
homepage := Some(url("http://grape-app.com"))

developers := List(
  Developer("1e0n", "Leon Kunert", "1e0n@grape.rocks", ORGANIZATION_URI)
)

scmInfo := Some(ScmInfo(
  url("https://github.com/Grape-App/Grape-Commons.git"),
  "scm:git:git@github.com:Grape-App/Grape-Commons.git"
))

// -------------------------------------------------------------------------------------------------
// SCALA COMPILER SETTINGS
// -------------------------------------------------------------------------------------------------
scalacOptions in Compile ++= Seq("-deprecation", "-feature", "-unchecked")
scalaVersion := "2.11.8"
testOptions in Test += Tests.Argument("-oF") // print stack-traces in tests

// -------------------------------------------------------------------------------------------------
// LIBRARY DEPENDENCIES
// -------------------------------------------------------------------------------------------------
libraryDependencies ++= Seq(
  "org.typelevel" %% "cats" % "0.9.0",
  "com.typesafe.play" %% "play" % "2.5.12",
  "io.swagger" % "swagger-annotations" % "1.5.12",

  "ch.qos.logback" % "logback-classic" % "1.1.9" % "test",
  "org.mockito" % "mockito-core" % "1.10.19" % "test",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % "test"
)


// -------------------------------------------------------------------------------------------------
// PUBLISHER
// -------------------------------------------------------------------------------------------------
useGpg := true
pgpReadOnly := false

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) {
    Some("snapshots" at nexus + "content/repositories/snapshots")
  } else {
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
  }
}
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }


// -------------------------------------------------------------------------------------------------
// Scapegoat Configuration (static code analysis)
//   - scapegoat will be executed during "sbt compile"
// -------------------------------------------------------------------------------------------------
scapegoatVersion := "1.3.0"
scapegoatConsoleOutput := true
scapegoatIgnoredFiles := Seq.empty


// -------------------------------------------------------------------------------------------------
// Scoverage Configuration (code coverage)
//   - enable coverage report with "sbt coverage"
//   - in order to run the test suite with coverage enabled use "sbt coverage test"
// -------------------------------------------------------------------------------------------------
coverageFailOnMinimum := true
coverageHighlighting := true
coverageMinimum := 100
coverageExcludedPackages :=
  """..*Module.*;"""


// -------------------------------------------------------------------------------------------------
// Scalastyle Configuration (check style)
//   - use "sbt scalastyle" to check against check style errors
// -------------------------------------------------------------------------------------------------
scalastyleConfig := file("project/scalastyle_config.xml")
scalastyleFailOnError := true


// -------------------------------------------------------------------------------------------------
// Scaladoc Configuration (api doc)
//   - use "sbt doc" to generate the API documentation
// -------------------------------------------------------------------------------------------------
scalacOptions in (Compile, doc) ++= Seq(
  "-author",
  "-diagrams",
  "-doc-title", "Grape - Commons"
)
