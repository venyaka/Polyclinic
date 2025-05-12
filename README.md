# Система управления пользователями (Пациенты и Врачи)

Веб-приложение для управления пользователями в медицинской системе. Поддерживает создание, редактирование и отображение **пациентов** и **врачей**, а также преобразование пациента во врача.

---

## Технологии

- **Backend**: Spring Boot (MVC, Security)
- **Frontend**: Jakarta Faces (JSF), PrimeFaces, PrimeFlex
- **База данных**: PostgreSQL
- **Сервер**: WildFly / Jakarta EE
- **Аутентификация**: Spring Security (ролевая модель: ADMIN, DOCTOR, PATIENT)

---

## Возможности

- Управление пациентами:
  - Просмотр всех пациентов
  - Добавление и редактирование пациентов
- Управление врачами:
  - Просмотр всех врачей
  - Создание врача из существующего пациента
- Простые и удобные диалоги (модальные окна)
- UI оформлен в стиле Bootstrap (PrimeFlex)

---

## Установка и запуск

### 1. Клонируйте проект:

```bash
git clone https://github.com/your-username/user-management-system.git
cd user-management-system
```

### 2. Настройка базы данных (PostgreSQL):

Создайте базу данных `user_management` и пользователя:

```sql
CREATE DATABASE user_management;
CREATE USER myuser WITH ENCRYPTED PASSWORD 'mypassword';
GRANT ALL PRIVILEGES ON DATABASE user_management TO myuser;
```

### 3. Настройка `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/user_management
spring.datasource.username=myuser
spring.datasource.password=mypassword

spring.jpa.hibernate.ddl-auto=update
```

### 4. Сборка и запуск

#### Вариант 1: Через IDE (IntelliJ, Eclipse)
- Импортируйте как Maven-проект
- Запустите `MainApplication.java`

#### Вариант 2: Через терминал

```bash
./mvnw clean package
java -jar target/user-management.jar
```

---

## Деплой на WildFly

1. Соберите `.war` файл:
   ```bash
   ./mvnw clean package -DskipTests
   ```

2. Скопируйте `.war` в папку `wildfly/standalone/deployments`

---

## Доступ по ролям

| Роль      | Возможности                              |
|-----------|------------------------------------------|
| `ADMIN`   | Управление всеми пользователями          |
| `DOCTOR`  | Просмотр и редактирование пациентов      |
| `PATIENT` | Просмотр личного кабинета                |

---

## Скриншоты

> *Добавьте сюда скриншоты: интерфейс, формы, таблицы...*

---

## Возможности для развития

- Фильтрация пациентов по email врача
- Назначение пациентов врачам
- История изменений и аудит
- Импорт/экспорт в Excel/PDF
- Расширенная авторизация с JWT

---

## Лицензия

Проект открыт под лицензией MIT. Используйте свободно.
