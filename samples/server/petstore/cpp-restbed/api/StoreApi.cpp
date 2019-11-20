/**
 * OpenAPI Petstore
 * This is a sample server Petstore server. For this sample, you can use the api key `special-key` to test the authorization filters.
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI-Generator 4.2.0-SNAPSHOT.
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


#include <corvusoft/restbed/byte.hpp>
#include <corvusoft/restbed/string.hpp>
#include <corvusoft/restbed/settings.hpp>
#include <corvusoft/restbed/request.hpp>

#include "StoreApi.h"

namespace org {
namespace openapitools {
namespace server {
namespace api {

using namespace org::openapitools::server::model;

StoreApi::StoreApi() {
	std::shared_ptr<StoreApiStoreOrderOrderIdResource> spStoreApiStoreOrderOrderIdResource = std::make_shared<StoreApiStoreOrderOrderIdResource>();
	this->publish(spStoreApiStoreOrderOrderIdResource);
	
	std::shared_ptr<StoreApiStoreInventoryResource> spStoreApiStoreInventoryResource = std::make_shared<StoreApiStoreInventoryResource>();
	this->publish(spStoreApiStoreInventoryResource);
	
	std::shared_ptr<StoreApiStoreOrderResource> spStoreApiStoreOrderResource = std::make_shared<StoreApiStoreOrderResource>();
	this->publish(spStoreApiStoreOrderResource);
	
}

StoreApi::~StoreApi() {}

void StoreApi::startService(int const& port) {
	std::shared_ptr<restbed::Settings> settings = std::make_shared<restbed::Settings>();
	settings->set_port(port);
	settings->set_root("/v2");
	
	this->start(settings);
}

void StoreApi::stopService() {
	this->stop();
}

StoreApiStoreOrderOrderIdResource::StoreApiStoreOrderOrderIdResource()
{
	this->set_path("/store/order/{orderId: .*}/");
	this->set_method_handler("DELETE",
		std::bind(&StoreApiStoreOrderOrderIdResource::DELETE_method_handler, this,
			std::placeholders::_1));
	this->set_method_handler("GET",
		std::bind(&StoreApiStoreOrderOrderIdResource::GET_method_handler, this,
			std::placeholders::_1));
}

StoreApiStoreOrderOrderIdResource::~StoreApiStoreOrderOrderIdResource()
{
}

void StoreApiStoreOrderOrderIdResource::set_handler_DELETE(
	std::function<std::pair<int, std::string>(
		std::string const &
	)> handler) {
	handler_DELETE_ = std::move(handler);
}

void StoreApiStoreOrderOrderIdResource::set_handler_GET(
	std::function<std::pair<int, std::string>(
		int64_t const &
	)> handler) {
	handler_GET_ = std::move(handler);
}

void StoreApiStoreOrderOrderIdResource::DELETE_method_handler(const std::shared_ptr<restbed::Session> session) {

	const auto request = session->get_request();

			// Getting the path params
			const std::string orderId = request->get_path_parameter("orderId", "");



			// Change the value of this variable to the appropriate response before sending the response
			int status_code = 200;
			std::string result = "successful operation";

			if (handler_DELETE_)
			{
				std::tie(status_code, result) = handler_DELETE_(
					orderId
				);
			}

			if (status_code == 400) {
				session->close(400, result.empty() ? "Invalid ID supplied" : std::move(result), { {"Connection", "close"} });
				return;
			}
			if (status_code == 404) {
				session->close(404, result.empty() ? "Order not found" : std::move(result), { {"Connection", "close"} });
				return;
			}

}

void StoreApiStoreOrderOrderIdResource::GET_method_handler(const std::shared_ptr<restbed::Session> session) {

	const auto request = session->get_request();

			// Getting the path params
			const int64_t orderId = request->get_path_parameter("orderId", 0L);



			// Change the value of this variable to the appropriate response before sending the response
			int status_code = 200;
			std::string result = "successful operation";

			if (handler_GET_)
			{
				std::tie(status_code, result) = handler_GET_(
					orderId
				);
			}

			if (status_code == 200) {
				std::shared_ptr<Order> response = NULL;
				session->close(200, result.empty() ? "successful operation" : std::move(result), { {"Connection", "close"} });
				return;
			}
			if (status_code == 400) {
				session->close(400, result.empty() ? "Invalid ID supplied" : std::move(result), { {"Connection", "close"} });
				return;
			}
			if (status_code == 404) {
				session->close(404, result.empty() ? "Order not found" : std::move(result), { {"Connection", "close"} });
				return;
			}

}


StoreApiStoreInventoryResource::StoreApiStoreInventoryResource()
{
	this->set_path("/store/inventory/");
	this->set_method_handler("GET",
		std::bind(&StoreApiStoreInventoryResource::GET_method_handler, this,
			std::placeholders::_1));
}

StoreApiStoreInventoryResource::~StoreApiStoreInventoryResource()
{
}

void StoreApiStoreInventoryResource::set_handler_GET(
	std::function<std::pair<int, std::string>(
		
	)> handler) {
	handler_GET_ = std::move(handler);
}


void StoreApiStoreInventoryResource::GET_method_handler(const std::shared_ptr<restbed::Session> session) {

	const auto request = session->get_request();




			// Change the value of this variable to the appropriate response before sending the response
			int status_code = 200;
			std::string result = "successful operation";

			if (handler_GET_)
			{
				std::tie(status_code, result) = handler_GET_(
					
				);
			}

			if (status_code == 200) {
				session->close(200, result.empty() ? "successful operation" : std::move(result), { {"Connection", "close"} });
				return;
			}

}



StoreApiStoreOrderResource::StoreApiStoreOrderResource()
{
	this->set_path("/store/order/");
	this->set_method_handler("POST",
		std::bind(&StoreApiStoreOrderResource::POST_method_handler, this,
			std::placeholders::_1));
}

StoreApiStoreOrderResource::~StoreApiStoreOrderResource()
{
}

void StoreApiStoreOrderResource::set_handler_POST(
	std::function<std::pair<int, std::string>(
		std::shared_ptr<Order> const &
	)> handler) {
	handler_POST_ = std::move(handler);
}


void StoreApiStoreOrderResource::POST_method_handler(const std::shared_ptr<restbed::Session> session) {

	const auto request = session->get_request();
	// Body params are present, therefore we have to fetch them
	int content_length = request->get_header("Content-Length", 0);
	session->fetch(content_length,
		[ this ]( const std::shared_ptr<restbed::Session> session, const restbed::Bytes & body )
		{

			const auto request = session->get_request();
			std::string file = restbed::String::format("%.*s\n", ( int ) body.size( ), body.data( ));
			/**
			 * Get body params or form params here from the file string
			 */




			// Change the value of this variable to the appropriate response before sending the response
			int status_code = 200;
			std::string result = "successful operation";

			if (handler_POST_)
			{
				std::tie(status_code, result) = handler_POST_(
					body
				);
			}

			if (status_code == 200) {
				session->close(200, result.empty() ? "successful operation" : std::move(result), { {"Connection", "close"} });
				return;
			}
			if (status_code == 400) {
				session->close(400, result.empty() ? "Invalid Order" : std::move(result), { {"Connection", "close"} });
				return;
			}

		});
}




}
}
}
}

