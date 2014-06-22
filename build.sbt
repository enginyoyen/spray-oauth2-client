organization  := "com.enginyoyen"

version       := "0.1.0"

scalaVersion  := "2.10.3"

name    := "sprayOAuth2Client"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.0"
  val sprayV = "1.3.1"
  Seq(
    "io.spray"               %   "spray-can"     % sprayV,
    "io.spray"               %   "spray-routing" % sprayV,
    "io.spray"               %   "spray-client" % sprayV,
    "io.spray"               %   "spray-testkit" % sprayV  % "test",
    "io.spray"               %%  "spray-json"    % "1.2.5",
    "com.typesafe.akka"      %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"      %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"             %%  "specs2-core"   % "2.3.7" % "test",
    "com.typesafe"           % "config"          % "1.2.0",
    "com.google.oauth-client"   %   "google-oauth-client"           % "1.18.0-rc",
    "com.google.oauth-client"   %   "google-oauth-client-java6"     % "1.18.0-rc",
    "com.google.http-client"    %   "google-http-client-jackson2"   % "1.18.0-rc",
    "ch.qos.logback" 		% "logback-classic" 	% "1.0.0"
  )
}



lazy val m2Repo =
  Resolver.file("publish-m2-local",
    Path.userHome / ".m2" / "repository")

publishTo := Some(m2Repo)
