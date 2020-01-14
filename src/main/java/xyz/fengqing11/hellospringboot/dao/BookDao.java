package xyz.fengqing11.hellospringboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.fengqing11.hellospringboot.pojo.Book;

import java.util.List;

@Repository
public class BookDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addBook(Book book) {
        return jdbcTemplate.update("INSERT INTO books(name,author) values (?,?)",
                book.getName(), book.getAuthor());
    }

    public int updateBook(Book book) {
        return jdbcTemplate.update("UPDATE books SET name=?,author=? WHERE id = ?",
                book.getId(), book.getName(), book.getAuthor());
    }

    public int deleteBookById(Integer id) {
        return jdbcTemplate.update("DElETE FROM books WHERE id=?", id);
    }

    public Book getBookById(Integer id){
        return jdbcTemplate.queryForObject("SELECT * FROM books WHERE id=?",
                new BeanPropertyRowMapper<>(Book.class),id);
    }

    public List<Book> getAllBooks(){
        return jdbcTemplate.query("SELECT * FROM books",
                new BeanPropertyRowMapper<>(Book.class));
    }
}
