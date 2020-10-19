import java.nio.file.{Files, NoSuchFileException, Paths}
import java.util

import Country.Country
/***
 * Class Loads Data by calling DAO class
 * Added CSV builder JAR in the project target
 */
class CensusLoader {
  /***
   * Function Loads Data for respective calls
   * Stores Collection Map data structure
   * Implements Iterator to Iterate the collection objects
   * @return Map key value pair type value
   */
  def loadData[A](country: Country, filePaths: Seq[String]): Map[String, CensusDAO] = {
    try {
      for (filePath <- filePaths) {
        if (!filePath.endsWith(".csv")) {
          throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.InCorrectFileType)
        }
      }
      var censusMap: Map[String, CensusDAO] = Map()
      val readerStateCensus = Files.newBufferedReader(Paths.get(filePaths.head))
      val csvBuilderStateCensus = CSVBuilderFactory.createCSVBuilder()
      if (country.equals(Country.India)) {
        val censusCSVIteratorStateCensus: util.Iterator[IndiaCensusDTO] = csvBuilderStateCensus.getCSVFileIterator(readerStateCensus, classOf[IndiaCensusDTO])
        censusCSVIteratorStateCensus.forEachRemaining { objDAO => censusMap += (objDAO.state -> new CensusDAO(objDAO)) }
      }
      else if (country.equals(Country.USA)) {
        val USCensusCSVIterator: util.Iterator[USCensusDTO] = csvBuilderStateCensus.getCSVFileIterator(readerStateCensus, classOf[USCensusDTO])
        USCensusCSVIterator.forEachRemaining { objDAO => censusMap += (objDAO.state -> new CensusDAO(objDAO)) }
      }
      else {
        throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.InvalidCountry)
      }
      if (filePaths.length == 1) {
        return censusMap
      }
      loadStateCode(censusMap, filePaths(1): String)
    }
    catch {
      case _: NoSuchFileException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.InCorrectFilePath)
      case _: CSVBuilderException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.UnableToParse)
    }
  }

  /***
   * Function Loads Data for StateCode
   * Stores Collection Map data structure
   * uses Iterator to Iterate the collection objects
   * @return Map key value pair type value
   */
  def loadStateCode(censusMap: Map[String, CensusDAO], filePath: String): Map[String, CensusDAO] = {
    try {
      val readerStateCode = Files.newBufferedReader(Paths.get(filePath))
      val CSVBuilderStateCode = CSVBuilderFactory.createCSVBuilder()
      val censusCSVIteratorStateCode: util.Iterator[IndiaStateCodeDTO] = CSVBuilderStateCode.getCSVFileIterator(readerStateCode, classOf[IndiaStateCodeDTO])
      var censusStateMap: Map[String, CensusDAO] = Map()
      censusCSVIteratorStateCode.forEachRemaining { objDAO => censusStateMap += (objDAO.stateName -> new CensusDAO(objDAO)) }

      for (stateNameCensus <- censusMap.keys; stateName <- censusStateMap.keys; if (stateName.equals(stateNameCensus))) {
        val censusData = censusMap(stateNameCensus)
        censusData.stateCode = censusStateMap(stateName).stateCode
      }
      censusMap
    }
    catch {
      case _: NoSuchFileException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.InCorrectFilePath)
      case _: CSVBuilderException => throw new CensusAnalyzerException(CensusAnalyzerExceptionEnums.UnableToParse)
    }
  }
}