import scala.math.Ordering


val minOrdering =
  Ordering.fromLessThan[Int](_ < _)


val absOrdering: Ordering[Int] =
  Ordering.fromLessThan[Int] { (first, second) =>
    Math.abs(first) < Math.abs(second)
  }


implicit val absOrderingNew: Ordering[Int] =
  Ordering.fromLessThan[Int] { (first, second) =>
    Math.abs(first) < Math.abs(second)
  }

val numbers =
  List(-4, -1, 0, 2, 3)

val result =
  List(3, 4, 2).sorted(minOrdering)

val r = numbers.sorted(absOrdering)
println(r)


val r_Imp = numbers.sorted
println(r_Imp)


final case class Rational(
                           numerator: Int,
                           denominator: Int
                         )


val rationalOrdering: Ordering[Rational] =
  Ordering.fromLessThan[Rational] { (first, second) =>
    val fi = first.numerator.toDouble / first.denominator.toDouble

    val sec = second.numerator.toDouble / second.denominator.toDouble

    fi < sec
  }


val rationalRes = List(Rational(1, 3), Rational(8, 4), Rational(2, 4), Rational(1, 2)).sorted(rationalOrdering)

println(rationalRes)


println("##############")

println(
  List(
    Rational(1, 2),
    Rational(3, 4),
    Rational(1, 3)
  ).sorted(rationalOrdering)
)

println(
  List(
    Rational(5, 2),
    Rational(1, 4),
    Rational(2, 3)
  ).sorted(rationalOrdering)
)


def example(): Unit = {

//  implicit val ordering: Ordering[Int] =
//    Ordering.fromLessThan[Int](_ < _)

  println(List(3, 1, -2).sorted)
}

example()


//final case class Rational(
//                           numerator: Int,
//                           denominator: Int
//                         )

object Example {

  def run(): Unit = {

    implicit val rationalOrdering: Ordering[Rational] =
      Ordering.fromLessThan[Rational] { (first, second) =>

        val firstValue =
          first.numerator.toDouble / first.denominator

        val secondValue =
          second.numerator.toDouble / second.denominator

        firstValue < secondValue
      }

    val values =
      List(
        Rational(1, 2),
        Rational(3, 4),
        Rational(1, 3)
      )

    println(values.sorted)
  }
}

Example.run()


final case class Product(
                          name: String,
                          price: Double
                        )

object ProductExample {

  def run(): Unit = {

    implicit val priceOrdering: Ordering[Product] =
      Ordering.fromLessThan[Product] { (first, second) =>
        first.price < second.price
      }

    val products =
      List(
        Product("Laptop", 900.0),
        Product("Mouse", 20.0),
        Product("Keyboard", 60.0)
      )

    println(products.sorted)
  }
}

ProductExample.run()