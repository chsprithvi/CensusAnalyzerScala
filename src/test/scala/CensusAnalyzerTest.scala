import com.google.gson.Gson
import org.scalatest.FunSuite

class CensusAnalyzerTest extends FunSuite {

  val IndiaCensusCSVFilePath = "./src/test/resources/IndiaStateCensusData.csv"
  val IndiaCensusWrongCSVFilePath = "./resources/IndiaStateCensusData.csv"
  val IndiaCensusWrongCSVFileTypePath = "./src/test/resources/IndiaStateCensusData.txt"
  val IndiaCensusInvalidDelimiterFilePath = "./src/test/resources/InvalidDelimitersIndiaStateCensusData.csv"
  val IndiaCensusInvalidHeaderFilePath = "./src/test/resources/InvalidHeadersIndiaStateCensusData.csv"
  val IndiaStateCodeCSVFilePath = "./src/test/resources/IndiaStateCode.csv"
  val IndiaStateCodeWrongCSVFilePath = "./src/resources/IndiaStateCode.csv"
  val IndiaStateCodeWrongCSVFileTypePath = "./src/test/resources/IndiaStateCode.txt"
  val IndiaStateCodeInvalidCSVDelimiterFilePath = "./src/test/resources/InvalidDelimitersIndiaStateCode.csv"
  val IndiaStateCodeInvalidCSVHeaderFilePath = "./src/test/resources/InvalidHeadersIndiaStateCode.csv"
  val CensusObj = new CensusAnalyzer()

  test("givenIndianCensusCSVFileShouldReturnCorrectRecords") {

    assert(CensusObj.loadIndiaCensusData(IndiaCensusCSVFilePath) === 29)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongFilePathShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaCensusData(IndiaCensusWrongCSVFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectFilePath.toString)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongFileTypeShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaCensusData(IndiaCensusWrongCSVFileTypePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectFileType.toString)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongDelimitersShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaCensusData(IndiaCensusInvalidDelimiterFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.UnableToParse.toString)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongHeadersShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaCensusData(IndiaCensusInvalidHeaderFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.UnableToParse.toString)
  }

  test("givenIndiaStateCodeCSVFileShouldReturnCorrectRecords") {
    assert(CensusObj.loadIndiaStateCode(IndiaStateCodeCSVFilePath) == 37)
  }

  test("givenIndianStateCodeCSVFileWhenWithWrongPathShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaStateCode(IndiaStateCodeWrongCSVFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectFilePath.toString)
  }

  test("givenIndianStateCodeCSVFileWhenWithWrongFileTypeShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaStateCode(IndiaStateCodeWrongCSVFileTypePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectFileType.toString)
  }

  test("givenIndianStateCodeCSVFileWhenWithWrongDelimitersShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaStateCode(IndiaStateCodeInvalidCSVDelimiterFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.UnableToParse.toString)
  }

  test("givenIndianStateCodeCSVFileWhenWithWrongHeadersShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadIndiaStateCode(IndiaStateCodeInvalidCSVHeaderFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.UnableToParse.toString)
  }

  test("givenIndianCensusData_WhenSortedByState_ShouldReturnSortedResult"){
    CensusObj.loadIndiaCensusData(IndiaCensusCSVFilePath)
    val sortedCensusData = CensusObj.getStateWiseSortedCensusData()
    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state === "Andhra Pradesh")
    assert(censusCSV.last.state === "West Bengal")
  }

  test("givenIndianCensusData_WhenEmptyData_ShouldReturnException"){
    val objCensus = new CensusAnalyzer()
    val throws = intercept[Exception]{
      objCensus.getStateCodeWiseSortedCensusData()
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.NoCensusData.toString)
  }

  test("givenIndianCensusData_WhenSortedByPopulation_ShouldReturnSortedResult"){
    CensusObj.loadIndiaCensusData(IndiaCensusCSVFilePath)
    val sortedCensusData = CensusObj.getPopulationWiseSortedCensusData()
    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state === "Uttar Pradesh")
    assert(censusCSV.last.state === "Sikkim")
  }


  test("givenIndianCensusData_WhenSortedByDensity_ShouldReturnSortedResult"){
    CensusObj.loadIndiaCensusData(IndiaCensusCSVFilePath)
    val sortedCensusData = CensusObj.getDensityWiseSortedCensusData()
    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state === "Bihar")
    assert(censusCSV.last.state === "Arunachal Pradesh")
  }

  test("givenIndianCensusData_WhenSortedByArea_ShouldReturnSortedResult"){
    CensusObj.loadIndiaCensusData(IndiaCensusCSVFilePath)
    val sortedCensusData = CensusObj.getAreaWiseSortedCensusData()
    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state === "Rajasthan")
    assert(censusCSV.last.state === "Goa")
  }
}
