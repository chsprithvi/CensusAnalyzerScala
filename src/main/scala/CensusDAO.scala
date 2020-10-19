class CensusDAO {
  var state:String = _
  var population:Double = 0
  var totalArea :Double= 0
  var populationDensity :Double= 0
  var stateCode:String = _
  var housingUnits:Double = 0
  var waterArea:Double = 0
  var landArea:Double = 0
  var housingDensity:Double = 0

  def this(indiaCensus: IndiaStateCensus){
    this()
    state = indiaCensus.state
    totalArea = indiaCensus.areaInSqKm
    populationDensity = indiaCensus.densityPerSqKm
    population = indiaCensus.population
  }

  def this(stateCensusCode: StateCode){
    this()
    state = stateCensusCode.sateName
    stateCode = stateCensusCode.stateCode
  }

  def this(usCensusCSV:USCensusDTO){
    this()
    stateCode = usCensusCSV.stateId
    state = usCensusCSV.state
    totalArea = usCensusCSV.totalArea
    populationDensity = usCensusCSV.populationDensity
    population = usCensusCSV.population
    housingDensity = usCensusCSV.housingDensity
    waterArea = usCensusCSV.waterArea
    landArea = usCensusCSV.landArea
    housingUnits=usCensusCSV.housingUnits
  }
}
