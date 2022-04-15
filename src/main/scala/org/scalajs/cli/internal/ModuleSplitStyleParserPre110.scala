package org.scalajs.cli.internal

import org.scalajs.linker.interface.ModuleSplitStyle

// class rather than object, as that's easier to substitute from native-image
class ModuleSplitStyleParserPre110 {
  def tryParse(splitStyle: String): Option[ModuleSplitStyle] =
    if (splitStyle == ModuleSplitStyle.FewestModules.toString)
      Some(ModuleSplitStyle.FewestModules)
    else if (splitStyle == ModuleSplitStyle.SmallestModules.toString)
      Some(ModuleSplitStyle.SmallestModules)
    else
      None
  def parse(splitStyle: String, modulePackages: Seq[String]): ModuleSplitStyle =
    tryParse(splitStyle).getOrElse {
      if (splitStyle == "SmallModulesFor")
        throw new IllegalArgumentException(s"SmallModuleFor style not supported for Scala.js < 1.10")
      else
        throw new IllegalArgumentException(s"$splitStyle is not a valid module split style")
    }
}
