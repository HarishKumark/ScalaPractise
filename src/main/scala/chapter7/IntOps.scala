package chapter7

class IntOps(value: Int) {

  def yeah(): Unit =
    for (_ <- 0 until value) {
      println("Oh yeah!")
    }
}

implicit def intToIntOps(value: Int): IntOps =
  new IntOps(value)



//new IntOps(3).yeah()