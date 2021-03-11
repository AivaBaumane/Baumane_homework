
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Baumane extends App{

  //args.foreach(println)
  val default_save_path = "src/resources/Baumane_full_book.txt"
  val real_path =if (args.size>0) args(0) else default_save_path

  def getLinesFromFile(srcPath: String) = {
    val bufferedSource = Source.fromFile(srcPath)
    val lines = bufferedSource.getLines.toArray
    bufferedSource.close
    lines
  }

  val lines=getLinesFromFile(real_path)
  println("*"*60)
  println("Beginning of Aivas Baumanes homework")
  println("*"*60)

  def getNames(lines: Array[String], startLine: Int, endLine: Int): Array[String] = {
    val inLines = lines.slice(startLine, endLine)
    val recipe = inLines.filter(line => line.toUpperCase == line &&
      (line.length > 0) && !line.contains("*") && !line.contains("]") && !line.startsWith("    "))
    recipe
  }
  val recipe = getNames(lines, 0, 3890)

  val linesToProcess = lines.slice(0,3890)

  def getIngridients(lines: Array[String], recipe: Array[String]): Map[String, Array[String]] = {
    val recipeMap = recipe.map(rec => (rec, ArrayBuffer[String]())).toMap
    var cur_recipe = ""
    for (line <- lines) {
      if (recipe.contains(line)) {
        cur_recipe = line
      }
        else if (line.startsWith("    ") && cur_recipe != "" && !line.contains(" \"") && !line.startsWith("       *")
        && (line.endsWith(",") || line.endsWith("."))) {
        val ingridients = recipeMap(cur_recipe)
        ingridients += line
      }
    }
    val results = for ((rec, titles) <- recipeMap) yield (rec, titles.toArray)
    results
  }

  def getAllTogether(lines: Array[String]): Unit = {
    val recipeTitles = getIngridients(linesToProcess, recipe)
    for ((key, value) <- recipeTitles) {
      println(s"$key")
      value.foreach(println)
      println(" ")
    }
  }

 val result = getAllTogether(lines)

  //val relative_save_path = "src/resources/Baumane_results.txt"
 //def saveLines(lines: Array[String], destPath: String, sep: String = "\n"): Unit = {
  //  val txt =result.mkString(sep)
  //  import java.io.{PrintWriter, File} //explicit import
  //  val pw = new PrintWriter(new File(destPath))
 //   pw.write(txt)
  //  pw.close()
  //}
  //saveLines(lines, relative_save_path)

}
