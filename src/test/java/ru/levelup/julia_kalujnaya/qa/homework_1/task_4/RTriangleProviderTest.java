package ru.levelup.julia_kalujnaya.qa.homework_1.task_4;

import org.junit.*;

import java.util.Arrays;

/**
 * Домашнее задание. Часть 1. Задание 4 (2-й вариант)
 * Реализован тест с использованием JUnit 4, проверяющий, действительно ли метод getRTriangle() класса RTriangleProvider
 * возвращает прямоугольный треугольник. Первый тест вычисляет длины сторон полученного треугольника и проверяет выполнение
 * для них теоремы Пифагора, второй - равенство 0 скалярных произведений векторов, образованных сторонами треугольника
 *
 * @author Юлия Калюжная
 */
public class RTriangleProviderTest {
    private RTriangle triangle;

    @Before
    public void initTest() {
        triangle = RTriangleProvider.getRTriangle();
    }

    @After
    public void afterTest() {
        triangle = null;
    }

    @Test
    public void testGetRTriangle_PythagorasTest() throws Exception {
        double side1 = getIntervalLength(triangle.getApexX1(), triangle.getApexX2(), triangle.getApexY1(), triangle.getApexY2());
        double side2 = getIntervalLength(triangle.getApexX2(), triangle.getApexX3(), triangle.getApexY2(), triangle.getApexY3());
        double side3 = getIntervalLength(triangle.getApexX1(), triangle.getApexX3(), triangle.getApexY1(), triangle.getApexY3());
        Assert.assertTrue(checkPythagorasTheorem(side1, side2, side3));
    }

    @Test
    public void testGetRTriangle_ScalarProductTest() throws Exception {
        int[] vector1 = getVectorCoordinates(triangle.getApexX1(), triangle.getApexX2(), triangle.getApexY1(), triangle.getApexY2());
        int[] vector2 = getVectorCoordinates(triangle.getApexX2(), triangle.getApexX3(), triangle.getApexY2(), triangle.getApexY3());
        int[] vector3 = getVectorCoordinates(triangle.getApexX1(), triangle.getApexX3(), triangle.getApexY1(), triangle.getApexY3());
        Assert.assertTrue(checkScalarProduct(vector1, vector2, vector3));
    }

    /**
     * Вычисляет длину отрезка по координатам его концов
     * @param x1 - абсцисса 1-й точки
     * @param x2 - абсцисса 2-й точки
     * @param y1 - ордината 1-й точки
     * @param y2 - ордината 2-й точки
     * @return Возвращает длину отрезка
     */
    private double getIntervalLength(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /**
     * Проверяет выполнение теоремы Пифагора для треугольника с заданными длинами сторон
     * @param s1 - длина 1-й стороны треугольника
     * @param s2 - длина 2-й стороны треугольника
     * @param s3 - длина 3-й стороны треугольника
     * @return Возвращает true, если стороны треугольника удовлетворяют теореме Пифагора
     */
    private boolean checkPythagorasTheorem(double s1, double s2, double s3) {
        if ((s1 <= 0) || (s2 <= 0) || (s3 <= 0))
            return false;
        double[] array = new double[] {s1, s2, s3};
        Arrays.sort(array);
        return (array[2] == Math.sqrt(Math.pow(array[0], 2) + Math.pow(array[1], 2)));
    }

    /**
     * Возвращает координаты вектора по координатам его концов
     * @param x1 - абсцисса начала вектора
     * @param x2 - абсцисса конца вектора
     * @param y1 - ордината начала вектора
     * @param y2 - ордината конца вектора
     * @return Возвращает координаты вектора
     */
    private int[] getVectorCoordinates(int x1, int x2, int y1, int y2) {
        return new int[] {x2 - x1, y2 - y1};
    }

    /**
     * Вычисляет скалярное произведение двух векторов, заданных в виде массива из 2 координат
     * @param vector1 - первый вектор
     * @param vector2 - второй вектор
     * @return Возвращает скалярное произведение векторов
     */
    private int getScalarProduct(int[] vector1, int[] vector2) {
        return vector1[0]*vector2[0] + vector1[1]*vector2[1];
    }

    /**
     * Проверяет для трёх заданных векторов, равно ли 0 скалярное произведение каких-либо двух из них
     * @param vector1 - первый вектор
     * @param vector2 - второй вектор
     * @param vector3 - третий вектор
     * @return Возвращает true, если скалярное произведение каких-либо двух векторов равно 0
     */
    private boolean checkScalarProduct(int[] vector1, int[] vector2, int[] vector3) {
        return ((getScalarProduct(vector1, vector2) == 0) || (getScalarProduct(vector2, vector3) == 0) || (getScalarProduct(vector1, vector3) == 0));
    }
}