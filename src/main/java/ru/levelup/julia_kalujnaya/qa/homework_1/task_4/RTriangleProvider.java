package ru.levelup.julia_kalujnaya.qa.homework_1.task_4;

public final class RTriangleProvider {
    /**
     * Заглушка: для тестирования методы возвращают конкретные координаты
     * @return Возвращает прямоугольный треугольник
     */
    public static RTriangle getRTriangle() {
        return new RTriangle() {
            public int getApexX1() {
                return 0;
            }

            public int getApexY1() {
                return 0;
            }

            public int getApexX2() {
                return 0;
            }

            public int getApexY2() {
                return 1;
            }

            public int getApexX3() {
                return 1;
            }

            public int getApexY3() {
                return 0;
            }
        };
    }
}