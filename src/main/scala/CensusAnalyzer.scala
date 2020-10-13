import java.io.FileNotFoundException

import scala.io.Source

class CensusAnalyzer {
  def loadIndiaCensusData(filePath: String): Int = {
    try {
      if(!filePath.endsWith(".csv")){
        throw new CensusAnalyzerException(CustomException.wrongFileType)
      }
      val fileReader = Source.fromFile(filePath)
      var countRow = 0
      for (line <- fileReader.getLines()) {
        val cols = line.split(",").map(_.trim)

        if (cols.length != 4){
          throw new CensusAnalyzerException(CustomException.wrongDelimiter)
        }

        if (countRow == 0){
          if(cols(0) != "State" || cols(1) != "Population" || cols(2) != "AreaInSqKm" || cols(3) != "DensityPerSqKm"){
            throw new CensusAnalyzerException(CustomException.wrongHeaders)
          }
        }
        countRow += 1
      }
      countRow - 1
    }
    catch {
      case _: FileNotFoundException => throw new CensusAnalyzerException(CustomException.wrongFilePath)
    }
  }
  def loadIndiaStateCode(filePath:String):Int={
    try {
      if(!filePath.endsWith(".csv")){
        throw new CensusAnalyzerException(CustomException.wrongFileType)
      }
      val fileReader = Source.fromFile(filePath)
      var countRow = 0
      for (line <- fileReader.getLines()) {
        val cols = line.split(",").map(_.trim)

        if (cols.length != 4){
          throw new CensusAnalyzerException(CustomException.wrongDelimiter)
        }

        if (countRow == 0){
          if(cols(0) != "SrNo" || cols(1) != "State Name" || cols(2) != "TIN" || cols(3) != "StateCode"){
            throw new CensusAnalyzerException(CustomException.wrongHeaders)
          }
        }
        countRow += 1
      }
      countRow - 1
    }
    catch {
      case _: FileNotFoundException => throw new CensusAnalyzerException(CustomException.wrongFilePath)
    }
  }
}
