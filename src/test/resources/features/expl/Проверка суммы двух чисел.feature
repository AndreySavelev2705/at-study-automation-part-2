# language: ru

Функция: Тестирование операций с числами

  #Сценарий: Тестирование суммы двух чисел - 4 и 6
  #  Дано Я складываю 4 и 6
  #  Тогда В результате я получаю 10

  #Сценарий: Тестирование суммы двух чисел - 7 и 8
  #  Дано Я складываю 7 и 8
  #  Тогда В результате я получаю 15

  #Сценарий: Тестирование разности двух чисел - 15 и 8
  #  Пусть Я вычитаю из числа 15 число 8
  #  Тогда В результате я получаю 7


  Структура сценария: Тестирование двух чисел
    Пусть Я складываю <Слагаемое1> и <Слагаемое2>
    Тогда В результате я получаю <Ожидаемый результат>

    Примеры:
      | Слагаемое1 | Слагаемое2 | Ожидаемый результат |
      | 4          | 6          | 10                  |
      | 7          | 8          | 15                  |
      | 10         | 10         | 20                  |