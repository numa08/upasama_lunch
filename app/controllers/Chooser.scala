package controllers

import models.Shop

object Chooser {
	
	val makeRandomChooser = (shops : List[Shop]) => {
		val sortedList = shops.sortWith(_.likely > _.likely)
		var likeSum = 0.0
		sortedList.foreach(likeSum += _.likely)
		val targetList = sortedList.map{tem => new Shop(0, tem.name, tem.likely / likeSum)}

		sortedList.foreach(likeSum += _.likely)

		def choosen (targetList : List[Shop], likeSum : Double) = {
			val rand = Math.random
			var sum = 0.0
			val result = targetList.find{(shop: Shop) =>
				sum += shop.likely
				rand < sum
			}
			if(result == None){
				targetList.last
			} else {
				result.get
			}
		}
		choosen(targetList, likeSum)
	}	
}