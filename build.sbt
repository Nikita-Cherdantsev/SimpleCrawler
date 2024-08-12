lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "SimpleCrawler",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "2.13.14",
    libraryDependencies ++= Seq(
      guice,
      ws,
      "org.jsoup" % "jsoup" % "1.17.2"
    )
  )