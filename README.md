### Hexlet tests and linter status:
[![Actions Status](https://github.com/alexey4050/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/alexey4050/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/58d59808062cbdbd47cb/maintainability)](https://codeclimate.com/github/alexey4050/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/58d59808062cbdbd47cb/test_coverage)](https://codeclimate.com/github/alexey4050/java-project-71/test_coverage)
[![CI](https://github.com/alexey4050/java-project-71/actions/workflows/ci.yml/badge.svg)](https://github.com/alexey4050/java-project-71/actions/workflows/ci.yml)

## Вычеслитель отличий
### Описание
`gendiff` - это консольная утилита для сравнени конфигураци файлов и отображения различий между ними. Утилита разработана с использованием бибилиотеки [picocli](https://picocli.info/),  которая упрощает создание ClI-приложений на Java.

*./build/install/app/bin/app -h*

[![asciicast](https://asciinema.org/a/sNmToSVr26iGAYzk28SnlI2N7.svg)](https://asciinema.org/a/sNmToSVr26iGAYzk28SnlI2N7)

## Аргументы и опции
*./build/install/app/bin/app -h*

[![asciicast](https://asciinema.org/a/dhKPqe6TRTZ54jNtEVP2yR5Mi.svg)](https://asciinema.org/a/dhKPqe6TRTZ54jNtEVP2yR5Mi)

## Сравнение плоских файлов (JSON)

Отсутствие плюса или минуса говорит, что ключ есть в обоих файлах, и его значения совпадают. Во всех остальных ситуациях ключ был либо удалён, либо добавлен, либо изменён.

*./build/install/app/bin/app filepath1.json filepath2.json*

[![asciicast](https://asciinema.org/a/WBW29efDjDFYHBVdAYKylNMxJ.svg)](https://asciinema.org/a/WBW29efDjDFYHBVdAYKylNMxJ)

## Сравнение плоский файлов (YAML)
YAML (рекурсивный акроним англ. «YAML Ain't Markup Language» — «YAML — не язык разметки») — дружественный формат сериализации данных, концептуально близкий к языкам разметки, но ориентированный на удобство ввода-вывода типичных структур данных многих языков программирования.
В трактовке названия отражена история развития: на ранних этапах YAML расшифровывался как yet another markup language («ещё один язык разметки») и даже позиционировался как конкурент XML, но позже был переименован с целью акцентировать внимание на данных, а не на разметке документов.

*./build/install/app/bin/app filepath1.yml filepath2.yml*
[![asciicast](https://asciinema.org/a/VpK2qbK0FjG9pc6G2pTLgLBzT.svg)](https://asciinema.org/a/VpK2qbK0FjG9pc6G2pTLgLBzT)

## Обработка данных
*./build/install/app/bin/app filepath1.json filepath2.json*
[![asciicast](https://asciinema.org/a/0ZTQYJytv6aDCMNGhWS9rGgg6.svg)](https://asciinema.org/a/0ZTQYJytv6aDCMNGhWS9rGgg6)

## Плоский формат
*./build/install/app/bin/app -f plain filepath1.json filepath2.json*
[![asciicast](https://asciinema.org/a/nKeTpuSLAkdm8ZRZ5W8CrlZrr.svg)](https://asciinema.org/a/nKeTpuSLAkdm8ZRZ5W8CrlZrr)
