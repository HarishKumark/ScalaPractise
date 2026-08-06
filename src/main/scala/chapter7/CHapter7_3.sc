
final case class Rational(
                           numerator: Int,
                           denominator: Int
                         )


object Instances {

  implicit val rationalOrdering: Ordering[Rational] =
    Ordering.fromLessThan[Rational] { (first, second) =>
      first.numerator.toDouble / first.denominator <
        second.numerator.toDouble / second.denominator
    }
}

object Example {

import Instances.rationalOrdering
  val values =
    List(
      Rational(1, 2),
      Rational(3, 4),
      Rational(1, 3)
    )

  values.sorted
}