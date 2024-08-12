package controllers

import models.{RequestModel, ResponseModel}
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import play.api.Logging
import play.api.libs.json._
import play.api.libs.ws.ahc.StandaloneAhcWSClient
import play.api.mvc._

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


@Singleton
class UrlHandlerController @Inject()(ws: StandaloneAhcWSClient, val controllerComponents: ControllerComponents) extends BaseController with Logging {
  def handleUrls(): Action[JsValue] = Action(parse.json).async { implicit request =>
    request.body.validate[RequestModel] match {
      case JsSuccess(reqM, _) =>
        val listOfResponsesF = reqM.urls.map { url =>
          ws.url(url)
            .get()
            .map { wsResponse =>
              try {
                val htmlBody: String = wsResponse.body
                val document: Document = Jsoup.parse(htmlBody)

                ResponseModel(url = url, title = Some(document.title()))
              } catch {
                case error: Throwable =>
                  ResponseModel(url = url, errMsg = Some(error.getMessage))
              }
            }
            .recover { ex =>
              ResponseModel(url = url, errMsg = Some(ex.getMessage))
            }
        }

        Future.sequence(listOfResponsesF)
          .andThen(_ => ws.close())
          .map { listOfResponses =>
            Ok(ResponseModel.toJson(listOfResponses))
          }

      case JsError(_) =>
        Future(BadRequest("Invalid JSON. Provide the correct body, please."))
    }
  }
}
