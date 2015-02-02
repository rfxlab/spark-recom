package de.kp.spark.recom
/* Copyright (c) 2014 Dr. Krusche & Partner PartG
 * 
 * This file is part of the Spark-Recom project
 * (https://github.com/skrusche63/spark-recom).
 * 
 * Spark-Recom is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * Spark-Recom is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with
 * Spark-Recom. 
 * 
 * If not, see <http://www.gnu.org/licenses/>.
 */
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

class RequestContext(  
  /*
   * Reference to the common SparkContext; this context can be used
   * to access HDFS based data sources or leverage the Spark machine
   * learning library or other Spark based functionality
   */
  @transient val sc:SparkContext) extends Serializable {

  val sqlc = new SQLContext(sc)
  
  val config = Configuration
  /*
   * The base directory for all internal data sources and sinks
   */
  val base = Configuration.model
  /*
   * The RemoteContext is used to interact with the User Preference engine
   * as well as with other engines from Predictiveworks.
   */
  private val rc = new RemoteContext()

  def send(service:String,message:String) = rc.send(service, message)
  
}