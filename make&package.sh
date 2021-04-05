cd .\\src\\
echo javac -Xlint:deprecation -d ..\\bin\\ .\\App.java
cd ..\\
cd .\\bin
# rm .\\label-media-3.0.jar
jar cvfm label-media-3.0.jar ..\\manifest.txt .\\*
cp .\\label-media-3.0.jar ..\\