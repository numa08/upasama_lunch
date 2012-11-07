package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Shop(id: Long, name: String, likely: Double)

object Shop {
	def all() : List[Shop] = DB.withConnection { implicit c =>
		SQL("select * from shop").as(shop *)
	} 

	def create(name: String, likely: Int) = {
		DB.withConnection { implicit c => 
			SQL("insert into shop (name,likely) values ({name}, {likely})").on(
				'name -> name,
				 'likely -> likely * 1.0)
				.executeUpdate()
			}
	}

	def delete(id:Long) = {
		DB.withConnection{ implicit c => 
			SQL("delete from shop where id = {id}").on(
				'id -> id
				).executeUpdate()
		}
	}

	val shop = {
		get[Long]("id") ~
		get[String]("name") ~
		get[Double]("likely") map {
			case id~name~likely => Shop(id,name,likely)
		}
	}
}