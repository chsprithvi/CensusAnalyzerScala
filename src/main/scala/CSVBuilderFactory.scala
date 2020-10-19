object CSVBuilderFactory {
  def createCSVBuilder():CSVBuilderTrait = {
    new OpenCSVBuilder()
  }
}
