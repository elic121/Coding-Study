package BookPractice;

public interface IBookManager {
  void add(Book book);
  void remove(String isbn);
  Book[] getList();
  Book searchByIsbn(String isbn);
  Book[] searchByTitle(String title);
  Magazine[] getMagazines();
  Book[] getBooks();
  int getTotalPrice();
  double getPriceAvg();
  void print();
  void print(Book book);
  void print(Book[] books);
  void sell(String isbn, int queantity) throws ISBNNotFoundException, QuantityException;
  void buy(String isbn, int queantity) throws ISBNNotFoundException;
}
