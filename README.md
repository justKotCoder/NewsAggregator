# 📰 News Aggregator — Android RSS Читалка

Это Android-приложение, написанное на **Kotlin + Jetpack Compose**, для чтения RSS-новостей. Приложение парсит RSS-ленты, отображает список новостей в виде карточек и позволяет просматривать детали каждой новости с красивым UI.

---

## Возможности

* Загрузка и отображение новостей из RSS-лент с помощью **Rome**.
* Современный интерфейс с **Jetpack Compose**.
* Pull-to-refresh через `pullRefresh` API.
* Красивые карточки новостей с картинками, авторами и датой.
* Отображение детальной информации в `AlertDialog`.
* Поддержка категорий и дат публикации.
* Использование **Room**, **Hilt**, **Navigation**, **Coroutines** и **Coil**.
* 📱 Готово для дальнейшего масштабирования.

---

## Стек технологий

* **Язык:** Kotlin
* **UI:** Jetpack Compose
* **DI:** Hilt
* **База данных:** Room
* **Навигация:** Navigation Component
* **Изображения:** Coil
* **RSS-парсинг:** Rome
* **Сетевые запросы:** OkHttp
* **Асинхронность:** Kotlin Coroutines
* **Парсинг XML:** xmlutil

---

## Скриншоты

 ![image](https://github.com/user-attachments/assets/045c34f8-6a6c-4ccf-99b7-a6f6bf6a4b45)
 ![image](https://github.com/user-attachments/assets/976cdef0-56e9-47ab-a347-be4b4e84a01d)

---
##  Зависимости (Gradle)

Вот основные библиотеки, которые используются:

```kotlin
implementation("androidx.compose.material:material:1.6.1")
implementation("androidx.compose.material3:material3:...")
implementation("com.rometools:rome:1.18.0")
implementation("io.coil-kt:coil-compose:2.4.0")
implementation("com.squareup.okhttp3:okhttp:4.12.0")
implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
// + Room, Navigation, Hilt, Coroutines и др.
```

---


   ```bash
   git clone https://github.com/your-username/news-aggregator.git
   cd news-aggregator
   ```

---
