ThisBuild / name := "packt-machine-learning-spark"
ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "2.12.8"


val sparkVersion = "2.4.0"

// !!! Make sure to build releases with `sbt -DsparkDependencyScope=provided clean assembly` !!!
val dependencyScope = sys.props.getOrElse("sparkDependencyScope", default = "compile")

ThisBuild / libraryDependencies ++= Seq(
  // Spark resources
  "org.apache.spark" %% "spark-core" % sparkVersion % dependencyScope,
  "org.apache.spark" %% "spark-sql" % sparkVersion % dependencyScope,
  "org.apache.spark" %% "spark-mllib" % sparkVersion % dependencyScope,
  "org.apache.spark" %% "spark-graphx" % sparkVersion % dependencyScope,
)

ThisBuild / scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-Xlint",
  "-Xlint:missing-interpolator",
  "-Ywarn-unused",
  "-Ywarn-dead-code",
  "-language:_",
  "-encoding", "UTF-8"
)


lazy val sparkui = (project in file("01-sparkui"))
  .settings(
    mainClass in Compile := Some("info.lous.packt.mlspark.exercises.SparkUIDemo")
  )