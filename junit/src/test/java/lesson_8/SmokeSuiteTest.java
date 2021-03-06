package lesson_8;

import lesson_8.features.TodoMVCTest;
import lesson_8.features.TodoOperationsAtAllFilterTest;
import lesson_8.categories.Smoke;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Categories.class)
@Suite.SuiteClasses({TodoMVCTest.class, TodoOperationsAtAllFilterTest.class})
@Categories.IncludeCategory(Smoke.class)
public class SmokeSuiteTest {

}
