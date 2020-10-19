/***
 * Data Access Object class
 */
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

  /***
   * defines and loads variables wrt IndiaCensusDTO class
   * @param indiaCensusCSV reference type for IndiaCensusDTO
   */
  def this(indiaCensusCSV: IndiaCensusDTO){
    this()
    state=indiaCensusCSV.state
    totalArea=indiaCensusCSV.areaInSqKm
    populationDensity=indiaCensusCSV.densityPerSqKm
    population=indiaCensusCSV.population
  }
  /***
   * defines and loads variables wrt IndiaStateCodeCensusDTO class
   * @param indiaStateCodeCSV reference type for IndiaStateCodeDTO
   */
  def this(indiaStateCodeCSV: IndiaStateCodeDTO){
    this()
    state=indiaStateCodeCSV.stateName
    stateCode= indiaStateCodeCSV.stateCode
  }
  /***
   * defines and loads variables wrt USCensusDTO class
   * @param usCensusCSV reference type for USCensusDTO
   */
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