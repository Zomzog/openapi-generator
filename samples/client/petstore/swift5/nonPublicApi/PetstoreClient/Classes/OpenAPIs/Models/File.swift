//
// File.swift
//
// Generated by openapi-generator
// https://openapi-generator.tech
//

import Foundation

/** Must be named &#x60;File&#x60; for test. */
internal struct File: Codable, Hashable {

    /** Test capitalization */
    internal var sourceURI: String?

    internal init(sourceURI: String? = nil) {
        self.sourceURI = sourceURI
    }

}
