import scala.annotation.tailrec

object Sum {
	def main(args:Array[String]) = {
		val list = (1 to 100000)
		val sum1:Int = getRecursiveSum( list )
		val sum2:Int = getIterativeSum( list )

		System.out.println( sum1 )
		System.out.println( sum2 ) }

	/**
	  *
	  */
	@tailrec
	def getRecursiveSum( 
		sequence:Seq[Int], 
		currentSum:Int = 0 ):Int = {

		/// ======================================================================
		/// Base Case
		/// ======================================================================
		///
		/// If the caller passes an empty Sequence then we assume that we have 
		/// reached the end of the algorithm and terminate recursion.
		///
		if( sequence.isEmpty ) 
			return currentSum

		/// ======================================================================
		/// Mutate State
		/// ======================================================================
		///
		/// Define two local variables for this function to process. 'last'
		/// references the last item in the sequence provided by the caller.
		/// 'init' references the sequence provided minus the last element. 
		///
		val init:Seq[Int] = sequence.init
		val last:Int = sequence.last


		/// ======================================================================
		/// Define Recurrance Relation
		/// ======================================================================
		///
		/// Determine the sum of numbers by taking the current sum and adding the
		/// first element from the list that the caller provided. Make a recursive
		/// call to process the sum of the remaning list.
		///
		getRecursiveSum( init, currentSum + last ) }

	/**
	  *
	  */
	def getIterativeSum( 
		sequence:Seq[Int] ):Int = {

		/// ======================================================================
		/// Define Data Bag
		/// ======================================================================
		///
		///
		///
		var sum:Int = 0

		/// ======================================================================
		/// Iterate over Sequence
		/// ======================================================================
		///
		///
		///
		for( i <- sequence )
			sum += i

		/// ======================================================================
		/// Return Result
		/// ======================================================================
		/// 
		/// We have reached the end of the algorithm, and we assume that the
		/// variable sum contains the proper value.
		///
		sum }

}
