package com.javarush.test.level05.lesson05.task02;

/* Реализовать метод fight
Реализовать метод boolean fight(Cat anotherCat):
реализовать механизм драки котов в зависимости от их веса, возраста и силы.
Зависимость придумать самому. Метод должен определять, выиграли ли мы (this) бой или нет,
т.е. возвращать true, если выиграли и false - если нет.
Должно выполняться условие:
если cat1.fight(cat2) = true , то cat2.fight(cat1) = false
*/

public class Cat
{
    public String name;
    public int age;
    public int weight;
    public int strength;

    public Cat()
    {
    }

    public boolean fight(Cat anotherCat)
    {
        //напишите тут ваш код
        if (this.age * this.weight * this.strength > anotherCat.age * anotherCat.weight * anotherCat.strength) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args)
    {
        Cat catVaska = new Cat();
        catVaska.name = "Vaska";
        catVaska.age = 1;
        catVaska.weight = 1;
        catVaska.strength = 1;

        Cat catBarsik = new Cat();
        catVaska.name = "Barsik";
        catVaska.age = 2;
        catVaska.weight = 2;
        catVaska.strength = 2;

        System.out.println(catVaska.fight(catBarsik));
        System.out.println(catBarsik.fight(catVaska));
    }
}
