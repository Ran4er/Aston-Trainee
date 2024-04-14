> [!note] Определение паттернов проектирования
> Паттернами проектирования (Design Patterns) называют решения часто встречающихся проблем в области разработки программного обеспечения.

Паттерны делятся на три группы:
1. [[GOF#Порождающие паттерны|Порождающие паттерны]]
	1. [[GOF#Абстрактная фабрика|Абстрактная фабрика (Abstract Factory)]]
	2. [[GOF#Строитель (Builder)|Строитель (Builder)]]
	3. [[GOF#Фабричный метод (Factory Method)|Фабричный метод (Factory Method)]]
	4. [[GOF#Прототип (Prototype)|Прототип (Prototype)]]
	5. [[GOF#Одиночка (Singleton)|Одиночка (Singleton)]]
2. [[GOF#Структурные паттерны|Структурные паттерны]]
	1. [[GOF#Адаптер (Adapter)|Адаптер (Adapter)]]
	2. [[GOF#Мост (Bridge)|Мост (Bridge)]]
	3. [[GOF#Компоновщик (Composite)|Компоновщик (Decorator)]]
	4. [[GOF#Фасад (Facade)|Фасад (Facade)]]
	5. Приспособленец (Flyweight)
	6. Заместитель (Proxy)
3. Поведенческие паттерны
	1. Цепочка обязанностей (Chain of responsibility)
	2. Команда (Command)
	3. Интерпретатор (Interpreter)
	4. Итератор (Iterator)
	5. Посредник (Mediator)
	6. Хранитель (Memento)
	7. Наблюдатель (Observer)
	8. Состояние (Strategy)
	9. Шаблонный метод (Template method)
	10. Посетитель (Visitor)

> [!note] Определение поведенческих паттернов
> __Поведенческие паттерны__ - они определяют алгоритмы и взаимодействие между классами и объектами, то есть их поведение.

### Порождающие паттерны
> [!note] Определение порождающего паттерна
> __Порождающие паттерны__ - это паттерны, которые абстрагируют процесс инстанцирования или, иными словами, процесс порождения классов и объектов.
#### Абстрактная фабрика (Abstract Factory)
> [!info] Назначение
> Абстрактная фабрика предоставляет интерфейс для создания семейства взаимосвязанных или родственных объектов (dependent or related objects), не специфицируя их конкретных классов. <br>
> Другими словами: абстрактная фабрика представляет собой стратегию создания семейства взаимосвязанных или родственных объектов.

> [!tip] Когда следует использовать
> - Когда система не должна зависеть от способа создания новых объектов.
> - Когда создаваемые объекты должны использоваться вместе и являются взаимосвязанными.

__UML схема паттерна Абстрактная фабрика__
![[Pasted image 20240412132012.png]]<br>
__Схема примера использования паттерна "абстрактная фабрика" из реальной жизни__:
![[Pasted image 20240412132254.png]]

__Реализация шаблона "Абстрактная фабрика" на Java__:
```Java
interface AbstractFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}

class ConcreteFactory1 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}

class ConcreteFactory2 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}

abstract class AbstractProductA {}

abstract class AbstractProductB {
    abstract void interact(AbstractProductA a);
}

class ProductA1 extends AbstractProductA {}

class ProductB1 extends AbstractProductB {
    @Override
    void interact(AbstractProductA a) {
        System.out.println(this.getClass().getSimpleName() + " interacts with " + a.getClass().getSimpleName());
    }
}

class ProductA2 extends AbstractProductA {}

class ProductB2 extends AbstractProductB {
    @Override
    void interact(AbstractProductA a) {
        System.out.println(this.getClass().getSimpleName() + " interacts with " + a.getClass().getSimpleName());
    }
}

class Client {
    private AbstractProductA abstractProductA;
    private AbstractProductB abstractProductB;

    public Client(AbstractFactory factory) {
        abstractProductB = factory.createProductB();
        abstractProductA = factory.createProductA();
    }

    public void run() {
        abstractProductB.interact(abstractProductA);
    }
}

public class Main {
    public static void main(String[] args) {
        // Abstract factory #1
        AbstractFactory factory1 = new ConcreteFactory1();
        Client client1 = new Client(factory1);
        client1.run();

        // Abstract factory #2
        AbstractFactory factory2 = new ConcreteFactory2();
        Client client2 = new Client(factory2);
        client2.run();
    }
}
```

#### Строитель (Builder)
> [!info] Назначение
> Строитель отделяет конструирование сложного объекта то его представления, так что в результате одного и того же процесса конструирования могут получаться разные представления.

> [!tip] Когда следует использовать
>- Когда процесс создания нового объекта не должен зависеть от того, из каких частей этот объект состоит и как эти части связаны между собой.
>- Когда необходимо обеспечить получение различных вариаций объекта в процессе его создания.

Стоит отметить, что паттерн "Строитель" довольно часто применяется в современных приложениях, но не в том виде, в котором он был описан "бандой четырех". На практике же строитель все так же отвечает за создание объектов, но гораздо реже обладает всеми изначальным свойствами. 

__UML схема паттерна строитель__
![[Pasted image 20240412133227.png]]<br>
Участники:
1. __Builder__ определяет интерфейс конструирования продукта по частям;
2. __Director__ управляет процессом создания, не зная, какой продукт будет создан в результате;
3. __ConcreteBuilder__ - конкретный строитель, который создает только известный ему объект класса __Product__.

__Схема примера использования паттерна "builder" из реальной жизни__:
![[Pasted image 20240412133512.png]]<br>
__Реализация шаблона "строитель" на Java__:
```Java
import java.util.ArrayList;

class Roof {
    public Roof() {
        System.out.println("Крыша построена");
    }
}

class Basement {
    public Basement() {
        System.out.println("Подвал построен");
    }
}

class House {
    ArrayList<Object> parts = new ArrayList<>();

    public void addPart(Object part) {
        parts.add(part);
    }
}

class Storey {
    public Storey() {
        System.out.println("Этаж построен");
    }
}

abstract class Builder {
    public abstract void buildBasement();
    public abstract void buildStorey();
    public abstract void buildRoof();
    public abstract House getResult();
}

class ConcreteBuilder extends Builder {
    House house = new House();

    @Override
    public void buildBasement() {
        house.addPart(new Basement());
    }

    @Override
    public void buildStorey() {
        house.addPart(new Storey());
    }

    @Override
    public void buildRoof() {
        house.addPart(new Roof());
    }

    @Override
    public House getResult() {
        return house;
    }
}

public class Main {
    public static void main(String[] args) {
        ConcreteBuilder builder = new ConcreteBuilder();
        builder.buildBasement();
        builder.buildStorey();
        builder.buildRoof();
        House house = builder.getResult();
    }
}
```

__Output__:
```Output
Подвал построен
Этаж построен
Крыша построена
```

#### Фабричный метод (Factory Method)
> [!info] Назначение
> Определяет интерфейс для создания объекта, но оставляет подклассам решение о том, какой класс инстанцировать. Фабричный метод позволяет классу делегировать инстанцирование подклассам.

> [!tip] Когда следует использовать
> - Когда заранее неизвестно, объекты каких типов необходимо создавать;
> - Когда система должна быть независимой от процесса создания новых объектов и расширяемой: в нее можно легко вводить новые классы, объекты которых система должна создавать;
> - Когда создание новых объектов необходимо делегировать из базового класса классам наследникам.

__Схема примера использования паттерна "Фабричный метод" из реальной жизни__:
![[Pasted image 20240412134304.png]]<br>
__UML схема паттерна фабричный метод__:
![[Pasted image 20240412134329.png]]<br>
__Реализация шаблона "фабричный метод" на Java__:
```Java
public class MainApp {

    public static void main(String[] args) {
        // Массив создателей
        Creator[] creators = new Creator[2];

        creators[0] = new ConcreteCreatorA();
        creators[1] = new ConcreteCreatorB();

        // Перебор создателей и создание продуктов
        for (Creator creator : creators) {
            Product product = creator.factoryMethod();
            System.out.println("Created " + product.getClass().getSimpleName());
        }

        // Ожидание ввода пользователя
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

abstract class Product {}

class ConcreteProductA extends Product {}

class ConcreteProductB extends Product {}

abstract class Creator {
    public abstract Product factoryMethod();
}

class ConcreteCreatorA extends Creator {
    @Override
    public Product factoryMethod() {
        return new ConcreteProductA();
    }
}

class ConcreteCreatorB extends Creator {
    @Override
    public Product factoryMethod() {
        return new ConcreteProductB();
    }
}
```

__Обсуждение паттерна "Фабричный метод"__:
Стоит сказать, что у каждой реализации фабричного метода есть свои особенности:
1. __Классический фабричный метод__ является частным случаем шаблонного метода. Это значит, что фабричный метод привязан к текущей иерархии типов и не может быть использован повторно в другом контексте.
2. __Полиморфный фабричный метод__ является стратегией создания экземпляров некоторого семейства типов, что позволяет использовать одну фабрику в разных контекстах. Тип создаваемого объекта определяется типом фабрики и обычно не зависит от аргументов фабричного метода.
3. __Статистический фабричный метод__ является самой простой формой фабричного метода. Статический метод создания позволяет обойти ограничения конструкторов. Например, тип создаваемого объекта может зависеть от аргументов метода, экземпляр может возвращаться из кэша, а не создаваться заново или же фабричный метод может быть асинхронным.

__Конструктор vs. фабричный метод__
В объектно-ориентированных языках программирования конструктор отвечает за корректную инициализацию создаваемого объекта. В большинстве случаев они прекрасно справляются со своей задачей, но иногда лучше воспользоваться статическим фабричным методом.
	__Именованные конструкторы.__
	В языке Java имя конструктора совпадает с именем класса, что делает невозможным использование двух конструкторов с одним набором и типом параметров. Хорошим примером такого ограничения может стать реализация Timespan, которая представляет собой интервал времени. Очень удобно создавать интервал времени по кол-ву секунд, минут, часов и дней, но сделать несколько конструкторов, каждый из которых принимает один параметр типа double, невозможно. Для этого класс Timespan содежит набор фабричных методов:
	
```Java
public class TimeSpan {

    private double ticks;

    private TimeSpan(double ticks) {
        this.ticks = ticks;
    }

    public static TimeSpan fromMilliseconds(double value) {
        return new TimeSpan(value);
    }

    public static TimeSpan fromSeconds(double value) {
        return new TimeSpan(value * 1000);
    }

    public static TimeSpan fromMinutes(double value) {
        return new TimeSpan(value * 60 * 1000);
    }

    public double getTicks() {
        return ticks;
    }
}

public class Main {
    public static void main(String[] args) {
        TimeSpan time1 = TimeSpan.fromMilliseconds(1000);
        TimeSpan time2 = TimeSpan.fromSeconds(60);
        TimeSpan time3 = TimeSpan.fromMinutes(2);

        System.out.println("Time 1 ticks: " + time1.getTicks());
        System.out.println("Time 2 ticks: " + time2.getTicks());
        System.out.println("Time 3 ticks: " + time3.getTicks());
    }
}
```

__Тяжеловесный процесс создания__
Конструктор отвечает за корректную инициализацию объекта, после которой объект должен быть готов для использования своими клиентами. Обычно логика инициализации относительно простая и должна выполняться конструктором, но слишком тяжеловесную логику лучше вынести из конструктора в статический фабричный метод.

__Примеры в Java__


#### Прототип (Prototype)
>[!info] Назначение
>__Прототип__ - позволяет создавать новые объекты путем клонирования уже существующих. По сути данный паттерн предлагает технику клонирования объектов.

>[!tip] Когда следует использовать
> - Класс порождаемого объекта определяется в момент выполнения.
> - Когда желательно избежать наследования создателя объекта. В этом случае, Прототип является конкурентом Абстрактной фабрики.
> - Когда клонирование объекта является более предпочтительным вариантом нежели его создание и инициализация с помощью конструктора.
> - Когда создание копии объекта проще и быстрее, чем порождение его стандартным путем, используя операцию new и включая инициализацию полей.

__UML схема паттерна Прототип__:
![[Pasted image 20240412163402.png]] <br>
__Реализация порождающего шаблона "прототип" на Java__:
```Java
class Client {
    void operation() {
        Prototype prototype = new ConcretePrototype1(1);
        Prototype clone = prototype.clone();
        prototype = new ConcretePrototype2(2);
        clone = prototype.clone();
    }
}

abstract class Prototype implements Cloneable {
    private int id;

    public Prototype(int id) {
        this.id = id;
    }

    public abstract Prototype clone();

    public int getId() {
        return id;
    }
}

class ConcretePrototype1 extends Prototype {
    public ConcretePrototype1(int id) {
        super(id);
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype1(getId());
    }
}

class ConcretePrototype2 extends Prototype {
    public ConcretePrototype2(int id) {
        super(id);
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype2(getId());
    }
}
```

__Участники__
- __Prototype__: определяет интерфейс для клонирования самого себя, который, как правило, представляет метод clone()
- __ConcretePrototype1 и ConcretePrototype2__: конкретные реализации прототипа. Реализуют метод clone()
- __Client__: создает объекты прототипов с помощью метода clone()

В Java обычно используется полное клонирование (deep cloning) и неполное клонирование (shallow cloning), но это зависит от специфики задачи и требований к копированию объектов.
1. **Полное клонирование (Deep cloning)**:
    - При полном клонировании создается копия объекта, а также копии всех объектов, на которые он ссылается.
    - Это особенно важно, когда у вас есть сложная структура объектов, и вы хотите создать полностью независимую копию всей структуры.
    - В Java для выполнения полного клонирования обычно используется рекурсивное копирование всех объектов, на которые ссылаются клонируемые объекты.
    - Для реализации полного клонирования в Java можно использовать метод `clone()` в сочетании с глубоким копированием объектов.
2. **Неполное клонирование (Shallow cloning)**:
    - При неполном клонировании создается копия объекта, но объекты, на которые он ссылается, не копируются, а просто ссылки на них копируются.
    - Это означает, что изменения внутри клонированного объекта могут повлиять на оригинальный объект, если он совместно использует ссылки на другие объекты.
    - В Java неполное клонирование можно выполнить, например, с помощью метода `clone()`, но это будет выполнять только поверхностное копирование объектов, а не их содержимого.

#### Одиночка (Singleton)
![[Pasted image 20240412221957.png]]<br>
>[!info] Назначение
>Гарантирует, что у класса есть только один экземпляр, и предоставляет глобальную точку доступа к нему.

>[!tip] Когда следует использовать
>Паттерн Синглтон обычно используется для обеспечения доступа к глобальным переменным или ограниченным по количеству экземплярам объектам. Этот паттерн гарантирует, что у класса есть только один экземпляр. Хотя в некоторых случаях концепция предметной области строго предполагает существование только одного экземпляра класса, на практике Синглтон часто используется для обеспечения доступа к ресурсам, необходимым разным частям приложения.

__UML схема паттерна "синглтон":__
![[Pasted image 20240412222541.png]]<br>
__Реализация шаблона "синглтон" на Java__:
```Java
public final class Singleton {
    private static volatile Singleton instance;
    private static final Object syncRoot = new Object();

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (syncRoot) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

__Singleton vs. Static Class__
Альтернативой паттерну «Синглтон» в Java является использование класса с исключительно статическими членами. Синглтон обладает большей гибкостью, но статические функции проще использовать. При отсутствии состояния и наличии небольшого числа операций статические методы являются более подходящим решением. Если же глобальный объект обладает состоянием, то реализация на основе паттерна «Синглтон» будет проще. Существует компромиссное решение: статический класс с небольшим набором методов может выполнять роль фасада над реализацией на основе синглтона. `ThreadPool.QueueUserWorkItem` является хорошим примером такого подхода.

__Применимость: паттерн или антипаттерн__
1. __Синглтон без видимого состояния__ -  Нет ничего критического использовании синглтона, через который можно получить доступ к стабильной справочной информации или некоторым утилитам.
2. __Настраиваемый контекст.__ Аналогично нет ничего критического в протаскивании инфраструктурных зависимостей в виде Ambient Context, то есть в использовании синглтона, возвращающего абстрактный класс или интерфейс, который можно установить в начале приложения или при инициализации юнит-теста. 
3. __Минимальная область использования.__ Ограничьте использование синглтона минимальным числом классов/модулей. Чем меньше у синглтона прямых пользователей, тем легче будет от него избавиться и перейти на более продуманную модель управления зависимостями. Помните, что чем больше у классов пользователей, тем сложнее его изменить. Если уж вы вынуждены использовать синглтон, возвращающий безнес-объект, то пусть лишь несколько высокоуровневых классов-медиаторов используют синглтон напрямую и передают его экземпляр в качестве зависимостей классам более низкого уровня.
4. __Сделайте использование синглтона явным.__ Если передать зависимость через аргументы конструктора не удается, то сделайте использование синглтона явным. Вместо обращения к синглтону из нескольких методов сделайте статическую переменную и проинициализируйте ее экземпляром синглтона.

### Структурные паттерны

> [!note] Определение структурного паттерна
> __Структурные паттерны__ - рассматривает, как классы и объекты образуют более крупные структуры - более сложные по характеру классы и объекты.

#### Адаптер (Adapter)

>[!info] Назначение
>Преобразует интерфейс одного класса в интерфейс другого, который ожидают клиенты. Адаптер делает возможной совместную работу классов с несовместными интерфейсами.

>[!tip] Когда следует использовать
>- Когда необходимо использовать имеющийся класс, но его интерфейс не соответствует потребностям бизнес логики.
>- Когда надо использовать уже существующий класс совместно с другими классами, интерфейсы которых несовместимы.

__UML схема паттерна адаптер:__
![[Pasted image 20240413190445.png]]<br>
__Участники__
- __Target__: представляет объекты, которые используются клиентом.
- __Client__: использует объекты Target для реализации своих задач.
- __Adaptee__: представляет адаптируемый класс, который мы хотели бы использовать у клиента вместо объектов Target.
- __Adapter__: собственно адаптер, который позволяет работать с объектами Adaptee как с объектами Target.

__Реализация паттерна адаптер на Java__:
```Java
public class Main {

    public static void main(String[] args) {
        // Создаем адаптер и отправляем запрос
        Target target = new Adapter();
        target.request();
    }
}

// Целевой класс
class Target {
    public void request() {
        System.out.println("Called Target Request()");
    }
}

// Адаптер
class Adapter extends Target {
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        // Возможно, выполнение другой работы
        // и затем вызов SpecificRequest
        adaptee.specificRequest();
    }
}

// Адаптируемый класс
class Adaptee {
    public void specificRequest() {
        System.out.println("Called SpecificRequest()");
    }
}
```

__Output__:
```Output
Called SpecificRequest()
```

__Обсуждение паттерна "Адаптер"__
Адаптер классов и объектов "Бандой четырех" были описаны два вида адаптеров - адаптеры классов и адаптеры объектов. Ранее был рассмотрен пример адаптера объектов. В этому случае создается новый класс, который реализует требуемый интерфейс и делегирует всю работу адаптируемому объекту, хранящемуся в виде закрытого поля.

__Применимость__
Адаптер позволяет использовать существующие типы в новом контексте
1. __Повторное использование чужого кода__. В некоторых случаях у нас уже есть код, который решает нужную задачу, но его интерфейс не подходит для текущего приложения. Вместо изменения кода библиотеки можно создать слой адаптеров.
2. __Адаптивный рефакторинг__. Адаптеры позволяют плавно изменять существующую функциональность путем выделения нового "правильного" интерфейса, но с использованием старой проверенной функциональности.

#### Мост (Bridge)

>[!info] Назначение
> Мост (Bridge) - структурный шаблон-проектирования, который позволяет отделить абстракцию от реализации таким образом, чтобы и абстракцию, и реализацию можно было изменять независимо друг от друга.

> [!tip] Когда следует использовать
> - Когда надо избежать постоянной привязки абстракции к реализации
> - Когда наряду с реализацией надо изменять и абстракцию независимо друг от друга. То есть изменения в абстракции не должно привести к изменениям в реализации.

__UML схема паттерна мост:__
![[Pasted image 20240413191909.png]]<br>
__Реализация шаблона на Java__:
```Java
public class Client {
    public static void main(String[] args) {
        Abstraction abstraction = new RefinedAbstraction(new ConcreteImplementorA());
        abstraction.operation();
        abstraction.setImplementor(new ConcreteImplementorB());
        abstraction.operation();
    }
}

abstract class Abstraction {
    protected Implementor implementor;

    public void setImplementor(Implementor imp) {
        implementor = imp;
    }

    public Abstraction(Implementor imp) {
        implementor = imp;
    }

    public void operation() {
        implementor.operationImp();
    }
}

abstract class Implementor {
    public abstract void operationImp();
}

class RefinedAbstraction extends Abstraction {
    public RefinedAbstraction(Implementor imp) {
        super(imp);
    }

    @Override
    public void operation() {
        // additional operations, if any
    }
}

class ConcreteImplementorA extends Implementor {
    @Override
    public void operationImp() {
        // implementation
    }
}

class ConcreteImplementorB extends Implementor {
    @Override
    public void operationImp() {
        // implementation
    }
}

```

__Участники__
- __Abstraction__: определяет базовый интерфейс и хранит ссылку на объект Implementor. Выполнение операций в Abstraction делегируется методам объекта Implementor
- __RefinedAbstraction__: уточненная абстракция, наследуется от Abstraction и расширяет унаследованный интерфейс
- __Implementor__: определяет базовый интерфейс для конкретных реализаций. Как правило, Implementor определяет только примитивные операции. Более сложные операции, которые базируются на примитивных, определяются в Abstraction
- __ConcreteImplementorA и ConcreteImplementorB__: конкретные реализации, которые унаследованы от Implementor
- __Client__: использует объекты Abstraction

__Схема примера использования паттерна мост из реальной жизни__:
![[Pasted image 20240413192549.png]] <br>
__Применимость паттерна мост__
- Когда вы хотите разделить монолитный класс, который содержит несколько различных реализаций какой-то функциональности (например, может работать с разными системами баз данных).
	- Чем больше класс , тем тяжелее разобраться в его коде, тем больше это затягивает разработку. Кроме того, изменения, вносимые в одну из реализаций, приводят к редактированию всего класса, что может привести к ошибкам.
	- Bridge позволяет разделить монолитный класс на несколько отдельных иерархий. После этого вы можете менять их код независимо друг от друга. Это упрощает работу над кодом и уменьшает вероятность внесения ошибок.
- Когда вы хотите, чтобы реализацию можно было бы изменять во время выполнения программы.

#### Компоновщик (Composite)

> [!info] Назначение
> Компонует объекты в древовидные структуры для предоставления иерархий "часть-целое". Позволяет клиентам единообразно трактовать индивидуальные и составные объекты.

> [!tip] Когда следует использовать
> - Когда объекты должны быть реализованы в виде иерархической древовидной структуры.
> - Когда клиенты единообразно должны управлять как целыми объектами, так и их составными частями. То есть целое и его части должны реализовать один и тот же интерфейс.

__Применимость__
Компоновщик - это относительно низкоуровневый паттерн проектирования, который лежит в основе других паттернов. Команды объединяются в составные команды, декоратор является составным объектом с одним дочерним элементом, посетитель очень часто обходит составные объекты иерархической формы.

__UML схема паттерна компоновщик__
![[Pasted image 20240413201137.png]] <br>
__Реализация шаблона компоновщик на Java__:
```Java
import java.util.ArrayList;
import java.util.List;

public class Client {
    public void main() {
        Component root = new Composite("Root");
        Component leaf = new Leaf("Leaf");
        Component subtree = new Composite("Subtree");
        root.add(leaf);
        root.add(subtree);
        root.display();
    }
}

abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public abstract void display();
    public abstract void add(Component c);
    public abstract void remove(Component c);
}

class Composite extends Component {
    private List<Component> children = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        children.add(component);
    }

    @Override
    public void remove(Component component) {
        children.remove(component);
    }

    @Override
    public void display() {
        System.out.println(name);

        for (Component component : children) {
            component.display();
        }
    }
}

class Leaf extends Component {
    public Leaf(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println(name);
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }
}
```

__Участники__
- __Component__: определяет интерфейс для всех компонентов в древовидной структуре.
- __Composite__: представляет компонент, который может содержать другие компоненты и реализует механизм для их добавления и удаления.
- __Leaf__: представляет отдельный компонент, который не может содержать другие компоненты
- __Client__: клиент, который использует компоненты

#### Декоратор (Decorator)

>[!info] Назначение
>Динамически добавляет объекту новые обязанности. Является гибкой альтернативой порождению подклассов с целью расширения функциональности.

> [!tip] Когда следует использовать
> - Когда надо динамически добавлять к объекту новые функциональные возможности. При этом данные возможности могут быть сняты с объекта.
> - Когда применение наследования неприемлемо

__UML схема паттерна Decorator:__
![[Pasted image 20240413201804.png]] <br>
__Участники__
- __Component__: абстрактный класс, который определяет интерфейс для наследуемых объектов.
- __ConcreteComponen__: конкретная реализация компонента, в которую с помощью декоратора добавляется новая функциональность.
- __Decorator__: собственно декоратор, реализуется в виде абстрактного класса и имеет тот же базовый класс, что и декорируемые объекты. Поэтому базовый класс Component должен быть по возможности легким и определять только базовый интерфейс. 
  
  Класс декоратора также хранит ссылку на декорируемый объект в виде объекта базового класса Component и реализует связь с базовым классом как через наследование, так и через отношение агрегации.
- __Классы ConcreteDecoratorA и ConcreteDecoratorB__: представляют дополнительные функциональности, которыми должен быть расширен объект ConcreteComponent.

__Реализация шаблона декоратор на Java__:
```Java
abstract class Component {
    public abstract void operation();
}

class ConcreteComponent extends Component {
    @Override
    public void operation() {
        // Реализация операции
    }
}

abstract class Decorator extends Component {
    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        if (component != null) {
            component.operation();
        }
    }
}

class ConcreteDecoratorA extends Decorator {
    @Override
    public void operation() {
        super.operation();
        // Дополнительная функциональность для ConcreteDecoratorA
    }
}

class ConcreteDecoratorB extends Decorator {
    @Override
    public void operation() {
        super.operation();
        // Дополнительная функциональность для ConcreteDecoratorB
    }
}
```

__Недостатки декораторов__
1. __Чувствительность к порядку__. Код инициализации декораторов очень важен, поскольку именно в процессе создания определяются вложенность и порядок исполнения разных декораторов.
2. __Сложность отладки__. Разработчику, незнакомому с этим паттерном, замер времени исполнения или кэширование результатов декораторами может показаться черной магией. Отлаживать проблемы, которые возникли внутри декоратора, может быть довольно сложно.
3. __Увеличение сложности__. Декоратор является довольно тяжеловесным паттерном, к которому стоит прибегать тогда, когда выделяемый аспект поведения достаточно сложен. Ели нужно кэшировать результаты в одном из десяти методов, то сложность, привнесенная декоратором, будет неоправданна.

__Применимость__
Декораторы применяются для добавления всем методам интерфейса некоторого поведения, которое не является частью основной функциональности.
Декораторы отлично подходят для решения следующих задач:
- кэширования результатов работы;
- замера времени исполнения методов;
- логирования аргументов;
- управления доступом пользователей;
- модификации аргументов или результата работы методов упаковки/распаковки, шифрования и т.п.

#### Фасад (Facade)

>[!info] Назначение
> Предоставляет унифицированный интерфейс вместо набора интерфейсов некоторой подсистемы. Фасад определяет интерфейс более высокого уровня, который упрощает использование подсистемы.
> Если коротко: Шаблон Фасад объединяет группу объектов в рамках одного специализированного интерфейса и переадресует вызов его методов к этим объектам.

>[!tip] Когда использовать фасад
>- Когда имеется сложная система, и необходимо упростить с ней работу. Фасад позволит определить одну точку взаимодействия между клиентом и системой.
>- Когда надо уменьшить количество зависимостей между клиентом и сложной системой. Фасадные объекты позволяют отделить, изолировать компоненты системы от клиента и развивать и работать с ними независимо.
>- Когда нужно определить подсистемы компонентов в сложной системе. Создание фасадов для компонентов каждой отдельной подсистемы позволит упростить взаимодействие между ними и повысить их независимость друг от друга.

__Схема примера использования паттерна "Facade" из реальной жизни__:
Фасад определяет унифицированный интерфейс более высокого уровня для подсистемы, которая упрощает ее использование. Представитель службы поддержки клиентов выступает в качестве фасада, предоставляя интерфейс отделу исполнения заказов, отделу биллинга и отделу доставки.
![[Pasted image 20240413231141.png]]<br>
__UML схема паттерна facade__:
![[Pasted image 20240413231209.png]]<br>
__Участники__:
__Facade__
	- Знает, какие классы подсистемы отвечают за запрос.
	- Делегирует запросы клиентов соответствующим объектам подсистемы.

__Классы подсистемы__
	- Реализовывают функционал подсистемы
	- Обрабатывают методы, назначенные объектом Facade.
	- Не знают о фасаде и не ссылаются на него.

__Реализация шаблона на Java__:
```Java
public class Main {
    public static void main(String[] args) {
        Facade facade = new Facade();

        facade.methodA();
        facade.methodB();
    }
}

// The 'Subsystem ClassA' class
class SubSystemOne {
    public void methodOne() {
        System.out.println("SubSystemOne Method");
    }
}

// The 'Subsystem ClassB' class
class SubSystemTwo {
    public void methodTwo() {
        System.out.println("SubSystemTwo Method");
    }
}

// The 'Subsystem ClassC' class
class SubSystemThree {
    public void methodThree() {
        System.out.println("SubSystemThree Method");
    }
}

// The 'Subsystem ClassD' class
class SubSystemFour {
    public void methodFour() {
        System.out.println("SubSystemFour Method");
    }
}

// The 'Facade' class
class Facade {
    private SubSystemOne one;
    private SubSystemTwo two;
    private SubSystemThree three;
    private SubSystemFour four;

    public Facade() {
        one = new SubSystemOne();
        two = new SubSystemTwo();
        three = new SubSystemThree();
        four = new SubSystemFour();
    }

    public void methodA() {
        System.out.println("\nMethodA() ---- ");
        one.methodOne();
        two.methodTwo();
        four.methodFour();
    }

    public void methodB() {
        System.out.println("\nMethodB() ---- ");
        two.methodTwo();
        three.methodThree();
    }
}
```

****
Хочется сказать, что фасады делают базовые сценарии простыми, а сложные сценарии - возможными.

__Инкапсуляция стороннего кода__
Использование фасадов не только упрощает использование библиотек или сторонних компонентов, но и решает ряд насущных проблем.
- Повторное использование кода и лучших практик.
- Переход на новую версию библиотеки.
- Переход с одной библиотеки на другую.

Фасад __не нужно применять__, когда в повышении уровня абстракции нет никакого смысла.

### 