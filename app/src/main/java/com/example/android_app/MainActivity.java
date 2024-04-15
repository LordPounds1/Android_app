package com.example.android_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android_app.adapter.CategoryAdapter;
import com.example.android_app.adapter.CourseAdapter;
import com.example.android_app.model.Category;
import com.example.android_app.model.Course;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>();
    static List<Course> fullcoursesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1,"Игры"));
        categoryList.add(new Category(2,"Сайты"));
        categoryList.add(new Category(3,"Языки"));
        categoryList.add(new Category(4,"Прочее"));

        setCategoryRecycler(categoryList);

        courseList.add(new Course(1,"java", "Профессия Java\nРазработчик", "10 марта", "начальный", "#424345","Программа обучения Джава – рассчитанана новичков в данной сфере.За программу вы изучите построение графических приложений под ПК,разработку веб сайтов на основе JavaSpring Boot, изучите построение полноценных Андроид приложенийи отлично изучите сам язык Джава!", 3));
        courseList.add(new Course(2,"python", "Профессия Python\nРазработчик", "1 апреля", "начальный", "#747B2B", "Программа обучения Python предназначена для новичков в сфере программирования. В ходе обучения вы освоите различные аспекты этого языка программирования. ", 2));




        fullcoursesList.addAll(courseList);
        setCourseRecycler(courseList);

    }



    public void openShoppingCart(View view){
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }


    private void setCourseRecycler(List<Course> courseList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);

    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this,categoryList);
        categoryRecycler.setAdapter(categoryAdapter);


    }



    public static void showCoursesByCategory(int category){

        courseList.clear();
        courseList.addAll(fullcoursesList);

        List<Course> filterCourses = new ArrayList<>();

        for (Course c : courseList){
            if (c.getCategory() == category)
                filterCourses.add(c);
        }

        courseList.clear();
        courseList.addAll(filterCourses);

        courseAdapter.notifyDataSetChanged();

    }


}