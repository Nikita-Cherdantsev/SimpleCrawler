package models

import play.api.libs.json._

case class ResponseModel(url: String, errMsg: Option[String] = None, title: Option[String] = None)

object ResponseModel {
  implicit val responseModelWrites: Writes[ResponseModel] = Json.writes[ResponseModel]

  def toJson(allResponses: List[ResponseModel]): JsObject = {
    val result = allResponses.map(resp => Json.toJson(resp))

    Json.obj("result" -> result)
  }
}
