import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Клас, який виконує тести за допомогою рефлексії
public class TestRunner {

    // Точка входу в програму для виклику тестів
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, InstantiationException {

        // Перевірка, чи передано рівно один аргумент
        if (args.length != 1) {
            throw new IllegalArgumentException("Exactly 1 argument must be provided");
        }

        // Отримання класу тестів за його іменем
        Class<?> testClass = Class.forName(args[0]);

        // Отримання всіх методів класу
        Method[] methods = testClass.getDeclaredMethods();

        // Ініціалізація змінних для статистики тестування
        String test = "test";
        int tests = 0, fail = 0, success = 0, meth = 0, flag = 0;

        // Ітерація по всіх методах класу
        for (Method met : methods) {
            meth++;
            flag = 0;

            // Перевірка, чи назва методу містить слово "test"
            Annotation ano = met.getAnnotation(MyAnnotation.class);
            if(ano!=null){
            //System.out.println(ano);
            if (ano.toString().contains("abc")) {
                System.out.println("Method: " + met.getName() + " is not a test method because it does not contain 'abc'");
                flag++;
                }
            }

            // Перевірка, чи метод є публічним
            if (!java.lang.reflect.Modifier.isPublic(met.getModifiers())) {
                System.out.println("Method: " + met.getName() + " is not a test method because it is not public");
                flag++;
            }

            // Перевірка, чи метод не приймає параметрів
            if (met.getParameterCount() != 0) {
                System.out.println("Method: " + met.getName() + " is not a test method because it has a non-zero parameter count");
                flag++;
            }

            // Перевірка, чи метод не повертає значення (void)
            if (met.getReturnType() != void.class) {
                System.out.println("Method: " + met.getName() + " is not a test method because it returns a non-void value");
                flag++;
            }

            // Якщо всі перевірки виконані, викликається метод тестування
            if (flag == 0) {
                Object obj = testClass.newInstance();
                try {
                    tests++;
                    met.invoke(obj, null);
                    System.out.println("Test: " + met.getName() + " SUCCESSFUL");
                    success++;
                } catch (Exception e) {
                    // Обробка виключення у випадку невдачі тесту
                    fail++;
                    String n = e.getCause().getMessage();
                    n = n.replaceAll("java.lang.Exception", " ");
                    System.out.println("Test: " + met.getName() + " FAILED, error: Test failed due to " + n);
                }
            }
        }

        // Виведення загальної статистики тестування
        System.out.println("Total methods: " + meth + " Total tests: " + tests + " Successful tests: " + success + " Failed tests: " + fail);
    }
}
