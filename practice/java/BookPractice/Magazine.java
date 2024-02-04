package BookPractice;

public class Magazine extends Book {

  int year, month;

  Magazine() {}

  Magazine(
    String isbn,
    String title,
    String author,
    String publisher,
    int price,
    String desc,
    int year,
    int month,
    int queantity
  ) {
    super(isbn, title, author, publisher, price, desc, queantity);
    this.year = year;
    this.month = month;
  }

  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  @Override
  public String toString() {
    return super.toString() + " | " + year + " | " + month;
  }
}
