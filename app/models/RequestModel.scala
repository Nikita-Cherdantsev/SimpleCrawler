package models

import play.api.libs.json.{Json, OFormat}

case class RequestModel(urls: List[String])

object RequestModel {
  implicit val format: OFormat[RequestModel] = Json.format[RequestModel]
}