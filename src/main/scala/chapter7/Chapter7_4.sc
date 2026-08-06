final case class Rational(
                           numerator: Int,
                           denominator: Int
                         )

object Rational {

  implicit val ordering: Ordering[Rational] =
    Ordering.fromLessThan[Rational] { (first, second) =>
      first.numerator.toDouble / first.denominator <
        second.numerator.toDouble / second.denominator
    }
}

val values =
  List(
    Rational(1, 2),
    Rational(3, 4),
    Rational(1, 3)
  )

println(values.sorted)



final case class Product(
                          name: String,
                          price: Double
                        )

object Product {

  implicit val priceOrdering: Ordering[Product] =
    Ordering.fromLessThan[Product] { (first, second) =>
      first.price < second.price
    }
}

object ProductExample {

  val products =
    List(
      Product("Laptop", 900.0),
      Product("Mouse", 20.0),
      Product("Keyboard", 60.0)
    )

  // This should fail because the implicit is hidden
   println(products.sorted)
}

ProductExample



