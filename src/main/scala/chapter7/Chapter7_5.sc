trait HtmlWriteable {
  def toHtml: String
}

final case class Person(
                         name: String,
                         email: String
                       ) extends HtmlWriteable {

  override def toHtml: String =
    s"<span>$name &lt;$email&gt;</span>"
}


Person("scalaUser", "scala@gm.com").toHtml


trait Displayable {
  def display: String
}

final case class Product(
                          name: String,
                          price: Double
                        ) extends Displayable {

  override def display: String =
    s"<span>$name - £$price</span>"
}


trait HtmlWriter[A] {
  def write(in: A): String
}

object PersonWriter
  extends HtmlWriter[Person] {

  def write(person: Person): String =
    s"<span>${person.name} &lt;${person.email}&gt;</span>"
}


object FullProductWriter
  extends HtmlWriter[Product] {

  override def write(product: Product): String =
    s"<span>${product.name} - £${product.price}</span>"
}

object NameOnlyProductWriter
  extends HtmlWriter[Product] {

  override def write(product: Product): String =
    s"<span>${product.name}</span>"
}

trait Formatter[A] {
  def format(value: A): String
}

object FullProductFormatter
  extends Formatter[Product] {

  override def format(product: Product): String =
    s"${product.name} - £${product.price}"
}

object NameProductFormatter
  extends Formatter[Product] {

  override def format(product: Product): String =
    product.name
}