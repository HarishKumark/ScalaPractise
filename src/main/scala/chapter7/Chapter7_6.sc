final case class Product(name: String)

trait Formatter[A] {
  def format(value: A): String
}

implicit object ProductFormatter
  extends Formatter[Product] {

  override def format(product: Product): String =
    s"Product: ${product.name}"
}

object Formatter {

  def apply[A](
                implicit instance: Formatter[A]
              ): Formatter[A] =
    instance
}


case class Person(
                   name: String,
                   email: String
                 )

trait Equal[A] {
  def equal(v1: A, v2: A): Boolean
}

object Eq {

  def apply[A](
                v1: A,
                v2: A
              )(
                implicit equal: Equal[A]
              ): Boolean =
    equal.equal(v1, v2)
}

object EmailEquality {

  implicit val emailEqual: Equal[Person] =
    new Equal[Person] {

      override def equal(
                          v1: Person,
                          v2: Person
                        ): Boolean =
        v1.email == v2.email
    }
}

object NameAndEmailEquality {

  implicit val nameAndEmailEqual: Equal[Person] =
    new Equal[Person] {

      override def equal(
                          v1: Person,
                          v2: Person
                        ): Boolean =
        v1.name == v2.name &&
          v1.email == v2.email
    }
}

val person1 =
  Person("John", "john@example.com")

val person2 =
  Person("Johnny", "john@example.com")

val person3 =
  Person("John", "different@example.com")


Eq(person2, person1)(EmailEquality.emailEqual)

Eq(person3, person1)(NameAndEmailEquality.nameAndEmailEqual)


implicit class ExtraStringMethods(str: String) {

  val vowels =
    Seq('a', 'e', 'i', 'o', 'u')

  def numberOfVowels: Int =
    str.toList
      .filter(vowels contains _)
      .length
}

new ExtraStringMethods(
  "the quick brown fox"
).numberOfVowels

"the quick brown fox".numberOfVowels