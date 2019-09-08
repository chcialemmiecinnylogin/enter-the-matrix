import java.io.File

import breeze.linalg.DenseMatrix
import breeze.linalg._
import breeze.numerics._
import scala.io.Source

object Launcher {

  def main(args: Array[String]) : Unit = {
    val inputLines = Source.fromFile(new File(args(0))).getLines().toList
    val input = inputLines.map(_.split(' ').map(_.toDouble).toList)
    val param = Source.fromFile(new File(args(1))).getLines().mkString.toDouble
    println(s"Input $input")
    println(s"Input $param")
    val inputMatrix = DenseMatrix[List[Double],Double](input : _*)
    val outputMatrix:DenseMatrix[Double] = inputMatrix *:*  param
    println(outputMatrix)
  }
}
