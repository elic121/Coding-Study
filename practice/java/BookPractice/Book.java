package BookPractice;

public class Book {

  String isbn, title, author, publisher, desc;
  int price, queantity;

  public Book() {}

  public Book(
    String isbn,
    String title,
    String author,
    String publisher,
    int price,
    String desc,
    int queantity
  ) {
    this.isbn = isbn;
    this.title = title;
    this.author = author;
    this.publisher = publisher;
    this.price = price;
    this.desc = desc;
    this.queantity = queantity;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    return sb
      .append(isbn)
      .append(" | ")
      .append(title)
      .append(" | ")
      .append(author)
      .append(" | ")
      .append(publisher)
      .append(" | ")
      .append(price)
      .append(" | ")
      .append(desc)
      .append(" | ")
      .append(queantity)
      .toString();
  }

  public String getAuthor() {
    return author;
  }

  public String getDesc() {
    return desc;
  }

  public String getIsbn() {
    return isbn;
  }

  public int getPrice() {
    return price;
  }

  public String getPublisher() {
    return publisher;
  }

  public String getTitle() {
    return title;
  }

  public int getQueantity() {
      return queantity;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setQueantity(int queantity) {
      this.queantity = queantity;
  }
}
