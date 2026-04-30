_# `code-maven-build_snapshot`

[`code-maven_java-build_snapshot.yml`](../code-maven_java-build_snapshot.yml) workflow deploys a snapshot version to the distribution platform

## Trigger

- Using GitFlow: Any push on `develop` / `develop-*` branches with changes in `code` path.
- Using TBD: Any push on `main` / `main-*` branches with changes in `code` path.

## Where does it run?

`ubuntu-24.04` GitHub infrastructure.

## Versions used

`asdf` and any `Java`, `Maven` and `Node`.

## How does it work?

This workflow relies on asdf to automatically load any tool version defined on the project's `code/.tool-versions` file.

## Jobs

- ### `build-and-promote`

  - **Steps**
    - `mvn verify`
