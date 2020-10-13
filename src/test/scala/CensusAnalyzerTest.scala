import org.scalatest.FunSuite
class CensusAnalyzerTest extends FunSuite {
  val CorrectFilePath   = "./src/test/resources/IndiaStateCensusData.csv"
  val WrongFilePath  = "./src/resources/IndiaStateCensusData.csv"
  val WrongFileType  = "./src/test/resources/IndiaStateCensusData.txt"
  val InvalidDelimiterFile = "./src/test/resources/InvalidDelimitersIndiaStateCensusData.csv"
  val InvalidHeaderFile = "./src/test/resources/InvalidHeadersIndiaStateCensusData.csv"

  test("given_IndianCensusCSVFile_ShouldReturnCorrectRecords") {
    val objCensus = new CensusAnalyzer()
    assert(objCensus.loadIndiaCensusData(CorrectFilePath) == 29)
  }

  test("givenIndianCensusDataCSVFile_whenWithWrongFilePath_ShouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyzer()
      objCensus.loadIndiaCensusData(WrongFilePath)
    }
    assert(throws.getMessage == CustomException.wrongFilePath.toString)
  }

  test("givenIndianCensusDataCSVFile_whenWithWrongFileType_ShouldThrowException"){
    val throws= intercept[Exception]{
      val objCensus = new CensusAnalyzer
      objCensus.loadIndiaCensusData(WrongFileType)
    }
    assert(throws.getMessage == CustomException.wrongFileType.toString)
  }

  test("givenIndianCensusDataCSVFile_whenWithWrongDelimiters_shouldThrowException"){
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyzer
      objCensus.loadIndiaCensusData(InvalidDelimiterFile)
    }
    assert(throws.getMessage == CustomException.wrongDelimiter.toString)
  }

  test("givenIndianCensusDataCSVFile_WhenWithWrongHeaders_ShouldThrowException"){
    val throws = intercept[Exception]{
      val objCensus = new CensusAnalyzer
      objCensus.loadIndiaCensusData(InvalidHeaderFile)
    }
    assert(throws.getMessage == CustomException.wrongHeaders.toString)
  }
}
