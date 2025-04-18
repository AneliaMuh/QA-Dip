# План автоматизации
### Перечень тестовых данных
Валидные данные: Номер карты: значение цифрами в формате XXXX XXXX XXXX XXXX.
1.	номер карты 4444 4444 4444 4441 (APPROVED — действующая карта для успешных операций);
2.	номер карты 4444 4444 4444 4442 (DECLINED — недействующая карта для отказа в операции).
Месяц: значение цифрами в формате XX от 01 до 12
Год: значение цифрами в формате XX, не ранее текущего года
CVC/CVV: значение цифрами в формате XXX
### Общее предисловие
открыта страница http://localhost:8080/

## Перечень автоматизируемых сценариев:
### Позитивные сценарии

### Оплата по карте со статусом карты для оплаты «APPROVED»
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить"**.
3. Ввести **валидные данные** из 16 цифр: `4444 4444 4444 4441` в поле **"Номер карты"**.
4. Ввести в поле **"Месяц"** двузначное значение от `01` до `12` (месяц не должен быть меньше текущего).
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года).
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире.
7. Ввести в поле **"CVC/CVV"** 3-значный номер (например: `123`).
8. Кликнуть по кнопке **"Продолжить"**.

**Ожидаемый результат:**  
Появилось всплывающее окно об успешной операции с сообщением **"Успешно. Операция одобрена Банком"**.

---

### Оплата через «Купить в кредит» со статусом карты для оплаты «APPROVED»
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить в кредит"**.
3. Ввести **валидные данные** из 16 цифр: `4444 4444 4444 4441` в поле **"Номер карты"**.
4. Ввести в поле **"Месяц"** двузначное значение от `01` до `12` (месяц не должен быть меньше текущего).
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года).
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире.
7. Ввести в поле **"CVC/CVV"** 3-значный номер (например: `123`).
8. Кликнуть по кнопке **"Продолжить"**.

**Ожидаемый результат:**  
Появилось всплывающее окно об успешной операции с сообщением **"Успешно. Операция одобрена Банком"**.

---

### Оплата по карте со статусом карты для оплаты «DECLINED»
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить"**.
3. Ввести **валидные данные** из 16 цифр: `4444 4444 4444 4442` в поле **"Номер карты"**.
4. Ввести в поле **"Месяц"** двузначное значение от `01` до `12` (месяц не должен быть меньше текущего).
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года).
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире.
7. Ввести в поле **"CVC/CVV"** 3-значный номер (например: `123`).
8. Кликнуть по кнопке **"Продолжить"**.

**Ожидаемый результат:**  
Появилось всплывающее окно об ошибке с сообщением **"Ошибка! Банк отказал в проведении операции"**.

---

### Оплата через «Купить в кредит» со статусом карты для оплаты «DECLINED»
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить в кредит"**.
3. Ввести **валидные данные** из 16 цифр: `4444 4444 4444 4442` в поле **"Номер карты"**.
4. Ввести в поле **"Месяц"** двузначное значение от `01` до `12` (месяц не должен быть меньше текущего).
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года).
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире.
7. Ввести в поле **"CVC/CVV"** 3-значный номер (например: `123`).
8. Кликнуть по кнопке **"Продолжить"**.

**Ожидаемый результат:**  
Появилось всплывающее окно об ошибке с сообщением **"Ошибка! Банк отказал в проведении операции"**.

---

## Негативные сценарии  

## Оплата по карте с незарегистрированной картой
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить"**
3. Ввести в поле **"Номер карты"** рандомный номер: `2232 1111 4454 6543`
4. Ввести в поле **"Месяц"** двузначное значение от `01` до `12` (месяц не должен быть меньше текущего)
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года)
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире
7. Ввести в поле **"CVC/CVV"** - 3-х значный номер (например: `123`)
8. Кликнуть по кнопке **"Продолжить"**

