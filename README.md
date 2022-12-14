# PetNet
Плановые сроки начала работы по созданию системы: 01.10.2022<br>
Плановые сроки окончания работы по созданию системы: 21.12.2022

Рассматривается создание единого веб ресурса, создающего доступную среду для хозяев домашних животных по ведению ухода и заботы о питомце, осуществляющее функции коммуникативного, ветеринарного и услугового характера.
<h2>Назначение разработки</h2>
По опросам ВЦИОМ 2021 года у 2/3 опрошенных есть домашнее животное, а 2/3 тех, у кого нет, хотели бы его завести. Приложение потенциально может быть актуальным среди пользователей из России.
Решение коммуникационной проблемы современного общества. Потенциальные пользователи заинтересованы в создании площадки для общения на основе определенных интересов, в числе которых немаловажную роль играют домашние животные. Решение проблемы бродячих животных. Серьезна проблема потерявшихся питомцев, которые со временем теряют одомашненность и создают определенный риск обществу. 
В конечном счете приложение должно представлять из себя платформу, предоставляющую следующие возможности:<br>

1.	Поиска компании для прогулок с питомцами (по местоположению или по категориям)
2.	Общения хозяев питомцев внутри приложения
3.	Поиск пропавшего питомца
4.	Поиск догситтеров, отелей для животных, зоомагазинов
5.	Запись в ветеринарную клинику
6.	Ведения медицинской карты питомца

Это должен быть сервис, который осуществляет свою работу за счет партнерских взаимодействий с заинтересованными компаниями. В их число входят: ветклиники, зоомагазины, компании по оказанию услуг догситтерства или временной передержки.

<h2>Существующие решения</h2> 
Был проанализирован рынок приложений с похожей тематикой. Исходя из результатов анализа были выведена явная востребованность такого приложения среди потенциальных пользователей. На основе исследования западного рынка похожих приложений был сделан вывод о том, что в мире существует довольно большое количество успешно коммерциализированных платформ, которые не имеют адаптации для российского рынка. <br>Например, приложение 11Pets имеет широкий функционал, однако не включает в себя все те функции, которые мы собираемся заложить в нашем приложение. Итак, функционал 11Pets содержит историю болезни животного и поддерживает пополнение ее диагностическими показаниями на всех жизненных этапах. Софт рассчитан на использование владельцами нескольких питомцев и позволяет индивидуально для каждого внести фото и истории болезни.<br><br>

На данный момент в РФ реализован ряд приложений, осуществляющих только ряд узконаправленных функций, находящихся в зоне определенной тематики. Например, приложение PetMet создано в формате приложения для знакомств с животными, которые находятся в приюте, чтобы людям было удобнее их выбирать. Однако функционал этого приложения осуществлен только на территории Москвы и Санкт-Петербурга. В 2018 году компания Яндекс разработала крупный проект по поиску пропавших домашних животных Yandex Petfinder по всем регионам России, но на момент 2022 года этот сервис сняли с обслуживания. Его аналог – Lost Pet представляет собой ленту объявлений о пропаже животных в регионе пользователя, подключаясь к текущему местоположению. Также интересно приложение PetStory, которое представляет из себя сервис для консультаций с ветеринаром онлайн, подбор оптимального рациона и средства от паразитов – оно по большей части направлено на здоровье животных.
Помимо всего вышеперечисленного, нередко приложения, доступные для использования реализованы только под одну платформу, что значительно сокращает сферу их использования. Таким образом, в планах реализовать кроссплатформенное приложение, которое отличается более широким функционалом, нежели существующие аналоги. 

<h2>Требования к системе</h2>
<h3>Минимальные требования к функциям сервиса</h3>
<li>Авторизация и создание личного кабинета пользователя;
<li>Возможность добавления и ведения профиля питомца;
<li>Ведение медкарты питомца;
<li>Поиск компании для совместных прогулок;
<li>Добавление карты с площадками, ветклиниками(с контактными данными для записи), магазины для животных, отелями для животных;

<h3>Расширенные требования к функциям сервиса</h3>
<li>Доска объявлений о пропавших животных
<li>Профиль догситтера
<li>Чат для общения с зарегистрированными пользователями, ветклиниками, догситтерами

Для работы системы необходим арендованный сервер для хранения данных. Информационная система будет представлять из себя мобильное приложение на Android и IOS. Frontend-часть на Kotlin с использованием Jetpack Compose и Swift с использованием SwiftUI, соответственно. Backend-часть будет реализована на фреймворке Spring, который будет работать с БД PostgreSQL. 

<h2>Технико-экономические показатели</h2>
Данная разработка потенциально включает в себя затраты на размещение и продвижение данного продукта – коллаборации с ветеринарными клиниками, магазинами для животных и т.п. Также стоит учесть затраты на аренду мощных серверов для реализации функционала, связанного с геолокацией.
