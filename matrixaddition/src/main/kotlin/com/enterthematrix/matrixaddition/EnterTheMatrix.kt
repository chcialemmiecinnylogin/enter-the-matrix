package com.enterthematrix.matrixaddition

import java.io.File

fun main(args: Array<String>) {
    try {
        val matrix = parseMatrix(File(args[0])).also { validateMatrix(it) }
        val rotated = matrixRotation(matrix)
        writeMatrix(rotated, File(args[2]))
    } catch (e: EnterTheMatrixException) {
        File(args[2]).writeText(e.message)
    }
}

fun parseMatrix(file: File): List<List<Int>> {
    return file.readLines().map { it.split(" ").map { Integer.parseInt(it) } }
}

fun validateMatrix(matrix: List<List<Int>>) {
    if (matrix.map { it.size }.distinct().size != 1) {
        throw InvalidInputMatrix()
    }
}

fun matrixMultiplicationByScalar(first: List<List<Int>>, scalar: Int): List<List<Int>> {
    return first.map { row -> row.map { it * scalar } }
}

fun addMatrix(first: List<List<Int>>, second: List<List<Int>>): List<List<Int>> {
    if (first.size != second.size) {
        throw InvalidMatrixSizes()
    }
    if (first.first().size != second.first().size) {
        throw InvalidMatrixSizes()
    }
    return first.zip(second).map { addRows(it.first, it.second) }
}

fun matrixRotation(matrix: List<List<Int>>): List<List<Int>> {
    return (matrix.first().indices).map { i -> matrix.reversed().map { row -> row[i] } }
}

fun addRows(first: List<Int>, second: List<Int>): List<Int> {
    return first.zip(second).map { it.first + it.second }
}

fun writeMatrix(matrix: List<List<Int>>, outputFile: File) {
    outputFile.writeText(matrix.map { it.joinToString(" ") }.joinToString("\n"))
}

class InvalidInputMatrix : EnterTheMatrixException("Invalid input matrix.")
class InvalidMatrixSizes : EnterTheMatrixException("Invalid matrix sizes.")
open class EnterTheMatrixException(override val message: String) : RuntimeException(message)