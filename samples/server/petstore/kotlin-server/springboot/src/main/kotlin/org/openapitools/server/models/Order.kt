/**
* OpenAPI Petstore
* This is a sample server Petstore server. For this sample, you can use the api key `special-key` to test the authorization filters.
*
* OpenAPI spec version: 1.0.0
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package org.openapitools.server.models


/**
 * An order for a pets from the pet store
 * @param id 
 * @param petId 
 * @param quantity 
 * @param shipDate 
 * @param status Order Status
 * @param complete 
 */
data class Order (
    val id: kotlin.Long? = null,
    val petId: kotlin.Long? = null,
    val quantity: kotlin.Int? = null,
    val shipDate: java.time.LocalDateTime? = null,
    /* Order Status */
    val status: Order.Status? = null,
    val complete: kotlin.Boolean? = null
) {

    /**
    * Order Status
    * Values: placed,approved,delivered
    */
    enum class Status(val value: kotlin.String){
    
        placed("placed"),
    
        approved("approved"),
    
        delivered("delivered");
    
    }

}

