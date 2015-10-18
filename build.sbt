name := "fun"

version := "1.0"

scalaVersion := "2.10.4"

sbtVersion := "0.13.6"

libraryDependencies ++= Seq("org.scalatest" % "scalatest_2.10" % "2.0" % "test")

libraryDependencies ++= Seq("org.apache.commons" % "commons-math3" % "3.5")

libraryDependencies ++= Seq("com.github.mauricio" %% "postgresql-async" % "0.2.18")


testOptions in Test += Tests.Argument("-oD")

javaOptions += "-XX:InitialHeapSize=134217728 -XX:MaxHeapSize=2147483648 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC"