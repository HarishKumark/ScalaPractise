final case class Person(
                         name: String,
                         email: String
                       )

trait HtmlWriter[A] {
  def toHtml(value: A): String
}

implicit object PersonWriter
  extends HtmlWriter[Person] {

  override def toHtml(person: Person): String =
    s"${person.name} (${person.email})"
}

implicit class HtmlOps[T](data: T) {

  def toHtml(
              implicit writer: HtmlWriter[T]
            ): String =
    writer.toHtml(data)
}


Person(
  "John",
  "john@example.com"
).toHtml



final case class Product(name: String)

trait Formatter[A] {
  def format(value: A): String
}

implicit object ProductFormatter
  extends Formatter[Product] {

  override def format(product: Product): String =
    s"Product: ${product.name}"
}

implicit class FormatterOps[T](value: T) {

  def formatted(
                 implicit formatter: Formatter[T]
               ): String =
    formatter.format(value)
}

Product("test").formatted


object IntImplicits {

  implicit class IntOps(number: Int) {

    def times(action: Int => Unit): Unit = {
      if (number > 0) {
        (1 to number).foreach(action)
      }
    }

    def yeah(): Unit = {
      if (number > 0) {
        (1 to number).foreach { _ =>
          println("Oh yeah!")
        }
      }
    }
  }
}

import IntImplicits._
-2.yeah()

3.times { i =>
  println(s"Number: $i")
}

new IntOps(2).yeah()

new IntOps(2).times(i =>
  println(s"Number: $i"))

