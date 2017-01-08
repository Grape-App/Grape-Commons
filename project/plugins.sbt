// -------------------------------------------------------------------------------------------------
// The PGP Plugin
// -------------------------------------------------------------------------------------------------
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")

//  -------------------------------------------------------------------------------------------------
// Build plugins
//  -------------------------------------------------------------------------------------------------
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.8.5")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.0.6")
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.6.1")

// -------------------------------------------------------------------------------------------------
// The Testing plugins
// -------------------------------------------------------------------------------------------------
addSbtPlugin("de.johoop" % "cpd4sbt" % "1.1.5")
addSbtPlugin("com.sksamuel.scapegoat" %% "sbt-scapegoat" % "1.0.4")
addSbtPlugin("org.scalastyle" % "scalastyle-sbt-plugin" % "0.8.0")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.0")

