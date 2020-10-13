
import java.nio.file.{Files, NoSuchFileException, Paths}
import java.util

class CensusLoader {
  def loadData[A](csvClass: Class[A], filePaths: String*): Map[String, IndiaStateCensusDAO] = {
    try {
      var censusMap: Map[String, IndiaStateCensusDAO] = Map()
      for (filePath <- filePaths) {
        if (!filePath.endsWith(".csv")) {
          throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.InCorrectFileType)
        }
        val reader = Files.newBufferedReader(Paths.get(filePath))
        val csvBuilder = CSVBuilderFactory.createCSVBuilder()
        if(csvClass.getName == "IndiaStateCensus"){
          val censusCSVIterator: util.Iterator[IndiaStateCensus] = csvBuilder.getCSVFileIterator(reader, classOf[IndiaStateCensus])
          while (censusCSVIterator.hasNext){
            val objDAO = censusCSVIterator.next()
            censusMap += (objDAO.state -> new IndiaStateCensusDAO(objDAO))
          }
        }
        else if(csvClass.getName == "StateCode"){
          val censusCSVIterator: util.Iterator[StateCode] = csvBuilder.getCSVFileIterator(reader, classOf[StateCode])
          while (censusCSVIterator.hasNext){
            val objDAO = censusCSVIterator.next()
            censusMap += (objDAO.sateName -> new IndiaStateCensusDAO(objDAO))
          }
        }
      }
      censusMap
    }
    catch {
      case _: NoSuchFileException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.InCorrectFilePath)
      case _: CSVBuilderException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.UnableToParse)
    }
  }
}