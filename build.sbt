import sbtrelease.ReleasePlugin

val commonSettings = Seq(
  scalacOptions += "-target:jvm-1.6",
  javacOptions ++= Seq("-source", "1.6", "-target", "1.6")
)

lazy val sslConfigCore = project.in(file("ssl-config-core"))
  .settings(commonSettings: _*)
  .settings(
    name := "ssl-config-core",
    libraryDependencies ++= Dependencies.sslConfigCore ++ Library.parserCombinators(scalaVersion.value)
  ).enablePlugins(ReleasePlugin)

lazy val sslConfigAkka = project.in(file("ssl-config-akka"))
  .dependsOn(sslConfigCore)
  .settings(commonSettings: _*)
  .settings(
    name := "ssl-config-akka",
    libraryDependencies ++= Dependencies.sslConfigAkka
  ).enablePlugins(ReleasePlugin)

lazy val sslConfigPlay = project.in(file("ssl-config-play"))
  .dependsOn(sslConfigCore)
  .settings(commonSettings: _*)
  .settings(
    name := "ssl-config-play",
    libraryDependencies ++= Seq()
  ).enablePlugins(ReleasePlugin)

lazy val root = project.in(file("."))
  .aggregate(sslConfigCore, sslConfigAkka, sslConfigPlay)
