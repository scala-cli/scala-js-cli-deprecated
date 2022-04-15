package org.scalajs.cli.internal

import org.scalajs.linker.interface.ModuleSplitStyle

// class rather than object, as that's easier to substitute from native-image
class ModuleSplitStyleParser {
  def parse(splitStyle: String, modulePackages: Array[String]): ModuleSplitStyle =
    (new ModuleSplitStyleParserPre110).tryParse(splitStyle).getOrElse {
      if (splitStyle == "SmallModulesFor") {
        if (modulePackages.isEmpty)
          throw new IllegalArgumentException(s"SmallModuleFor style must have at least one package. To define it pass `--smallModuleForPackages` parameter.")
        ModuleSplitStyle.SmallModulesFor(modulePackages.toList)
      } else
        throw new IllegalArgumentException(s"$splitStyle is not a valid module split style")
    }
}
