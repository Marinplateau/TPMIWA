name := "tpmiwa"

version := "1.0"

lazy val `tpmiwa` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq( javaJdbc , javaEbean , cache , javaWs )

libraryDependencies += "com.mashape.unirest" % "unirest-java" % "1.4.7"

libraryDependencies += "commons-net" % "commons-net" % "3.3"

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  