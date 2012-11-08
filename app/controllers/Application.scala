package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Shop
import play.api.libs.json._

object Application extends Controller {
  
  def index = Action {
    Redirect(routes.Application.shops)
  }

  def shops = Action{
  	Ok(views.html.index(Shop.all(), shopForm))
  }
  
  def choose (callBack : String) = Action {
  	val shops = Shop.all()
  	val choose = Chooser.makeRandomChooser(shops)
  	Ok(callBack + "("  + Json.toJson(Map("name" -> choose.name)) +")")
  }

  def newShops = Action {	implicit request =>
  	shopForm.bindFromRequest.fold(
  		errors => BadRequest(views.html.index(Shop.all(), errors)),
  		result => {
  			Shop.create(result._1, result._2)
  			Redirect(routes.Application.shops)
  		}
	)
  }

  def deleteShops(id:Long) = Action{
  	Shop.delete(id)
  	Redirect(routes.Application.shops)
  }

  val shopForm = Form{
  	tuple(
  		"name" -> nonEmptyText,
  		"like" -> number(min=1, max=20)
  	)
  }
}