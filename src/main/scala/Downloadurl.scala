import ReadingFile1.and_text

object Downloadurl extends App{
 val url="https://www.gutenberg.org/ebooks/25880.txt.utf-8"

  import scala.io.Source
  val txtBuffer= Source.fromURL(url)
  val lines=txtBuffer.getLines.toArray
  println(txtBuffer.size)

  lines.slice(0,20).foreach(println)
  val txt=lines.mkString("\n")

 val relative_save_path = "src/resources/poem_1990.txt"

def saveLines(lines: Array[String], destPath: String, sep: String = "\n"): Unit = {
 val txt=lines.mkString(sep)
 import java.io.{PrintWriter, File} //explicit import
 val pw = new PrintWriter(new File(destPath))
 pw.write(txt)
 pw.close()
}
 saveLines(lines, relative_save_path)
}
