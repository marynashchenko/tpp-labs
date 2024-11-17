import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Оголошення анотації з назвою "MyAnnotation"
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    // Оголошення елементу анотації (в даному випадку це просто рядок)
    String value() default "";
}

// Використання анотації на класі
@MyAnnotation(value = "This is a sample annotation")
public class CalculatorTest {

    // Використання анотації на полі
    @MyAnnotation(value = "This is a field annotation abc")
    private int myField;

    // Використання анотації на методі
    @MyAnnotation(value = "This is a method annotation")
    public void myMethod() {
        // Implementation
    }

    // Використання анотації на конструкторі
    @MyAnnotation(value = "This is a constructor annotation")
    public CalculatorTest() {
        // Implementation
    }

    public static void main(String[] args) {
        // Отримання анотації за допомогою рефлексії
        MyAnnotation classAnnotation = CalculatorTest.class.getAnnotation(MyAnnotation.class);
        System.out.println("Class Annotation: " + classAnnotation.value());

        try {
            // Отримання анотації для поля за допомогою рефлексії
            MyAnnotation fieldAnnotation = CalculatorTest.class.getDeclaredField("myField").getAnnotation(MyAnnotation.class);
            System.out.println("Field Annotation: " + fieldAnnotation.value());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // Отримання анотації для методу за допомогою рефлексії
        MyAnnotation methodAnnotation = null;
        try {
            methodAnnotation = CalculatorTest.class.getMethod("myMethod").getAnnotation(MyAnnotation.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("Method Annotation: " + methodAnnotation.value());
    }
}

// Клас для тестування функцій класу Calculator
/*interface Annotation {
    // This annotation has two attributes.
    public String key();
    public String value();
}

@Annotation(key = "GFG", value = "GeeksForGeeks")
public class CalculatorTest {

    // Створення об'єкту класу Calculator для тестування
    Calculator calculator = new Calculator();

    // Тест на додавання, повинен бути викликаний та пройдений
    public void testPlus() throws Exception {
        // Виклик функції додавання
        int result = calculator.plus(2, 2);
        // Перевірка правильності результату
        if (result != 4) {
            // Викид виключення у випадку невідповідності результату
            throw new Exception("2+2 must be 4");
        }
    }

    // Тест на віднімання, не повинен бути викликаний
    public void testMinus() throws Exception {
        // Виклик функції віднімання
        int result = calculator.subtract(4, 2);
        // Перевірка правильності результату
        if (result != 2) {
            // Викид виключення у випадку невідповідності результату
            throw new Exception("4-2 must be 2");
        }
    }

    // Неправильно написаний тест на віднімання, не повинен бути викликаний
    public void tesMinus() throws Exception {
        // Виклик функції віднімання
        int result = calculator.subtract(4, 2);
        // Перевірка правильності результату
        if (result != 2) {
            // Викид виключення у випадку невідповідності результату
            throw new Exception("4-2 must be 2");
        }
    }

    // Тест на множення, має завершитися невдачею через помилку в функції multiply
    private void testMultiply() throws Exception {
        // Виклик функції множення
        int result = calculator.multiply(3, 3);
        // Перевірка правильності результату
        if (result != 9) {
            // Викид виключення у випадку невідповідності результату
            throw new Exception("3*3 must be 9");
        }
    }
}*/

