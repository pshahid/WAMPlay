import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "rpc"
  val appVersion      = "1.0-SNAPSHOT"

val appDependencies = Seq(
    javaCore,
    "com.blopker" %% "wamplay" % "0.0.3"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers += Resolver.url("Bo's Repository", url("http://blopker.github.com/releases/"))(Resolver.ivyStylePatterns)
  )

}