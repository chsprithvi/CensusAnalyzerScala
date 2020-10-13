import org.scalatest.FunSuite
class CensusAnalyzerTest extends FunSuite {
  val CorrectFilePath   = "./src/test/resources/IndiaStateCensusData.csv"
  val WrongFilePath  = "./src/resources/IndiaStateCensusData.csv"
  val WrongFileType  = "./src/test/resources/IndiaStateData.txt"
  val InvalidDelimiterFile = "./src/test/resources/InvalidDelimitersIndiaStateCensusData.csv"
  val InvalidHeaderFile = "./src/test/resources/InvalidHeadersIndiaStateCensusData.csv"
  val correctIndianStateFile = "./src/test/resources/IndiaStateCode.csv"
  val censusAnalyser = new CensusAnalyzer()
  val InvalidDelimiterStateFile="./src/test/resources/InvalidDelimiterIndiaStateCode.csv"
  test("given_IndianCensusCSVFile_ShouldReturnCorrectRecords") {
    assert(censusAnalyser.loadIndiaCensusData(CorrectFilePath) == 29)
  }

  test("givenIndianCensusDataCSVFile_whenWithWrongFilePath_ShouldThrowException") {
    val throws = intercept[Exception] {
      censusAnalyser.loadIndiaCensusData(WrongFilePath)
    }
    assert(throws.getMessage == CustomException.wrongFilePath.toString)
  }

  test("givenIndianCensusDataCSVFile_whenWithWrongDelimiters_shouldThrowException"){
    val throws = intercept[Exception] {
      censusAnalyser.loadIndiaCensusData(InvalidDelimiterFile)
    }
    assert(throws.getMessage == CustomException.wrongDelimiter.toString)
  }

  test("givenIndianCensusDataCSVFile_WhenWithWrongHeaders_ShouldThrowException"){
    val throws = intercept[Exception]{
      censusAnalyser.loadIndiaCensusData(InvalidHeaderFile)
    }
    assert(throws.getMessage == CustomException.wrongHeaders.toString)
  }
  //For Indian State Data . CSV
  test("givenIndiaStateCodeCSVFile_ShouldReturn_CorrectRecords") {
    assert(censusAnalyser.loadIndiaStateCode(correctIndianStateFile) == 37)
  }

  test("givenIndianDataCSVFile_whenWithWrongFileType_ShouldThrowException"){
    val throws= intercept[Exception]{
      censusAnalyser.loadIndiaStateCode(WrongFileType)
    }
    assert(throws.getMessage == CustomException.wrongFileType.toString)
  }


  test("givenIndiaStateDataCSVFile_whenWithWrongDelimiters_shouldThrowException"){
    val throws = intercept[Exception] {
      censusAnalyser.loadIndiaStateCode(InvalidDelimiterStateFile)
    }
    assert(throws.getMessage == CustomException.wrongDelimiter.toString)
  }
}