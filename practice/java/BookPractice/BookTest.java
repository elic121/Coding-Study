package BookPractice;

public class BookTest {

  public static void main(String[] args) {
    IBookManager bm = BookManagerImpl.getInstance();

    Book book1 = new Book(
      "21424",
      "Java Pro",
      "김하나",
      "jaen.kr",
      15000,
      "Java 기본 문법",
      10
    );

    Book book2 = new Book(
      "35355",
      "분석설계",
      "소나무",
      "jaen.kr",
      30000,
      "SW 모델링",
      20
    );

    Book book3 = new Book(
      "21425",
      "Java Pro2",
      "김하나",
      "jaen.kr",
      25000,
      "Java 기본 문법",
      30
    );

    Book book4 = new Magazine(
      "45678",
      "월간 알고리즘",
      "홍길동",
      "jaen.kr",
      10000,
      "1월 알고리즘",
      2021,
      1,
      40
    );

    bm.add(book1);
    bm.add(book2);
    bm.add(book3);
    bm.add(book4);

    bm.print();

    // System.out.println(bm.searchByIsbn("21424"));

    // bm.remove("21424");

    bm.print(bm.getBooks());
    bm.print(bm.getMagazines());

    bm.print(bm.searchByTitle("Java"));

    System.out.println("도서 가격 총합 : " + bm.getTotalPrice());
    System.out.println("도서 가격 평균 : " + bm.getPriceAvg());

    try {
      bm.sell("21424", 11);
    } catch (QuantityException e) {
      System.out.println(e.getMessage());
    } catch (ISBNNotFoundException e) {
      System.out.println(e.getMessage());
    }

    try {
      bm.buy("21424", 10);
    } catch (ISBNNotFoundException e) {
      System.out.println(e.getMessage());
    }

    try {
      bm.sell("21424", 11);
    } catch (QuantityException e) {
      System.out.println(e.getMessage());
    } catch (ISBNNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }
}
