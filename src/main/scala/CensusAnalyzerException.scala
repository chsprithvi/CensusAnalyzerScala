class CensusAnalyzerException(message:CustomException.Value) extends Exception(message.toString) {}

object CustomException extends Enumeration {
  type CensusAnalyserException = Value
  val wrongFilePath = Value("Incorrect File Path provided")
  val wrongFileType = Value("Incorrect File Type provided")
  val wrongDelimiter = Value("Incorrect Delimiter")
  val wrongHeaders = Value("Incorrect Headers")
}