import AssemblyKeys._

assemblySettings

assemblyCacheOutput in assembly := false

assemblyCacheUnzip in assembly := true

name := "demo_adv"

version := "0.1"

scalaVersion := "2.10.1"

checksums in update := Nil

scalacOptions += "-deprecation"

libraryDependencies += "com.nicta" %% "scoobi" % "0.7.2"

libraryDependencies += "org.specs2" %% "specs2" % "2.0" % "test"

libraryDependencies += "joda-time" % "joda-time" % "2.2"

libraryDependencies += "org.joda" % "joda-convert" % "1.3.1"

resolvers ++= Seq(
    "cloudera" at "https://repository.cloudera.com/content/repositories/releases",
    "Sonatype-snapshots" at "http://oss.sonatype.org/content/repositories/snapshots")

mergeStrategy in assembly <<= (mergeStrategy in assembly) { mergeStrategy => {
    case entry => {
      val strategy = mergeStrategy(entry)
      if (strategy == MergeStrategy.deduplicate) MergeStrategy.first
      else strategy
    }
  }
}
