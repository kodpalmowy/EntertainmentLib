package com.kodpalmowy.database.dao;

import com.kodpalmowy.database.models.Book;
import com.kodpalmowy.database.utils.ConnectionClass;
import com.kodpalmowy.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends Dao{

    private List<Book> bookList = new ArrayList<>();

    public void insertBook(Book book){
        String SQL_INSERT_QUERY = "INSERT INTO bookList (title, author, genre, description, ISBN, publisher, rating, readDate) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection connection = ConnectionClass.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_QUERY)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setString(4, book.getDescription());
            preparedStatement.setString(5, book.getISBN());
            preparedStatement.setString(6, book.getPublisher());
            preparedStatement.setInt(7, book.getRating());
            preparedStatement.setDate(8, java.sql.Date.valueOf(Utils.convertToLocalDate(book.getReadDate())));
            preparedStatement.execute();
        } catch (SQLException sqle) {
            System.out.println("SQLException(INSERT) : " + sqle.getMessage());
            // change this later to logger
        }
    }

    public List<Book> queryBooks(){
        String SQL_SELECT_QUERY = "SELECT * FROM bookList";
        try (Connection connection = ConnectionClass.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_QUERY)){
            while (resultSet.next()){
                Book book = new Book();
                book.setBookId(resultSet.getInt("bookId"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setDescription(resultSet.getString("description"));
                book.setISBN(resultSet.getString("ISBN"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setRating(resultSet.getInt("rating"));
                book.setReadDate(Utils.convertToDate(resultSet.getDate("readDate").toLocalDate()));
                bookList.add(book);
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException(SELECT) : " + sqle.getMessage());
        }
        return bookList;
    }

    public void deleteBook(Integer id){
        String SQL_DELETE_QUERY = "DELETE FROM bookList WHERE bookId=" + id;
        try (Connection connection = ConnectionClass.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(SQL_DELETE_QUERY);
        } catch (SQLException sqle) {
            System.out.println("SQLException(DELETE) : " + sqle.getMessage());
            // change this later to logger
        }
    }


}
