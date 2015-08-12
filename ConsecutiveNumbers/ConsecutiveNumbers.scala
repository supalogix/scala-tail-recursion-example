import scala.annotation.tailrec

object ConsecutiveNumbers {
	def main( args:Array[String] ) {

		/// ----------------------------------------------------------------
		/// Perform Basic Error Checking 
		/// ----------------------------------------------------------------
		/// The program could break with bad input. I made a very half-assed
		/// attempt to minimize that possibility.
		///
		if( args.length == 0 ) {
			System.out.println( 
				"Please provide an input file as an argument")
			return
		}

		val fileName:String = args(0)
		
		/// ----------------------------------------------------------------
		/// Iterate Over All Lines in a File
		/// ----------------------------------------------------------------
		/// We assume that all input for the program will come from a file
		/// provided as an argument. 
		///
		for( line <- io.Source.fromFile(fileName).getLines ) {

			/// -------------------------------------------------------------
			/// Split String by Whitespace
			/// -------------------------------------------------------------
			///
			val tokens = line.trim().split("""\s+""")

			/// -------------------------------------------------------------
			/// Convert Tokens to a Sequence of Integers
			/// -------------------------------------------------------------
			/// The algorithm needs to work on a sequence of integers.
			/// I perform some basic manipulations on the data to get it into
			/// the necessary format
			///
 			val nums:Seq[Int] = tokens
 				.filter( _.nonEmpty)
 				.map( _.toInt )

			/// -------------------------------------------------------------
			/// Run the Algorithm
			/// -------------------------------------------------------------
			///
			val results = getConsecutiveNumbers( nums )

			/// -------------------------------------------------------------
			/// Print the Results to Standard Output
			/// -------------------------------------------------------------
			/// 
			printResult( results )
		}
	}

	def getConsecutiveNumbers( args:Seq[Int] ):Seq[Tuple2[Int,Int]] = {

		/// ----------------------------------------------------------------
		/// Find Consecutive Numbers with Tail Call Optimization
		/// ----------------------------------------------------------------
		/// Use this nested inner function to recursively find consecutive 
		/// numbers with tail call optimization. 
		///
		/// Use the following convention: (A) "head" holds the values of the 
		/// numbers not yet processes, (B) "tail" holds values we can 
		/// potentially use for a future list, and (C) "bag" holds values 
		/// of known intervals that we can safely ignore
		///
		@tailrec
		def getConsecutiveNumbers(
			head:Seq[Int], tail:Seq[Int], 
			bag:Seq[Tuple2[Int,Int]]):Seq[Tuple2[Int,Int]] = {

			/// -------------------------------------------------------------
			/// Terminate Recursion 
			/// -------------------------------------------------------------
			/// We assume that we have processed all the input values for the
			/// program when the length of head has reached zero
			///
			if( head.length == 0 ) {

				/// ----------------------------------------------------------
				/// Consider Two Possible Terminating Conditions
				/// ----------------------------------------------------------
				/// Consider two possible situations when we need to terminate
				/// recursion: (1) the last element of the list belongs to a 
				/// interval (in which case we add it to the bag), and (2) 
				/// it does not (we ignore the last value).
				///
				if( tail.length > 1 ) {
					val lValue = tail(0)
					val rValue = tail.last

					return newBag( lValue, rValue, bag )
				}
				else 
					return bag
			}

			val headInit:Seq[Int] = head.init
			val headLast:Int = head.last

			/// -------------------------------------------------------------
			/// Define the Recurrence Relation 
			/// -------------------------------------------------------------
			/// Consider three possible situations for a recursive call:
			/// (1) a new interval, (2) an interval with only one element
			/// so far, and (3) an interval with more than one element.
			///
			if( tail.length == 0 ) 

				/// ----------------------------------------------------------
				/// Case 1: A New Interval
				/// ----------------------------------------------------------
				/// Start a new interval with the first element 
				///
				getConsecutiveNumbers( 
					headInit, 
					List( headLast ), 
					bag )
			else if (tail.length == 1 ) {
				
				/// ----------------------------------------------------------
				/// Case 2: An Interval with only One Element
				/// ----------------------------------------------------------
				/// Consider two cases: (a) we can append the head of the list 
				/// to create a new interval, and (b) we cannot so we use the 
				/// current value to start a new interval
				///
				if( headLast == (tail.last + 1 ) )
					getConsecutiveNumbers( 
						headInit, 
						tail :+ headLast, 
						bag )
				else
					getConsecutiveNumbers( 
						headInit, 
						List( headLast ), 
						bag )
			}
			else {
				/// ----------------------------------------------------------
				/// Case 3: An Interval with More Than One Element
				/// ----------------------------------------------------------
				/// Consider two cases: (a) we can continue adding values to 
				/// the interval under consideration, and (b) we have found
				/// a value that we cannot add to the interval under
				/// consideration.
				///
				if( headLast == (tail.last + 1) ) 
					getConsecutiveNumbers( 
						headInit, 
						tail :+ headLast, 
						bag )
				else {
					val lValue = tail(0)
					val rValue = tail.last

					getConsecutiveNumbers( 
						headInit, 
						List( headLast ), 
						newBag(lValue,rValue, bag) )
				}
			}
		}

		/// ----------------------------------------------------------------
		/// Create a New Bag
		/// ----------------------------------------------------------------
		/// Use this helper function to create a new bag when recursively
		/// looking for intervals
		///
		def newBag( 
			lValue:Int, 
			rValue:Int, 
			currentBag:Seq[Tuple2[Int,Int]]):Seq[Tuple2[Int,Int]] = {

			currentBag :+ (lValue, rValue)
		}

		/// ----------------------------------------------------------------
		/// Solve the Problem with a Nested Inner Function
		/// ----------------------------------------------------------------
		/// Use the encapsulated recursive algorithm to solve our problem
		///
		/// Also, reverse args before passing it as an argument because we 
		/// use a stack implementation for each recursive call
		///
		getConsecutiveNumbers( args.reverse, List(), List() )
	}

	def printResult( result:Seq[Tuple2[Int,Int]] ) {
		if( result.length == 0 ) {
			println()
			return
		}
			
		val length = result.length
		result.zipWithIndex.foreach{
			case(x,i) => {
				if( i == (length - 1) )
					println( x._1 + "-" + x._2 )
				else
					print( x._1 + "-" + x._2 + " " )
			}
		}
	}
}
