package com.example.Bai6.model;

import com.example.Bai6.Book.Book;

public class ItemOrder {
    private Integer numberBook;
    private Book book;

    public ItemOrder(Book item) {
        setBook(item);
        setNumberBook(1);
    }

  public String getLongDescription() {
      return getBook().getLongDescription();
  }

  public String getShortDescription() {
      return  getBook().getShortDescription();
  }

    public  String getItemId() { return  (getBook().getID());}

    public double getUnitCost() {
        return(getBook().getCost());
    }
    public void incrementNumItems() {
        setNumberBook(getNumberBook() + 1);
    }

    public void cancelOrder() {
        setNumberBook(0);
    }

    public double getTotalCost() {
        return(getNumberBook() * getUnitCost());
    }



    public Integer getNumberBook() {
        return numberBook;
    }

    public void setNumberBook(Integer numberBook) {
        this.numberBook = numberBook;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
