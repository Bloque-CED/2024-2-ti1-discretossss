ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test

lazy val root = (project in file("."))
  .settings(
    name := "project"
  )
