/*
 * Created by Tom Lous on 2019-03-04
 * Copyright © 2019 Packt Publishing Ltd.
 */

package info.lous.packt.mlspark

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

import scala.util.Random

object SparkUIDemo extends App {

  @transient lazy val log = Logger.getLogger(getClass.getName)
  val random = new Random(19791108)

  // Create a spark session
  val spark = SparkSession.builder()
    .master("local[*]")
    .appName("SparkUIDemo")
    .getOrCreate()

  import spark.implicits._

  val sourceSize = 10000

  // Generate a random source
  val source1 = (0 until sourceSize).map(i =>
    (i, random.nextInt(20), random.nextDouble, (0 until 5 + random.nextInt(5)).map(_ => random.nextPrintableChar()).mkString("")))
    .toDF("id", "number1", "value1", "text1")

  // Generate another random source
  val source2 = (0 until sourceSize).map(i =>
    (i, random.nextInt(20), random.nextDouble, (0 until 5 + random.nextInt(5)).map(_ => random.nextPrintableChar()).mkString("")))
    .toDF("id", "number2", "value2", "text2")

  // Join the sources and do some arbitrary operations
  val joined = source1
    .join(broadcast(source2), "id")
    .withColumn("number", 'number1 + 'number2)
    .withColumn("text", explode(array('text1, 'text2)))
    .withColumn("value", explode(array('value1, 'value2)))
    .groupBy("number")
    .agg(
      max('id).as("maxId"),
      collect_set('text).as("uniqueText"),
      avg('value).as("avgValue")
    )
    .repartition(100)
    .orderBy('maxId.desc)

  // Evaluate joined dataframe
  log.info("# of rows (1) " + joined.count())

  val cached = joined.cache()

  // Evaluate cached dataframe
  val rows = cached.collect()
  log.info("# of rows (2) " + rows.length)

  // Used cached
  cached.show()

  // Don't kill SparkUI for 1h
  Thread.sleep(60 * 60 * 1000)
}
