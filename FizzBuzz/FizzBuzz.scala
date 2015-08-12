object FizzBuzz {
	def main( args:Array[String] ) {

		///
		/// Perform basic error checking 
		///
		if( args.length == 0 ) {
			System.out.println( 
				"Please provide an input file as an argument")
			return
		}

		val fileName:String = args(0)

		for( line <- io.Source.fromFile(fileName).getLines ) {
			val tokens = line.split(" ")

			val result:Seq[String] = FizzBuzz( 
				tokens(0).toInt, 
				tokens(1).toInt, 
				tokens(2).toInt )

			printResult( result )
		}
	}

	def FizzBuzz( 
		A:Integer, 
		B:Integer, 
		N:Integer ):Seq[String] = {

		val product = (A * B)

		val result:Seq[String] = (1 to N).map( i => { 
			if( ( i % product) == 0 ) {
				"FB"
			}
			else if( (i % A) == 0 ) {
				"F"
			}
			else if( (i % B) == 0 ) {
				"B"
			}
			else {
				"" + i
			}
		})

		result
	}

	def printResult( result:Seq[String] ) {
		val length = result.length
		result.zipWithIndex.foreach{
			case(x,i) => {
				if( i == (length - 1) )
					println( x )
				else
					print( x + " " )
			}
		}
	}
}