**Ожидаемый результат:**  
Появилось всплывающее окно об ошибке с сообщением **"Ошибка! Банк отказал в проведении операции"**.

---
## Оплата через «Купить в кредит» с незарегистрированной картой
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить"**
3. Ввести в поле **"Номер карты"** рандомный номер: `2232 1111 4454 6543`
4. Ввести в поле **"Месяц"** двузначное значение от `01` до `12` (месяц не должен быть меньше текущего)
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года)
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире
7. Ввести в поле **"CVC/CVV"** - 3-х значный номер (например: `123`)
8. Кликнуть по кнопке **"Продолжить"**

**Ожидаемый результат:**  
Появилось всплывающее окно об ошибке с сообщением **"Ошибка! Банк отказал в проведении операции"**.

---

## Оплата по карте с невалидным значением номера карты (менее 16 цифр)
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить"**
3. Ввести в поле **"Номер карты"** данные: `2222 1111 4444`
4. Ввести в поле **"Месяц"** двузначное значение от `01` до `12` (месяц не должен быть меньше текущего)  
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года)  
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире  
7. Ввести в поле **"CVC/CVV"** - 3-х значный номер (например: `123`)  
8. Кликнуть по кнопке **"Продолжить"**  

**Ожидаемый результат:**  
Появляется текст под полем **"Номер карты"**: *"Неверный формат"*

---

## Оплата через **«Купить в кредит»** с невалидным значением номера карты (менее 16 цифр)
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить в кредит"**
3. Ввести в поле **"Номер карты"** данные: `2222 1111 4444`
4. Ввести в поле **"Месяц"** двузначное значение от `01` до `12` (месяц не должен быть меньше текущего)  
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года)  
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире  
7. Ввести в поле **"CVC/CVV"** - 3-х значный номер (например: `123`)  
8. Кликнуть по кнопке **"Продолжить"**  

**Ожидаемый результат:**  
Появляется текст под полем **"Номер карты"**: *"Неверный формат"*

---

## Оплата по карте с незаполненным полем **"Номер карты"**
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить"**
3. Оставить поле **"Номер карты"** пустым  
4. Ввести в поле **"Месяц"** двузначное значение от `01` до `12` (месяц не должен быть меньше текущего)  
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года)  
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире  
7. Ввести в поле **"CVC/CVV"** - 3-х значный номер (например: `123`)  
8. Кликнуть по кнопке **"Продолжить"**  

**Ожидаемый результат:**  
Появляется текст под полем **"Номер карты"**: *"Поле обязательно для заполнения"*

---

## Оплата через **«Купить в кредит»** с незаполненным полем **"Номер карты"**
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить в кредит"**
3. Оставить поле **"Номер карты"** пустым  
4. Ввести в поле **"Месяц"** двузначное значение от `01` до `12` (месяц не должен быть меньше текущего)  
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года)  
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире  
7. Ввести в поле **"CVC/CVV"** - 3-х значный номер (например: `123`)  
8. Кликнуть по кнопке **"Продолжить"**  

**Ожидаемый результат:**  
Появляется текст под полем **"Номер карты"**: *"Поле обязательно для заполнения"*

---

## Оплата по карте с незаполненным полем **"Месяц"**
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить"**
3. Ввести валидные данные из 16 цифр: `4444 4444 4444 4441` в поле **"Номер карты"**
4. Оставить поле **"Месяц"** пустым  
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года)  
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире  
7. Ввести в поле **"CVC/CVV"** - 3-х значный номер (например: `123`)  
8. Кликнуть по кнопке **"Продолжить"**  

**Ожидаемый результат:**  
Появляется текст под полем **"Месяц"**: *"Поле обязательно для заполнения"*

---

## Оплата через **«Купить в кредит»** с незаполненным полем **"Месяц"**
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить в кредит"**
3. Ввести валидные данные из 16 цифр: `4444 4444 4444 4441` в поле **"Номер карты"**
4. Оставить поле **"Месяц"** пустым  
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года)  
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире  
7. Ввести в поле **"CVC/CVV"** - 3-х значный номер (например: `123`)  
8. Кликнуть по кнопке **"Продолжить"**  

