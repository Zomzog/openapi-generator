{-
   OpenAPI Petstore
   This is a sample server Petstore server. For this sample, you can use the api key `special-key` to test the authorization filters.

   The version of the OpenAPI document: 1.0.0

   NOTE: This file is auto generated by the openapi-generator.
   https://github.com/openapitools/openapi-generator.git
   Do not edit this file manually.
-}


module Data.Category exposing (Category, decoder, encode, toString)

import Dict exposing (Dict)
import Json.Decode as Decode exposing (Decoder)
import Json.Decode.Pipeline exposing (decode, optional, required)
import Json.Encode as Encode


{-| A category for a pet
-}
type alias Category =
    { id : Maybe Int
    , name : Maybe String
    }


decoder : Decoder Category
decoder =
    decode Category
        |> optional "id" (Decode.nullable Decode.int) Nothing
        |> optional "name" (Decode.nullable Decode.string) Nothing


encode : Category -> Encode.Value
encode model =
    Encode.object
        [ ( "id", Maybe.withDefault Encode.null (Maybe.map Encode.int model.id) )
        , ( "name", Maybe.withDefault Encode.null (Maybe.map Encode.string model.name) )
        ]


toString : Category -> String
toString =
    Encode.encode 0 << encode
