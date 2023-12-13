# Advent of Code 2023

This repository is hosted at `https://gitlab.com/draget-advent-of-code/aoc2023` and only mirrored to GitHub.

Draget's (Michael G) solutions to Advent of Code 2023. The goal is not only to solve the tasks, but add a little something each time to grow my skills in different areas.

All new aspects are documented in the [changes](#changelog) section below.

## Technology

This project is based on [Kotlin](https://kotlinlang.org/).

## Building and running

* Build: `./gradlew build`
* Test: `./gradlew verify`
* Run: `./gradlew run`

## Contributing

Even if I want to learn myself, I still very much appreciate if you want to make suggestions or tell me how I can do something better, by raising an issue or suggesting a change in a MR. While I doubt anyone would do that for a AoC repository, input is still welcome!

## Changelog

### 2023-12-13

* Refactor to properly use reflection on class names, no need to specify day numbers anymore
* Save inputs and load them from disk, if already loaded

### 2023-12-11

* Solve Day 11

### 2023-12-03

* Initial project setup
* Added loading of AoC session via interaction session
