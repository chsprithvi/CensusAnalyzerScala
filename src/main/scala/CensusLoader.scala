import java.nio.file.{Files, NoSuchFileException, Paths}
import java.util

class CensusLoader {
  def loadData[A](csvClass: Class[A], filePaths: String*): Map[String, CensusDAO] = {
    try {
      var censusMap: Map[String, CensusDAO] = Map()
      for (filePath <- filePaths) {
        if (!filePath.toLowerCase.endsWith(".csv")) {
          throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.InCorrectFileType)
        }
        val reader = Files.newBufferedReader(Paths.get(filePath))
        val csvBuilder = CSVBuilderFactory.createCSVBuilder()
        if (csvClass.getName == "IndiaStateCensus") {
          val censusCSVIterator: util.Iterator[IndiaStateCensus] = csvBuilder.getCSVFileIterator(reader, classOf[IndiaStateCensus])
          censusCSVIterator.forEachRemaining { objDAO => censusMap += (objDAO.state -> new CensusDAO(objDAO)) }
        }
        else if (csvClass.getName == "StateCode") {
          val censusCSVIterator: util.Iterator[StateCode] = csvBuilder.getCSVFileIterator(reader, classOf[StateCode])
          censusCSVIterator.forEachRemaining { objDAO => censusMap += (objDAO.sateName -> new CensusDAO(objDAO)) }
        }
        else if (csvClass.getName == "USCensusDTO"){
          val censusCSVIterator: util.Iterator[USCensusDTO] = csvBuilder.getCSVFileIterator(reader,classOf[USCensusDTO])
          censusCSVIterator.forEachRemaining { objDAO => censusMap += (objDAO.state -> new CensusDAO(objDAO))}
        }
      }
      censusMap
    }
    catch {
      case _: NoSuchFileException =>
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.InCorrectFilePath)
      case _: CSVBuilderException =>
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.UnableToParse)
    }
  }
}
