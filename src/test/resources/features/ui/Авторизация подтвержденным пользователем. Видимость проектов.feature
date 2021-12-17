#language: ru

Функция: Авторизация подтвержденным пользователем. Видимость проектов

  Предыстория:
    Пусть В системе есть пользователь "НЕ_АДМИНИСТРАТОР" с параметрами:
      | Администратор | false    |
      | Статус        | Активен |

    Также В системе существует набор ролей - "РОЛИ" с разрешениями:
      | Просмотр задач | VIEW_ISSUES |

    Также Существует проект "ПРОЕКТ_1"
    Также Существует приватный проект "ПРОЕКТ_2"
    Также Существует приватный проект "ПРОЕКТ_3"
    Также У пользователя "НЕ_АДМИНИСТРАТОР" с набором ролей "РОЛИ" есть доступ только к проекту "ПРОЕКТ_3"
    И Открыт браузер на странице " "

  @ui
  Сценарий: Авторизация подтвержденным пользователем. Видимость проектов
    Если Нажать на кнопку "Войти"
    И Авторизоваться как пользователь "НЕ_АДМИНИСТРАТОР"
    Тогда Текст элемента Домашняя страница - "Домашняя страница"
    Затем На главной странице нажать "Проекты"
    Тогда Текст элемента Проекты - "Проекты"
    И На странице отображается проект "ПРОЕКТ_1"
    И Имя проекта совпадает с именем проекта "ПРОЕКТ_1"
    И Описание проекта совпадает с описанием проекта "ПРОЕКТ_1"
    И На странице не отображается проект "ПРОЕКТ_2"
    И На странице отображается проект "ПРОЕКТ_3"
    И Имя проекта совпадает с именем проекта "ПРОЕКТ_3"
    И Описание проекта совпадает с описанием проекта "ПРОЕКТ_3"
