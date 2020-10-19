import Country.{India, USA}
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
  val USCensusCSVFilePath = "./src/test/resources/USCensusData.csv"
  val CensusObj = new CensusAnalyzer()

  test("givenIndianCensusCSVFileShouldReturnCorrectRecords") {
    assert(CensusObj.loadCensusData(India,IndiaCensusCSVFilePath) === 29)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongFilePathShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadCensusData(India,IndiaCensusWrongCSVFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectFilePath.toString)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongFileTypeShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadCensusData(India,IndiaCensusWrongCSVFileTypePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectFileType.toString)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongDelimitersShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadCensusData(India,IndiaCensusInvalidDelimiterFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.UnableToParse.toString)
  }

  test("givenIndianCensusDataCSVFileWhenWithWrongHeadersShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadCensusData(India,IndiaCensusInvalidHeaderFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.UnableToParse.toString)
  }

  test("givenIndianStateCodeCSVFileWhenWithWrongPathShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadCensusData(India,IndiaStateCodeWrongCSVFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectFilePath.toString)
  }

  test("givenIndianStateCodeCSVFileWhenWithWrongFileTypeShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadCensusData(India,IndiaStateCodeWrongCSVFileTypePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.InCorrectFileType.toString)
  }

  test("givenIndianStateCodeCSVFileWhenWithWrongDelimitersShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadCensusData(India,IndiaStateCodeInvalidCSVDelimiterFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.UnableToParse.toString)
  }

  test("givenIndianStateCodeCSVFileWhenWithWrongHeadersShouldThrowException") {
    val throws = intercept[Exception] {
      CensusObj.loadCensusData(India,IndiaStateCodeInvalidCSVHeaderFilePath)
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.UnableToParse.toString)
  }

  test("givenIndianCensusData_WhenSortedByState_ShouldReturnSortedResult"){
    CensusObj.loadCensusData(India,IndiaCensusCSVFilePath)
    val sortedCensusData = CensusObj.getStateWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state === "Andhra Pradesh")
    assert(censusCSV.last.state === "West Bengal")
  }

  test("givenIndianCensusData_WhenEmptyData_ShouldReturnException"){
    val objCensus = new CensusAnalyzer()
    val throws = intercept[Exception]{
      objCensus.getStateCodeWiseSortedCensusData
    }
    assert(throws.getMessage === CensusAnalyzerExceptionEnums.NoCensusData.toString)
  }

  test("givenIndianCensusData_WhenSortedByPopulation_ShouldReturnSortedResult"){
    CensusObj.loadCensusData(India,IndiaCensusCSVFilePath)
    val sortedCensusData = CensusObj.getPopulationWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state === "Uttar Pradesh")
    assert(censusCSV.last.state === "Sikkim")
  }


  test("givenIndianCensusData_WhenSortedByDensity_ShouldReturnSortedResult"){
    CensusObj.loadCensusData(India,IndiaCensusCSVFilePath)
    val sortedCensusData = CensusObj.getDensityWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state === "Bihar")
    assert(censusCSV.last.state === "Arunachal Pradesh")
  }

  test("givenIndianCensusData_WhenSortedByArea_ShouldReturnSortedResult"){
    CensusObj.loadCensusData(India,IndiaCensusCSVFilePath)
    val sortedCensusData = CensusObj.getAreaWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaStateCensus]])
    assert(censusCSV(0).state === "Rajasthan")
    assert(censusCSV.last.state === "Goa")
  }

  test("givenUSCensusCSVFileShouldReturnCorrectRecords"){
    assert(CensusObj.loadCensusData(USA,USCensusCSVFilePath) === 51)
  }
  test("givenUSCensusDataWhenSortedByPopulationShouldReturnSortedResult") {
    CensusObj.loadCensusData(USA,USCensusCSVFilePath)
    val sortedCensusData = CensusObj.getPopulationWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state === "California")
    assert(censusCSV.last.state === "Wyoming")
  }
  test("givenUSCensusDataWhenSortedByAreaShouldReturnSortedResult"){
    CensusObj.loadCensusData(USA,USCensusCSVFilePath)
    val sortedCensusData = CensusObj.getAreaWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state === "Alaska")
    assert(censusCSV.last.state === "District of Columbia")
  }

  test("givenUSCensusDataWhenSortedByPopulationDensityShouldReturnSortedResult"){
    CensusObj.loadCensusData(USA,USCensusCSVFilePath)
    val sortedCensusData = CensusObj.getDensityWiseSortedCensusData

    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state === "District of Columbia")
    assert(censusCSV.last.state === "Alaska")
  }

  test("givenUSCensusDataWhenSortedByStateWiseSortedShouldReturnSortedResult"){
    CensusObj.loadCensusData(USA,USCensusCSVFilePath)
    val sortedCensusData = CensusObj.getStateWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state === "Alabama")
    assert(censusCSV.last.state === "Wyoming")
  }

  test("givenUSCensusDataWhenSortedByStateCodeWiseSortedShouldReturnSortedResult"){
    CensusObj.loadCensusData(USA,USCensusCSVFilePath)
    val sortedCensusData = CensusObj.getStateCodeWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state === "Alaska")
    assert(censusCSV.last.state === "Wyoming")
  }

}
