package jayden.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

import jayden.myapplication.R;
import jayden.myapplication.entity.Book;

public class LitePalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);
    }
    public void createDB(View view){
        LitePal.getDatabase();
    }
    public void addData(View view){
        Book book=new Book();
        book.setName("Xu Cai Hui");
        book.setAuthor("Jayden Cool");
        book.setPages(454);
        book.setPrice(16.96);
        book.setPress("Slogan");
        book.save();
    }
    public void updateData(View view){
        Book book=new Book();
        /*book.setName("LiXiuyun");
        book.setAuthor("jayden");
        book.setPages(507);
        book.setPrice(19.95);
        book.setPress("北京大学出版社");
        book.save();
        book.setPrice(10.99);
        book.save();*/
        book.setPrice(18.96);
        book.setPress("Anchor");
        book.updateAll("name = ? and author = ?","LiXiuyun","jayden");
//        book.setToDefault("pages");
//        book.updateAll();
    }
    public void deleteData(View view){
        DataSupport.deleteAll(Book.class,"price < ?","15");
    }
    public void queryData(View view){
        List<Book> books=DataSupport.findAll(Book.class);


        ((TextView)findViewById(R.id.textView)).setText(books.toString());
    }
}
