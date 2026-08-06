final case class Product(
                          name: String,
                          price: Double
                        )

object Product {

  implicit val defaultOrdering: Ordering[Product] =
    Ordering.fromLessThan[Product] { (a, b) =>
      a.price < b.price
    }
}

object ProductExample {

  implicit val localOrdering: Ordering[Product] =
    Ordering.fromLessThan[Product] { (a, b) =>
      a.price > b.price
    }

  val products =
    List(
      Product("Laptop", 900.0),
      Product("Mouse", 20.0),
      Product("Keyboard", 60.0)
    )

  def run(): Unit =
    println(products.sorted)
}


ProductExample.run()


final case class Rational(
                           numerator: Int,
                           denominator: Int
                         )

object Rational {

  implicit val ordering: Ordering[Rational] =
    Ordering.fromLessThan[Rational] { (x, y) =>
      x.numerator.toDouble / x.denominator.toDouble <
        y.numerator.toDouble / y.denominator.toDouble
    }
}

object Example {

//  implicit val higherPriorityImplicit: Ordering[Rational] =
//    Ordering.fromLessThan[Rational] { (x, y) =>
//      x.numerator.toDouble / x.denominator.toDouble >
//        y.numerator.toDouble / y.denominator.toDouble
//    }

  def example(): Unit = {
    val result =
      List(
        Rational(1, 2),
        Rational(3, 4),
        Rational(1, 3)
      ).sorted

    println(result)
  }
}

Example.example()



final case class Order(
                        units: Int,
                        unitPrice: Double
                      ) {
  val totalPrice: Double =
    units * unitPrice
}

object Order {

  implicit val totalPriceOrdering: Ordering[Order] =
    Ordering.fromLessThan[Order] { (first, second) =>
      first.totalPrice < second.totalPrice
    }
}

object OrderUnitsOrdering {

  implicit val unitsOrdering: Ordering[Order] =
    Ordering.fromLessThan[Order] { (first, second) =>
      first.units < second.units
    }
}

object OrderUnitPriceOrdering {

  implicit val unitPriceOrdering: Ordering[Order] =
    Ordering.fromLessThan[Order] { (first, second) =>
      first.unitPrice < second.unitPrice
    }
}


List(
  Order(5, 3.0),
  Order(2, 10.0),
  Order(1, 25.0)
)

Order(4, 5.0).totalPrice