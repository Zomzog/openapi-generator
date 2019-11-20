/**
* OpenAPI Petstore
* This spec is mainly for testing Petstore server and contains fake endpoints, models. Please do not use this for any other purpose. Special characters: \" \\
*
* The version of the OpenAPI document: 1.0.0
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package org.openapitools.client.models


import com.squareup.moshi.Json
import java.io.Serializable
/**
 * 
 * @param integer None
 * @param int32 None
 * @param int64 None
 * @param number None
 * @param float None
 * @param double None
 * @param string None
 * @param patternWithoutDelimiter None
 * @param byte None
 * @param binary None
 * @param date None
 * @param dateTime None
 * @param password None
 * @param callback None
 */

data class InlineObject3 (
    /* None */
    @Json(name = "number")
    val number: java.math.BigDecimal,
    /* None */
    @Json(name = "double")
    val double: kotlin.Double,
    /* None */
    @Json(name = "pattern_without_delimiter")
    val patternWithoutDelimiter: kotlin.String,
    /* None */
    @Json(name = "byte")
    val byte: kotlin.ByteArray,
    /* None */
    @Json(name = "integer")
    val integer: kotlin.Int? = null,
    /* None */
    @Json(name = "int32")
    val int32: kotlin.Int? = null,
    /* None */
    @Json(name = "int64")
    val int64: kotlin.Long? = null,
    /* None */
    @Json(name = "float")
    val float: kotlin.Float? = null,
    /* None */
    @Json(name = "string")
    val string: kotlin.String? = null,
    /* None */
    @Json(name = "binary")
    val binary: java.io.File? = null,
    /* None */
    @Json(name = "date")
    val date: java.time.LocalDate? = null,
    /* None */
    @Json(name = "dateTime")
    val dateTime: java.time.LocalDateTime? = null,
    /* None */
    @Json(name = "password")
    val password: kotlin.String? = null,
    /* None */
    @Json(name = "callback")
    val callback: kotlin.String? = null
) 
: Serializable 

{
	companion object {
		private const val serialVersionUID: Long = 123
	}
}

