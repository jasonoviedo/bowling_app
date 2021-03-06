# Bowling App

A program that can read a file containing the information
for a bowling game, process it and render it to
a table to an injectable print stream.

## Considerations
The program uses gradle.
Java streams and lambdas are used.
The main game engine is encapsulated behind
a well defined interface, which allows full independence from
implementation. 

The program is designed to solve the stated problem, prioritizing:
1. Readability: Code should be easy to follow
2. Separation of concerns: Well defined responsibilities
3. Pragmatism: Do not solve un-existent problems.

## Usage
For *Nix like systems, use gradlew, for Windows, gradlew.bat:

```bash
./gradlew run --args="data/datai.txt"
```
Four data files are included in the data folder for testing
purposes (data0 through data3).

* data0.txt = No fouls, Jeff only throws twice on th 10th, John three times
* data1.txt = Sample input
* data2.txt = Fouls and misses
* data3.txt = All failed
* data4.txt = Perfect game

The test set can be run like this:
```bash
./gradlew test
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)