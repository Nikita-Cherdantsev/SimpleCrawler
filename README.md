# SimpleCrawler

This project is designed for resolving technical task before interview process. You can see task below:

> Необходимо создать простой интернет crawler, который будет доставать из страниц информацию о названии сайта. Это должно быть приложение с http эндпоинтом. На этот эндпоинт поступает список http url'ов. Приложение должно пройтись по всем предоставленным урлам и достать оттуда название. Названием условно будем считать содержимое тэга title. После изъятия информации из всех страниц эндпоинт должен вернуть ответ, в котором каждому входному урлу соответствует найденное название. Все недостающие требования или неоднозначности начальной формулировки задачи вы должны разрешить самостоятельно - это является частью задания. Единственное требование к реализации - приложение должно быть написано на языке Scala.

### Used libraries and frameworks:
- playframework
- guice
- wc
- jsoup
  
_Used Java version - 21.0.4; Used Scala version - 2.13.14; Used Play Franework version - 3.0.5_

### Project launch

For running the project you need to initialize SBT Shell first and then put the command `run` into shell or just put the command `sbt run` into terminal. After project will started you will be able to use the only one route `\handleUrls` which will proceed inout urls according to the task.
