package jayden.myapplication.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import jayden.myapplication.R;
import jayden.myapplication.db.MyDataBaseHelper;

public class CreatDBActivity extends AppCompatActivity {

    MyDataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_db);
        dbHelper= new MyDataBaseHelper(this, "BookStore.db", null, 2);
    }
    public void createDB(View view){
        dbHelper.getWritableDatabase();
    }
    public void addData(View view){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        //开始组装第一条数据
        values.put("name","The Da Vinci Code");
        values.put("author","Dan Brown");
        values.put("pages",454);
        values.put("price",16.96);
        db.insert("Book",null,values);
        values.clear();
        //开始组装第二条数据
        values.put("name","The Lost Symol");
        values.put("author","Dan Brown");
        values.put("pages",510);
        values.put("price",19.95);
        db.insert("Book",null,values);
    }
    public void upData(View view){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("price",10.99);
        db.update("Book",values,"name=?",new String[]{"The Da Vinci Code"});
    }
    public void deleteData(View view){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete("Book","pages > ?",new String[]{"500"});
    }
    public void queryData(View view){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        //查询表中所有的数据
        Cursor cursor=db.query("Book",null,null,null,null,null,null);
        if(cursor.moveToNext()){
            do{
               //遍历Cursor对象的，取出数据并显示
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String author=cursor.getString(cursor.getColumnIndex("author"));
                int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                double price=cursor.getDouble(cursor.getColumnIndex("price"));
                ((TextView)findViewById(R.id.textView)).setText("name:"+name+"\nauthor:"+author+"\npages:"+pages+"\nprice:"+price);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
