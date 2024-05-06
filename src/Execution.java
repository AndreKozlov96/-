import java.util.*;

public class Execution {
    static HashMap<String, ArrayList<String>> phoneBook = new HashMap<>();

    public static void phoneBookAction() {
        userDialog();
    }

    public static void userDialog() {
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                Введите идентификатор желаемого действия:
                1 - добавить запись в телефонную книгу;
                2 - распечатать телефонную книгу;
                3 - выйти из программы.""");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                addPhoneNumber();
                phoneBookAction();
            case 2:
                printPhoneBook();
                phoneBookAction();
            case 3:
                System.exit(0);
            default:
                System.out.println("Вы указали несуществующий вариант. Попробуйте снова.\n");
                phoneBookAction();
        }
    }

    private static void addPhoneNumber(){
        Scanner scPhNum = new Scanner(System.in);

        System.out.println("""
                       Введите имя абонента и номер его телефона в формате \"Имя абонента, номер телефона\".
                       Допускается ввести сразу несколько номеров телефона для одного абонента через запятую:""");

        String[] phoneNumber = scPhNum.nextLine().replace(" ", "").split(",");

        if (!phoneBook.containsKey(phoneNumber[0]))
            phoneBook.put(phoneNumber[0], new ArrayList<>());

        for (int i = 1; i < phoneNumber.length; i++) {
            phoneBook.get(phoneNumber[0]).add(phoneNumber[i]);
        }
        System.out.println("\nЗапись успешно добавлена! \n");
    }

    private static void printPhoneBook(){
        List<Map.Entry<String, ArrayList<String>>> entryList = new ArrayList<>(phoneBook.entrySet());
        List<Map.Entry<String, ArrayList<String>>> printBook = new ArrayList<>();
        ArrayList<Integer> phoneQuantity = new ArrayList<>(); //лист для хранения значений количества телефонов у абонентов
        int index;
        System.out.println("\nВ телефонной книге содержатся следующие записи:");

        for (Map.Entry<String, ArrayList<String>> record: entryList)
            phoneQuantity.add(record.getValue().size());

            //Вывод листа в начальном состоянии
            //System.out.println(phoneQuantity);

        for (int i = 0; i < phoneQuantity.size(); i++) {
            index = phoneQuantity.indexOf(Collections.max(phoneQuantity));//находим индекс максимального элемента-->
                                // -->(соответствует индексу абонента в <MAP.Entry> entryList с максимальным количеством телефонов)
            System.out.println(entryList.get(index));
            phoneQuantity.set(index, 0);

            //вывод листа для промежуточной проверки его состояния
            //System.out.println(phoneQuantity);
        }
        System.out.println("");
    }
}

