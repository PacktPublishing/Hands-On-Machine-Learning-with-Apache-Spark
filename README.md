## $5 Tech Unlocked 2021!
[Buy and download this product for only $5 on PacktPub.com](https://www.packtpub.com/)
-----
*The $5 campaign         runs from __December 15th 2020__ to __January 13th 2021.__*

# Hands-On-Machine-Learning-with-Apache-Spark
Hands-On Machine Learning with Apache Spark, published by Packt


## Installation

To make sure you have all the correct versions installed, run `./scripts/check-versions.sh`
Otherwise update your system to these versions:

- Java: 1.8
    - check: `java -version`
    - install/update: `brew cask [install|upgrade] java`
    
- Scala: 2.11
    - check: `scala -version`
    - install/update: `brew cask [install|upgrade] scala`    

- Sbt: 1.0
    - check: `sbt sbtVersion`
    - install/update: `brew cask [install|upgrade] sbt`
    
- Python: 3.4   
    - check: `python3 --version`
    - install/update: `brew cask [install|upgrade] python3`

- Spark: 2.4.0
    - check: `spark-shell --version`
    - install/update: `brew cask [install|upgrade] apache-spark`
    
Now either run once, or add to your profile (`~/.bash_profile` or `~/.zshrc`):
`export PYSPARK_PYTHON=python3`


## Exersises:

### Chapter 1:
    
Run the SparkUIDemo : `sbt "project sparkui" run`

You now have the SparkUI on [http://localhost:4040](http://localhost:4040)
    
##### History Server

Add to `spark-defaults.conf` :

```
spark.eventLog.enabled             true
spark.eventLog.dir                 /tmp/spark-events-log
spark.history.fs.logDirectory      /tmp/spark-events-log
```
    
Create the folder: `mkdir -p /tmp/spark-events-log`

Start the history server: `./sbin/start-history-server.sh`

You now have the History Server on [http://localhost:18080](http://localhost:18080)

