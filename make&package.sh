cd .\\src\\
echo javac -Xlint:deprecation -d ..\\bin\\ .\\App.java
cd ..\\
cd .\\bin
<<<<<<< HEAD
# rm .\\label-media-3.0.jar
jar cvfm label-media-3.0.jar ..\\manifest.txt .\\*
cp .\\label-media-3.0.jar ..\\
=======
rm .\\label-media-2.0.jar
jar cvfm label-media-2.0.jar ..\\manifest.txt .\\*
cp .\\label-media-2.0.jar ..\\
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
