import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static List<String> stringList = new ArrayList(Arrays.asList("a1","a2","a3","a4"));
    static Collection<People> peoples = Arrays.asList(
            new People("Ewan", 16, Sex.MAN),
            new People("Garry", 24, Sex.MAN),
            new People("Iren", 24, Sex.WOMEN),
            new People("Edward", 65, Sex.MAN));

    public static void main(String[] args) {
        //возвращает количество совпадений
        long count = stringList.stream().filter("a1"::equals).count();
        System.out.println(count);

        //определяет, пуста ли коллекция или нет. Если да, то верньт "0" (но стрингом)
        String isEmpty = stringList.stream().findFirst().orElse("0");
        System.out.println(isEmpty);

        //находит первый элемент "a3" или выбрасывает экзепшен
        String giveMeA3 = stringList.stream().filter("a3"::equals).findFirst().get();
        System.out.println(giveMeA3);

        //получаем 3й єлемент коллекции
        String get3thElement = stringList.stream().skip(2).findFirst().get();
        System.out.println(get3thElement);

        Object[] arraysOflements = stringList.stream().skip(1).limit(2).toArray();
        System.out.println(arraysOflements);

//-------------------------работа с обьектом----------------------------------

        //выбирает военнообязанных мужчин
        Collection<People> peopleForServe = peoples.stream()
                        .filter((p)-> p.getAge() >= 18 && p.getAge() < 27 && p.getSex() == Sex.MAN)
                        .collect(Collectors.toList());
        System.out.println(peopleForServe);

        //выводим средний возраст всех мужчин
        double middleAgeOfMen = peoples.stream()
                .filter((p) -> p.getSex() == Sex.MAN)
                .mapToInt(People::getAge).average().getAsDouble();
        System.out.println(middleAgeOfMen);

        //выводим к-во потенциально трудоспособных людей
        long coutsOfPotentialWorkers = peoples.stream()
                .filter((p) -> p.getAge() >= 18)
                .filter((p) -> (p.getSex() == Sex.WOMEN && p.getAge() < 55) || (p.getSex() == Sex.MAN && p.getAge() < 60))
                .count();
        System.out.println(coutsOfPotentialWorkers);

//-----------------------------------Работа с методами .match() (Все что ниже, - boolean)-------------------------------

        //есть ли в коллекции хоть 1 "a1"
        boolean a1IsExistInAny = stringList.stream().anyMatch("a1"::equals);
        System.out.println(a1IsExistInAny);

        //есть ли в коллекции хоть 1 "a8"
        boolean a8IsExistInAny = stringList.stream().anyMatch("a8"::equals);
        System.out.println(a8IsExistInAny);

        //Есть ли во всех элементах коллекции "a"
        boolean aIsExistInAll = stringList.stream().allMatch(a->(a.contains("a")));
        System.out.println(aIsExistInAll);

        //Проверка на то, что элемента "а14" не существует (и даже не содержит)
        boolean a14isNotExists = stringList.stream().noneMatch(a->(a.contains("a14")));
        System.out.println(a14isNotExists);

//-----------------------------------
    }
}
