#language: ru

Функция: Получение пользователя пользователем без прав администратора, у которого есть Api-ключ

  Предыстория:
    Пусть В системе есть пользователь "ПОЛЬЗОВАТЕЛЬ_НЕ_АДМИНИСТРАТОР" с параметрами:
      | Api-ключ | API |
    И Создан api-клиент "АПИ_КЛИЕНТ" для пользователя "ПОЛЬЗОВАТЕЛЬ_НЕ_АДМИНИСТРАТОР"

  @api
  Сценарий: Получение пользователя пользователем без прав админа, у которого есть Api-ключ
    Затем Выполнение GET запроса через "АПИ_КЛИЕНТ" пользователем "ПОЛЬЗОВАТЕЛЬ_НЕ_АДМИНИСТРАТОР" на эндпоинт "/users/%d.json" и получение ответа "ОТВЕТ" от сервера
    Затем Проверка, что статус код из ответа "ОТВЕТ" = "200"
    Затем Проверка отсутствия у пользователя "ПОЛЬЗОВАТЕЛЬ_НЕ_АДМИНИСТРАТОР" прав администратора
    Затем Проверка формата Api-ключа у пользователя "ПОЛЬЗОВАТЕЛЬ_НЕ_АДМИНИСТРАТОР" шестнадцатеричный формат "^[0-9a-fA-F]*$"