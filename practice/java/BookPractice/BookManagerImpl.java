package BookPractice;

public class BookManagerImpl implements IBookManager {

  private static IBookManager instance = new BookManagerImpl();
  final int MAX_SIZE = 100;
  int size;
  Book[] books;

  public BookManagerImpl() {
    books = new Book[MAX_SIZE];
  }

  public static IBookManager getInstance() {
    return instance;
  }

  public void add(Book book) {
    books[size++] = book;
  }

  public void remove(String isbn) {
    System.out.printf("***************도서삭제:%s***************\n", isbn);
    for (int i = 0; i < size; i++) {
      if (books[i].getIsbn().equals(isbn)) {
        for (int j = i + 1; j < size; j++) {
          books[j - 1] = books[j];
        }
        books[(size--) - 1] = null;
        break;
      }
    }
  }

  public Book[] getList() {
    return books;
  }

  public Book searchByIsbn(String isbn) {
    System.out.printf("***************도서조회:%s***************\n", isbn);
    for (int i = 0; i < size; i++) {
      if (books[i].getIsbn().equals(isbn)) {
        return books[i];
      }
    }
    return null;
  }

  public Book[] searchByTitle(String title) {
    System.out.printf(
      "***************도서 제목 포함검색:%s***************\n",
      title
    );
    Book[] temp = new Book[size];
    int cnt = 0;
    for (int i = 0; i < size; i++) {
      if (books[i].getTitle().contains(title)) {
        temp[cnt++] = books[i];
      }
    }
    return temp;
  }

  public Magazine[] getMagazines() {
    System.out.println("***************잡지 목록***************");
    Magazine[] temp = new Magazine[size];
    int cnt = 0;
    for (int i = 0; i < size; i++) {
      if (books[i] instanceof Magazine) {
        temp[cnt++] = (Magazine) books[i];
      }
    }

    return temp;
  }

  public Book[] getBooks() {
    System.out.println("***************일반 도서 목록***************");
    Book[] temp = new Book[size];
    int cnt = 0;
    for (int i = 0; i < size; i++) {
      if (!(books[i] instanceof Magazine)) {
        temp[cnt++] = books[i];
      }
    }

    return temp;
  }

  public int getTotalPrice() {
    int sum = 0;
    for (int i = 0; i < size; i++) {
      sum += books[i].getPrice();
    }

    return sum;
  }

  public double getPriceAvg() {
    return (double) getTotalPrice() / (double) size;
  }

  public void sell(String isbn, int queantity)
    throws ISBNNotFoundException, QuantityException {
    System.out.printf(
      "***************도서판매:%s %d개***************\n",
      isbn, queantity
    );
    for (int i = 0; i < size; i++) {
      if (books[i].getIsbn().equals(isbn)) {
        if (books[i].getQueantity() < queantity) {
          throw new QuantityException();
        } else {
          books[i].setQueantity(books[i].getQueantity() - queantity);
          System.out.println(books[i]);
        }
        return;
      }
    }
    throw new ISBNNotFoundException(isbn);
  }

  public void buy(String isbn, int queantity) throws ISBNNotFoundException {
    System.out.printf(
        "***************도서구매:%s %d개***************\n",
        isbn, queantity
      );
    for (int i = 0; i < size; i++) {
      if (books[i].getIsbn().equals(isbn)) {
        books[i].setQueantity(books[i].getQueantity() + queantity);
        System.out.println(books[i]);
        return;
      }
    }
    throw new ISBNNotFoundException(isbn);
  }

  public void print() {
    System.out.println("***************도서 전체 목록***************");
    for (Book b : getList()) {
      if (b == null) break;
      System.out.println(b);
    }
  }

  public void print(Book[] books) {
    for (Book b : books) {
      if (b == null) break;
      System.out.println(b);
    }
  }

  public void print(Book book) {
    System.out.println(book);
  }
}
