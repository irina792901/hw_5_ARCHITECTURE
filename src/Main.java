public class Main {
    public static void main(String[] args) {

        PersonMapper personMapper = new PersonMapper();

// Создание нового объекта Person
        Person person = new Person("Иванов", "Иван", 1);

// Вставка объекта в базу данных
        personMapper.insert(person);

// Обновление объекта в базе данных
        person.setNumberOfDependents(3);
        personMapper.update(person);

// Удаление объекта из базы данных
        personMapper.delete(person);

// Поиск объекта по идентификатору
        Person foundPerson = personMapper.findByNumberOfDependens(1);
    }
}