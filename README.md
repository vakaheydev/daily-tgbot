# Телеграм бот для управления задачами

Это телеграм бот для управления задачами (приложение для списка дел). Он управлять задачами и получать уведомления.

## Особенности

- **Управление задачами**: Добавление, редактирование, просмотр и удаление задач.
- **Планирование задач**: Привязка сроков и напоминаний к задачам.
- **Уведомление**: Получение и отправка пользователям уведомлений.
- **Клиент**: Для управления данными, телеграм бот взаимодействует с [REST API](https://github.com/vakaheydev/daily-rest-api) с помощью [Клиента](https://github.com/vakaheydev/daily-rest-client)

## Установка

Проект контейнеризован с использованием Docker, что позволяет быстро и удобно осуществить запуск всех сервисов (REST API, MVC, Telegram-бот) локально.

## API Endpoints

Вы можете взаимодействовать с REST API через представленные ниже эндпоинты.  
Корневой URL: /api

### Требования

- Docker
- Docker Compose

### Запуск приложения

Чтобы запустить все сервисы (REST API, клиент, MVC и Telegram-бот) следуйте инструкциям из [REST API](https://github.com/vakaheydev/daily-rest-api/blob/master/README.md#%D0%B7%D0%B0%D0%BF%D1%83%D1%81%D0%BA-%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D1%8F)