**Ожидаемый результат:**  
Появляется текст под полем **"Месяц"**: *"Поле обязательно для заполнения"*

---

## Оплата по карте с одной цифрой в поле **"Месяц"**
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить"**
3. Ввести валидные данные из 16 цифр: `4444 4444 4444 4441` в поле **"Номер карты"**
4. Ввести в поле **"Месяц"** одну цифру, например: `1`  
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года)  
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире  
7. Ввести в поле **"CVC/CVV"** - 3-х значный номер (например: `123`)  
8. Кликнуть по кнопке **"Продолжить"**  

**Ожидаемый результат:**  
Появляется текст под полем **"Месяц"**: *"Неверный формат"*

---

## Оплата через **«Купить в кредит»** с одной цифрой в поле **"Месяц"**
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить в кредит"**
3. Ввести валидные данные из 16 цифр: `4444 4444 4444 4441` в поле **"Номер карты"**
4. Ввести в поле **"Месяц"** одну цифру, например: `1`  
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года)  
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире  
7. Ввести в поле **"CVC/CVV"** - 3-х значный номер (например: `123`)  
8. Кликнуть по кнопке **"Продолжить"**  

**Ожидаемый результат:**  
Появляется текст под полем **"Месяц"**: *"Неверный формат"*

---

## Оплата по карте с вводом `00` в поле **"Месяц"**
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить"**
3. Ввести валидные данные из 16 цифр: `4444 4444 4444 4441` в поле **"Номер карты"**
4. Ввести в поле **"Месяц"** `00`  
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года)  
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире  
7. Ввести в поле **"CVC/CVV"** - 3-х значный номер (например: `123`)  
8. Кликнуть по кнопке **"Продолжить"**  

**Ожидаемый результат:**  
Появляется текст под полем **"Месяц"**:  *"Неверно указан срок действия карты"*

---

## Оплата через **«Купить в кредит»** с вводом `00` в поле **"Месяц"**
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке **"Купить в кредит"**
3. Ввести валидные данные из 16 цифр: `4444 4444 4444 4441` в поле **"Номер карты"**
4. Ввести в поле **"Месяц"** `00`  
5. Ввести в поле **"Год"** две последние цифры (не ранее текущего года)  
6. В поле **"Владелец"** ввести валидные значения (латинские буквы, например: `Muhambetova Anelia`), допускаются пробел и тире  
7. Ввести в поле **"CVC/CVV"** - 3-х значный номер (например: `123`)  
8. Кликнуть по кнопке **"Продолжить"**  

**Ожидаемый результат:**  
Появляется текст под полем **"Месяц"**: *"Неверно указан срок действия карты"*

---

## Оплата по карте с вводом невалидного значения (несуществующий месяц) в поле «Месяц»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" несуществующий месяц. Пример: 15
5. Ввести в поле "Год" две последние цифры (не ранее текущего года)
6. В поле "Владелец" ввести валидные значения (латинские буквы (например: Muhambetova Anelia), допускается пробел и тире)
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Месяц": "Неверно указан срок действия карты".

---

## Оплата через «Купить в кредит» с вводом невалидного значения (несуществующий месяц) в поле «Месяц»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить в кредит"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" несуществующий месяц. Пример: 13
5. Ввести в поле "Год" две последние цифры (не ранее текущего года)
6. В поле "Владелец" ввести валидные значения (латинские буквы (например: Muhambetova Anelia), допускается пробел и тире)
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Месяц": "Неверно указан срок действия карты".

---

## Оплата по карте с вводом истекшего срока карты (год меньше текущего) в поле «Год»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" две последние цифры года ранее текущего. Пример: 2001
6. В поле "Владелец" ввести валидные значения (латинские буквы (например: Muhambetova Anelia), допускается пробел и тире)
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Год": "Истёк срок действия карты".

