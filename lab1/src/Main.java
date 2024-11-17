import java.lang.reflect.InvocationTargetException;

// Оголошення класу Main
public class Main {

    // Створення об'єкту класу TestRunner
    private TestRunner test = new TestRunner();

    // Точка входу в програму
    public static void main(String[] args) throws NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        // Створення об'єкту класу Main
        Main app = new Main();
        // Виклик методу startApp для початку виконання програми
        app.startApp();
    }

    // Метод для початку виконання програми
    private void startApp() throws NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        // Створення масиву рядків з іменем класу тестів (в даному випадку "CalculatorTest")
        String[] str = new String[] {"CalculatorTest"};
        try {
            // Виклик методу main класу TestRunner із передачею масиву рядків
            test.main(str);
        } catch (ClassNotFoundException e) {
            // Обробка виключення, якщо клас тестів не знайдено
            System.out.println("Class not found");
        }
    }
}
