#language: ru

Функция: Вход  подтвержденным пользователем. Проверка отображения домашней страницы, элементов загорловка и элемента "Поиск".

  Предыстория:
    Пусть В системе есть пользователь "НЕ_АДМИНИСТРАТОР" с параметрами:
      | Администратор | false |
    И Открыт браузер на главной странице

  @ui
  Сценарий: Вход   подтвержденным пользователем. Проверка отображения домашней страницы, элементов загорловка и элемента "Поиск"
    Если Нажать на кнопку Войти
    И Авторизоваться как пользователь "НЕ_АДМИНИСТРАТОР"
    Тогда На странице "Домашняя страница" отображается элемент "Домашняя страница"
    Также В заголовке страницы текст элемента Вошли как "НЕ_АДМИНИСТРАТОР" верный логин текущего администратора
    Также На странице "Домашняя страница" отображается элемент "Домашняя страница"
    Также На странице "Заголовок страницы" отображается элемент "Моя страница"
    Также На странице "Заголовок страницы" отображается элемент "Проекты"
    Также На странице "Заголовок страницы" отображается элемент "Помощь"
    Также На странице "Заголовок страницы" отображается элемент "Моя учётная запись"
    Также На странице "Заголовок страницы" отображается элемент "Выйти"
    Также На странице "Заголовок страницы" не отображается элемент "Администрирование"
    Также На странице "Заголовок страницы" не отображается элемент "Войти"
    Также На странице "Заголовок страницы" не отображается элемент "Регистрация"
    Также На странице "Заголовок страницы" отображается элемент "Поиск"