---

## Оплата через «Купить в кредит» с вводом истекшего срока карты (год меньше текущего) в поле «Год»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить в кредит"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" две последние цифры года ранее текущего. Пример: 2001
6. В поле "Владелец" ввести валидные значения (латинские буквы (например: Muhambetova Anelia), допускается пробел и тире)
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Год": "Истёк срок действия карты".

---

## Оплата по карте с незаполненным полем «Год»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Оставить поле "Год" пустым.
6. В поле "Владелец" ввести валидные значения (латинские буквы (например: Muhambetova Anelia), допускается пробел и тире)
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Год": "Поле обязательно для заполнения".

---

## Оплата через «Купить в кредит» с незаполненным полем «Год»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить в кредит"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Оставить поле "Год" пустым.
6. В поле "Владелец" ввести валидные значения (латинские буквы (например: Muhambetova Anelia), допускается пробел и тире)
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Год": "Поле обязательно для заполнения".

---

## Оплата по карте с одной цифрой в поле «Год»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" одну цифру. Пример: 2
6. В поле "Владелец" ввести валидные значения (латинские буквы (например: Muhambetova Anelia), допускается пробел и тире)
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Год": "Неверный формат".

---

## Оплата через «Купить в кредит» с одной цифрой в поле «Год»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить в кредит"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" одну цифру. Пример: 2
6. В поле "Владелец" ввести валидные значения (латинские буквы (например: Muhambetova Anelia), допускается пробел и тире)
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Год": "Неверный формат".

---

## Оплата по карте с числовым значением поля «Владелец»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" две последние цифры (не ранее текущего года)
6. В поле "Владелец" ввести цифры или число. Пример: An4l1
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Владелец": "Неверный формат".

---

## Оплата через «Купить в кредит» с числовым значением поля «Владелец»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить в кредит"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" две последние цифры (не ранее текущего года)
6. В поле "Владелец" ввести цифры или число. Пример: An4l1
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Владелец": "Неверный формат".

---

## Оплата по карте с вводом кириллицы в поле «Владелец»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" две последние цифры (не ранее текущего года)
6. В поле "Владелец" ввести значение кириллицей. Пример: Анелия
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Владелец": "Неверный формат".

---

## Оплата через «Купить в кредит» с вводом кириллицы в поле «Владелец»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить в кредит"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" две последние цифры (не ранее текущего года)
6. В поле "Владелец" ввести значение кириллицей. Пример: Анелия
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Владелец": "Неверный формат".

---

## Оплата по карте с вводом спецсимволов (кроме пробел и дефис) в поле «Владелец»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" две последние цифры (не ранее текущего года)
6. В поле "Владелец" спецсимволы (кроме пробел и дефис). Пример: Ан№л%я
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Владелец": "Неверный формат".

---

## Оплата через «Купить в кредит» с вводом спецсимволов (кроме пробел и дефис) в поле «Владелец»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить в кредит"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" две последние цифры (не ранее текущего года)
6. В поле "Владелец" спецсимволы (кроме пробел и дефис). Пример: Ан№л%я
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Владелец": "Неверный формат".

---

## Оплата по карте с незаполненным полем «Владелец»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" две последние цифры (не ранее текущего года)
6. Оставить поле "Владелец" пустым.
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Владелец": "Поле обязательно для заполнения".

---

## Оплата через «Купить в кредит» с незаполненным полем «Владелец»

1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить в кредит"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" две последние цифры (не ранее текущего года)
6. Оставить поле "Владелец" пустым.
7. Ввести в поле "CVC/CVV" - 3-х значный номер (например: 123)
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "Владелец": "Поле обязательно для заполнения".

---

### Оплата по карте с незаполненным полем «CVV»
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" две последние цифры (не ранее текущего года)
6. В поле "Владелец" ввести валидные значения (латинские буквы (например: Muhambetova Anelia), допускается пробел и тире).
7. Оставить поле "CVC/CVV" пустым.
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "CVV": "Поле обязательно для заполнения".

