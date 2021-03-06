//
// Cat.swift
//
// Generated by openapi-generator
// https://openapi-generator.tech
//

import Foundation

internal struct Cat: Codable, Hashable {

    internal var className: String
    internal var color: String? = "red"
    internal var declawed: Bool?

    internal init(className: String, color: String? = "red", declawed: Bool? = nil) {
        self.className = className
        self.color = color
        self.declawed = declawed
    }

}
