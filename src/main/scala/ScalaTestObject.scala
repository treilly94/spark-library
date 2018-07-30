object ScalaTestObject {

  protected def maths(val1: Int, val2: Int): Int = {
    val1 + val2
  }

  def main(args: Array[String]): Unit = {
    val ans: Int = maths(1, 4)

    print(ans)
  }
}
