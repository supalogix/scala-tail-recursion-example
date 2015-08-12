import scala.annotation.tailrec

object DuplicationRemoving {

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
			/// Remove All Whitespace And Tokenize String By Comma Value
			/// -------------------------------------------------------------
			/// The input could have strange whitespace characters between
			/// commas. I use a simple regex to "automate" the tokenization 
			/// process.
			///
			val tokens = line.replaceAll("\\s", "").split(",")

			/// -------------------------------------------------------------
			/// Convert Tokens to a Sequence of Integers
			/// -------------------------------------------------------------
			/// The algorithm needs to work on a sequence of integers. 
			/// I perform some basic manipulations on the data to get it into 
			/// the necessary format
			///
			val nums:Seq[Int] = tokens
				.filter( _.nonEmpty )
				.map( _.toInt )

			/// -------------------------------------------------------------
			/// Run the Algorithm
			/// -------------------------------------------------------------
			///
			val newList:Seq[Int] = removeDuplicates(nums)

			/// -------------------------------------------------------------
			/// Print the Results to Standard Output
			/// -------------------------------------------------------------
			///
			printResult(newList)
		}
	}

	def removeDuplicates( list:Seq[Int] ):Seq[Int] = {

		/// ----------------------------------------------------------------
		/// Remove Duplicates with Tail Call Optimization
		/// ----------------------------------------------------------------
		/// "oldList" holds the values of number not yet processed by the
		/// algorithm, and "newList" holds the values of numbers the 
		/// algorithm has already processed
		///
		/// We use a stack implementation so the caller should place 
		/// sequence values in reverse order 
		///
		@tailrec
		def removeDuplicates( 
			oldList:Seq[Int], 
			newList:Seq[Int] ):Seq[Int]  = {

			val length = oldList.length

			/// -------------------------------------------------------------
			/// Terminate Recursion 
			/// -------------------------------------------------------------
			/// We assume that we have processed all the input values for the
			/// program when the length of oldList has reached zero
			/// 
			if( length == 0 ) 
				return newList

			val init:Seq[Int] = oldList.init
			val last:Int = oldList.last

	
			/// -------------------------------------------------------------
			/// Define the Recurrence Relation 
			/// -------------------------------------------------------------
			/// We have to consider two cases with each recursive call: (a) 
			/// we have seen the number before, and (b) we have not seen the 
			/// number before. 
			/// 
			if( newList.contains( last ) ) 
				removeDuplicates( init , newList )
			else  
				removeDuplicates( init , newList :+ last )
		}

		/// ----------------------------------------------------------------
		/// Solve the Problem with a Nested Inner Function 
		/// ----------------------------------------------------------------
		/// Use the encapsulated recursive algorithm to solve our problem
		///
		/// Also, reverse the list before passing it in as an argument 
		/// because we use a stack implementation for each recursive call
		///
		removeDuplicates( list.reverse, List() )
	}

	def printResult( result:Seq[Any] ) {
		val length = result.length
		result.zipWithIndex.foreach{
			case(x,i) => {
				if( i == (length - 1) )
					println( x )
				else
					print( x + ", " )
			}
		}
	}
}
