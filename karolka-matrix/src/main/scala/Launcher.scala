import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

import breeze.linalg.DenseMatrix

import scala.io.Source

object Launcher {

  def checkInput(input: List[List[Int]]) = {
    val size = input.head.size
    require(input.forall(_.size == size))
  }

  def main(args: Array[String]): Unit = {
    try {
      val inputLines = Source.fromFile(new File(args(0))).getLines().toList
      val input = inputLines.map(_.split(' ').map(_.toInt).toList)
      val param = Source.fromFile(new File(args(1))).getLines().mkString.toInt
      checkInput(input)
      println(s"Input $input")
      println(s"Input $param")
      val inputMatrix = DenseMatrix[List[Int], Int](input: _*)
      val outputMatrix: DenseMatrix[Int] = inputMatrix *:* param
      Files.write(Paths.get(args(2)), outputMatrix.toString().getBytes(StandardCharsets.UTF_8))
    } catch{
      case e: Exception => Files.write(Paths.get(args(2)), "Invalid input matrix.".getBytes(StandardCharsets.UTF_8))
    }
  }
}