---

### Оплата через «Купить в кредит» с незаполненным полем «CVV»
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить в кредит"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" две последние цифры (не ранее текущего года)
6. В поле "Владелец" ввести валидные значения (латинские буквы (например: Muhambetova Anelia), допускается пробел и тире).
7. Оставить поле "CVC/CVV" пустым.
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "CVV": "Поле обязательно для заполнения".

---

### Оплата по карте с вводом менее 3 цифр в поле «CVV»
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" две последние цифры (не ранее текущего года)
6. В поле "Владелец" ввести валидные значения (латинские буквы (например: Muhambetova Anelia), допускается пробел и тире).
7. Ввести в поле "CVC/CVV" менее 3-х цифр. Пример: 34
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "CVC/CVV": "Неверный формат".

---

### Оплата через «Купить в кредит» с вводом менее 3 цифр в поле «CVV»
1. Открыть в браузере страницу [http://localhost:8080/](http://localhost:8080/)
2. Кликнуть по кнопке "Купить в кредит"
3. Ввести валидные данные из 16 цифр: 4444 4444 4444 4441 в поле "Номер карты"
4. Ввести в поле "Месяц" двузначное значение от 01 до 12 (месяц не должен быть меньше текущего)
5. Ввести в поле "Год" две последние цифры (не ранее текущего года)
6. В поле "Владелец" ввести валидные значения (латинские буквы (например: Muhambetova Anelia), допускается пробел и тире).
7. Ввести в поле "CVC/CVV" менее 3-х цифр. Пример: 34
8. Кликнуть по кнопке "Продолжить"

**Ожидаемый результат:**  
Появился текст под полем "CVC/CVV": "Неверный формат".

---

### Перечень используемых инструментов с обоснованием выбора

1. **IntelliJ IDEA**  
   Среда разработки программного обеспечения. Данная программа является одной из самых популярных и удобных для Java-разработки с поддержкой всех последних технологий и фреймворков. Она подходит для работы не только на Java, но и на JavaScript, Python.

2. **Java 11**  
   Один из самых популярных языков для кодирования и написания автотестов. Java удобна благодаря большому количеству библиотек для написания автотестов, а также наличию набора готового ПО для разработки и запуска приложений.

3. **Gradle**  
   Система управления зависимостями. Gradle используется для сборки проекта, управления подключенными зависимостями, а также для генерации отчётов о тестировании.

4. **JUnit 5**  
   Современный фреймворк для написания и запуска автотестов. Легко интегрируется с Gradle, JVM, IntelliJ IDEA, содержит все необходимые аннотации и поддерживает параметризированные тесты.

5. **Selenide**  
   Удобный инструмент для автоматизированного тестирования веб-приложений, основанный на Selenium WebDriver. Selenide упрощает написание тестов для веб-приложений, прост в использовании. Легко добавляется в зависимости и не требует отдельной загрузки браузера. С помощью него можно легко находить элементы на странице, выполнять действия с ними и проверять их состояние.

6. **Docker**  
   Система контейнеризации с открытым исходным кодом, с помощью которой можно автоматизировать создание приложений, их доставку и управление. Docker позволяет подключать базы данных MySQL и PostgreSQL.

7. **Allure**  
   Фреймворк, который позволяет создать визуально понятный и наглядный отчёт о выполнении автотестов. Этот отчёт отображает прохождение тестов и ошибки.

8. **Библиотека Lombok**  
   Библиотека, которая помогает упростить и ускорить процесс разработки. Она уменьшает количество шаблонного кода в Java-приложениях, автоматизируя создание геттеров, сеттеров, конструкторов и других стандартных методов. Это повышает читаемость и упрощает поддержку кода.

9. **Git**  
   Распределённая система управления версиями. Git позволяет удобно создавать специализированные системы контроля версий на базе Git или пользовательские интерфейсы. Она достаточно проста и удобна для управления исходным кодом и является очень распространённой системой контроля версий, которая хорошо взаимодействует с различными операционными системами.

10. **GitHub**  
    Удобный веб-сервис для хранения кода, включая автотесты. GitHub интегрирован с Git и предоставляет удобный интерфейс для управления проектами.

11. **Faker**  
    Библиотека для генерации тестовых данных. Эта библиотека используется для создания случайных данных, что удобно для тестирования различных функциональностей.

    ### Перечень и описание возможных рисков при автоматизации

1. **Отсутствие или недостаток документации**  
   Если процесс автоматизации не сопровождается документацией (инструкциями, описанием тестов, отчётами), это усложнит поддержку тестов и их дальнейшее развитие.

2. **Неисправности оборудования и программного обеспечения**  
   Автоматизированные системы могут столкнуться с техническими сбоями, что приведёт к простоям, задержке выполнения тестов и потере данных. Это может быть связано с несовместимостью инструментов, нехваткой ресурсов или ошибками в конфигурации среды.

3. **Потеря данных**  
   В случае сбоя системы или ошибки в тестах возможна потеря важных тестовых данных, что усложнит анализ результатов и потребует повторного выполнения тестов. Это особенно критично при проверке базы данных.

4. **Неверно рассчитано время выполнения работ**  
   Ошибки в оценке трудозатрат на разработку и выполнение тестов могут привести к задержкам в проекте и несоответствию установленным срокам.

5. **Проблемы с интеграцией систем**  
   Интеграция автоматизированных тестов с различными сервисами (БД, эмулятор банковских сервисов, отчётность) может вызвать конфликты, ошибки передачи данных и несоответствие ожидаемого поведения системы.

6. **Недостаточная подготовка персонала**  
   Если тестировщики не обладают достаточными знаниями в автоматизации, это может привести к ошибкам в тестах, неверной интерпретации результатов и снижению качества тестирования.

7. **Несоответствие тестов измененному функционалу**  
   Автоматизированные тесты могут устаревать, если в код вносятся изменения, а тесты не обновляются. Это приведёт к ложным сбоям и необходимости постоянного рефакторинга тестов.

8. **Недостаточное тестовое покрытие**  
   Если автоматизированные тесты не охватывают все критические сценарии, существует риск пропуска ошибок, что может негативно сказаться на качестве продукта.

---

### Интервальная оценка с учётом рисков в часах

- **Анализ требований и подготовка плана автоматизации**: 6-8 часов
- **Настройка тестового окружения (Docker, БД, эмулятор банковских сервисов)**: 5-6 часов
- **Разработка тестовых сценариев**: 8-10 часов
- **Написание автотестов, их отладка**: 20-24 часа
- **Подготовка отчётных документов по итогам автоматизированного тестирования**: 4 часа
- **Оформление возможных баг-репортов и документации по тестированию**: 4-5 часов
- **Подготовка отчётных документов по итогам автоматизации**: 4 часа

**Итого**: Минимально 51 час. Максимально 61 час.

---

### План сдачи работы

- **День 1**: Анализ требований, составление плана автоматизации (Plan.md), настройка репозитория.
- **День 2**: Разворачивание тестового окружения: поднимаются контейнеры с MySQL, PostgreSQL и эмулятором банковских сервисов, проводится их проверка.
- **День 3**: Разработка UI-тестов для позитивных сценариев.
- **День 4**: Разработка UI-тестов для негативных сценариев и тесты на взаимодействие с БД.
- **День 5**: Отладка тестов, настройка Allure и Report Portal, полный тестовый прогон.
- **День 6**: Анализ результатов, оформление баг-репортов и подготовка отчёта о тестировании.
- **День 7**: Написание отчёта по автоматизации, доработка и финальная проверка.
- **День 8**: Резервный день: последний запуск тестов, проверка всех материалов и публикация в репозитории.

