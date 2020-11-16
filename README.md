# Bowling App

A program that can read a file containing the information
for a bowling game, process it and render it to
a table to stdout.

## Considerations
The program uses gradle.
Java streams and lambdas are used.
The main game engine is encapsulated behind
a well defined interface, which allows full independence from
implementation. To prove this, the implementation uses [Kotlin](https://kotlinlang.org),
a JVM compatible language.

## Usage
For *Nix like systems:

```bash
gradlew run --args="data/datai.txt"
```
Four data files are included in the data folder for testing
purposes (data0 through data3).

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)