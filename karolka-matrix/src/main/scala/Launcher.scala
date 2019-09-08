import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

import breeze.linalg.DenseMatrix
import breeze.linalg._
import breeze.numerics._

import scala.io.Source

object Launcher {

  def checkInput(input: List[List[Int]]) = {
    val size = input.head.size
    require(input.forall(_.size == size))
  }

  def main(args: Array[String]): Unit = {
    try {
      val input = loadMatrix(args(0))
      val param = loadMatrix(args(1))
      println(s"Input $input")
      println(s"Input $param")
      val outputMatrix: DenseMatrix[Int] = input * param
      println(printMatrix(outputMatrix))
      Files.write(Paths.get(args(2)), printMatrix(outputMatrix).getBytes(StandardCharsets.UTF_8))
    } catch{
      case e: Exception => Files.write(Paths.get(args(2)), "Invalid input matrix.".getBytes(StandardCharsets.UTF_8))
    }
  }

  private def loadMatrix(filename:String) = {
    val inputLines = Source.fromFile(new File(filename)).getLines().toList
    val input = inputLines.map(_.split(' ').map(_.toInt).toList)
    checkInput(input)
    DenseMatrix[List[Int], Int](input: _*)
  }

  def printMatrix(m:DenseMatrix[_]) = {
      m.toString().replaceAll("( )+", " ").lines.map(_.trim).mkString("\n")
  }
}
