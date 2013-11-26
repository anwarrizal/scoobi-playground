package adv

import com.nicta.scoobi.Scoobi._

object WordCount2 extends ScoobiApp {

  def run() {

    if (args.length < 2) {
      sys.error("Expecting input and output path " +args.mkString(","))
    }
    
    // The input file for word counting
    val input = args(0)
    val output = args(1)

    // the file source
    val lines = fromTextFile(input)

    val counts = 
      lines.mapFlatten(line => line.split("\\s+")).
        map(word => (word, 1)).
        groupByKey. 
        combine(Reduction.Sum.int)

    val result = 
      counts.map{
        case (word, frequency) => (frequency, 1)
      }.groupByKey.
      combine(Reduction.Sum.int).
      map{
        case (frequency, fof) => frequency + "," + fof
      }

    persist(result.toTextFile(output, overwrite = true))
  }
}
